package org.shield.mybatis.plugin;

import java.lang.annotation.*;

/**
 * 逻辑主键注解 支持两种生成策略：雪花算法 和 UUID
 *
 * @author zacksleo@gmail.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LogicId {

    /**
     * 使用的生成策略: 雪花算法(默认)/UUID
     *
     * @return
     */
    LogicIdType value() default LogicIdType.SNOWFLAKE;

    /**
     * 是否使精简模式： 雪花算法使用16进制缩写形式，UUID省略横线
     */
    boolean shortable() default true;

    /**
     * 前缀
     *
     * @return
     */
    String prefix() default "";

    /**
     * id类型
     */
    enum LogicIdType {
        /**
         * UUID
         */
        UUID,
        /**
         * 雪花id
         */
        SNOWFLAKE
    }
}
