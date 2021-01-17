package org.shield.crud.service;

import org.shield.crud.exception.ServiceException;
import org.shield.mybatis.mapper.Mapper;
import org.shield.mybatis.plugin.LogicId;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 基于通用 MyBatis Mapper 插件的 Service 接口的实现
 *
 * @author zacksleo@gmail.com
 */
public abstract class AbstractService<T> implements Service<T> {

    @Autowired
    protected Mapper<T> mapper;

    /**
     * 当前泛型真实类型的Class
     */
    private Class<T> modelClass;

    @SuppressWarnings("unchecked")
    public AbstractService() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) genericSuperclass;
            modelClass = (Class<T>) pt.getActualTypeArguments()[0];
        }
    }

    public T create(T model) {
        mapper.insertSelective(model);
        return model;
    }

    public List<T> create(List<T> models) {
        mapper.insertList(models);
        return models;
    }

    public void deleteById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void deleteById(String id) {
        String idNameCameCase = getLogicId();
        Condition condition = new Condition(modelClass);
        condition.createCriteria().andEqualTo(idNameCameCase, id);
        mapper.deleteByCondition(condition);
    }

    public T update(T model) {
        mapper.updateByPrimaryKeySelective(model);
        return model;
    }

    public T update(String id, T model) {
        String idNameCameCase = getLogicId();
        Condition condition = new Condition(modelClass);
        condition.createCriteria().andEqualTo(idNameCameCase, id);
        mapper.updateByConditionSelective(model, condition);
        return model;
    }

    public T findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public T findById(String id) {
        return this.findBy(getLogicId(), id);
    }

    @Override
    public T findBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.getDeclaredConstructor().newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<T> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    public List<T> findAll() {
        return mapper.selectAll();
    }

    /**
     * 将 DAO 类转成逻辑编号字段
     */
    private String getLogicId() {
        Field[] fields = modelClass.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(LogicId.class)) {
                return field.getName();
            }
        }
        return lowerFirst(modelClass.getSimpleName().replace("DAO", "").replace("DO", "")) + "Id";
    }

    /**
     * 首字母小写
     */
    private String lowerFirst(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }
}
