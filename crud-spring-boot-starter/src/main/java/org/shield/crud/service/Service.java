package org.shield.crud.service;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;
import java.util.List;

/**
 * Service 层 基础接口，其他Service 接口 请继承该接口
 *
 * @author zacksleo@gmail.com
 */
public interface Service<T> {

    /**
     * 插入一条记录
     *
     * @param model
     * @return
     */
    T create(T model);

    /**
     * 批量插入记录
     *
     * @param models
     * @return
     */
    List<T> create(List<T> models);

    /**
     * 通过自增主鍵刪除一条记录
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 通过业务主键删除一条记录
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 更新一条记录
     *
     * @param model
     * @return
     */
    T update(T model);

    /**
     * 根据编码更新一条记录
     *
     * @param id
     * @param model
     * @return
     */
    T update(String id, T model);

    /**
     *
     * 通过自增主键查找一条记录
     *
     * @param id
     * @return
     */
    T findById(Integer id);

    /**
     *
     * 通过业务主键查找一条记录
     *
     * @param id
     * @return
     */
    T findById(String id);

    /**
     * 通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
     *
     * @param fieldName
     * @param value
     * @return
     * @throws TooManyResultsException
     */
    T findBy(String fieldName, Object value) throws TooManyResultsException;

    /**
     * 根据条件查找
     *
     * @param condition
     * @return
     */
    List<T> findByCondition(Condition condition);

    /**
     * 获取所有
     *
     * @return
     */
    List<T> findAll();
}
