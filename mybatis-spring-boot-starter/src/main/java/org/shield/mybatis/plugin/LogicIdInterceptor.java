package org.shield.mybatis.plugin;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 * @author xub zacksleo@gmail.com
 * @see https://github.com/yudiandemingzi/spring-boot-mybatis-interceptor
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }), })
public class LogicIdInterceptor implements Interceptor {

    private final String INSERT_STR = "INSERT";

    /**
     * key值为class对象 value可以理解成是该类带有LogicId注解的属性，只不过对属性封装了一层。 它是非常能够提高性能的处理器
     * 它的作用就是不用每一次一个对象经来都要看下它的哪些属性带有LogicId注解
     * 毕竟类的反射在性能上并不友好。只要key包含该对象那就不需要检查它哪些属性带LogicId注解。
     */
    private Map<Class, List<AbstractHandler>> handlerMap = new ConcurrentHashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        // args数组对应对象就是上面@Signature注解中args对应的对应类型
        MappedStatement mappedStatement = (MappedStatement) args[0];
        // 实体对象
        Object entity = args[1];
        if (INSERT_STR.equalsIgnoreCase(mappedStatement.getSqlCommandType().name())) {
            // 获取实体集合
            Set<Object> entitySet = getEntitySet(entity);
            // 批量设置id
            for (Object object : entitySet) {
                process(object);
            }
        }
        return invocation.proceed();
    }

    /**
     * object是需要插入的实体数据,它可能是对象,也可能是批量插入的对象。 如果是单个对象,那么object就是当前对象
     * 如果是批量插入对象，那么object就是一个map集合,key值为"list",value为ArrayList集合对象
     */
    private Set<Object> getEntitySet(Object object) {
        //
        Set<Object> set = new HashSet<>();
        if (object instanceof Map) {
            // 批量插入对象
            Collection values = (Collection) ((Map) object).get("list");
            for (Object value : values) {
                if (value instanceof Collection) {
                    set.addAll((Collection) value);
                } else {
                    set.add(value);
                }
            }
        } else {
            // 单个插入对象
            set.add(object);
        }
        return set;
    }

    /**
     * @todo 性能优化点，如果有两个都是user对象同时,那么只需有个进行反射处理属性就好了,另一个只需执行下面的for循环
     * @param object
     * @throws Throwable
     */
    private void process(Object object) throws Throwable {
        Class handlerKey = object.getClass();
        List<AbstractHandler> handlerList = handlerMap.get(handlerKey);
        SYNC: if (handlerList == null) {
            synchronized (this) {
                handlerList = handlerMap.get(handlerKey);
                // 如果到这里map集合已经存在，则跳出到指定SYNC标签
                if (handlerList != null) {
                    break SYNC;
                }
                handlerMap.put(handlerKey, handlerList = new ArrayList<>());
                // 反射工具类 获取带有LogicId注解的所有属性字段
                Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    LogicId annotation = field.getAnnotation(LogicId.class);
                    if (annotation == null) {
                        continue;
                    }
                    // 1、添加UUID字符串作为主键
                    if (field.getType().isAssignableFrom(String.class)) {
                        if (annotation.value().equals(LogicId.LogicIdType.UUID)) {
                            handlerList.add(new UUIDHandler(field));
                            // 2、添加String类型雪花ID
                        } else if (annotation.value().equals(LogicId.LogicIdType.SNOWFLAKE)) {
                            handlerList.add(new UniqueStringHandler(field));
                        }
                    } else if (field.getType().isAssignableFrom(Long.class)) {
                        // 3、添加Long类型的雪花ID
                        if (annotation.value().equals(LogicId.LogicIdType.SNOWFLAKE)) {
                            handlerList.add(new UniqueLongHandler(field));
                        }
                    }
                }
            }
        }
        for (AbstractHandler handler : handlerList) {
            handler.accept(object);
        }
    }

    private static abstract class AbstractHandler {
        Field field;

        Snowflake snowflake = IdUtil.getSnowflake(1, 1);

        AbstractHandler(Field field) {
            this.field = field;
        }

        /**
         * 构造方法
         *
         * @param field
         * @param object
         * @throws Throwable
         */
        abstract void handle(Field field, Object object) throws Throwable;

        private boolean checkField(Object object, Field field) throws IllegalAccessException {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            // 如果该注解对应的属性已经被赋值，那么就不用通过雪花生成的ID
            return field.get(object) == null;
        }

        public void accept(Object o) throws Throwable {
            if (checkField(o, field)) {
                handle(field, o);
            }
        }
    }

    private static class UUIDHandler extends AbstractHandler {
        UUIDHandler(Field field) {
            super(field);
        }

        /**
         * 1、插入UUID主键
         */
        @Override
        void handle(Field field, Object object) throws Throwable {
            LogicId annotation = field.getAnnotation(LogicId.class);
            String uuid = UUID.randomUUID().toString();
            if (annotation.shortable()) {
                uuid = uuid.replace("-", "");
            }
            field.set(object, uuid);
        }
    }

    private static class UniqueLongHandler extends AbstractHandler {
        UniqueLongHandler(Field field) {
            super(field);
        }

        /**
         * 2、插入Long类型雪花ID
         */
        @Override
        void handle(Field field, Object object) throws Throwable {
            field.set(object, snowflake.nextId());
        }
    }

    private static class UniqueStringHandler extends AbstractHandler {
        UniqueStringHandler(Field field) {
            super(field);
        }

        /**
         * 3、插入String类型雪花ID
         */
        @Override
        void handle(Field field, Object object) throws Throwable {
            LogicId annotation = field.getAnnotation(LogicId.class);
            if (annotation.shortable()) {
                field.set(object, annotation.prefix() + String.format("%016x", snowflake.nextId()).toUpperCase());
            } else {
                field.set(object, annotation.prefix() + snowflake.nextIdStr());
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
