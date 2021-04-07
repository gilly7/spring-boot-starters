package org.shield.mybatis.mapper.crud;

import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @param <T> 不能为空
 * @author zacksleo <zacksleo@gmail.com>
 */
@RegisterMapper
public interface SelectByLogicIdMapper<T> {

    /**
     * 根据逻辑主键进行查询一条记录
     *
     * @param logicId
     * @return
     */
    @SelectProvider(type = CrudProvider.class, method = "dynamicSQL")
    T selectByLogicId(@Param("logicId") String logicId);
}
