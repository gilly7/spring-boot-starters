package org.shield.mybatis.mapper.crud;

import tk.mybatis.mapper.annotation.RegisterMapper;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;

/**
 * @param <T> 不能为空
 * @author zacksleo <zacksleo@gmail.com>
 */
@RegisterMapper
public interface DeleteByLogicIdMapper<T> {

    /**
     * 根据逻辑主键删除一条记录
     *
     * @param logicId
     * @return
     */
    @DeleteProvider(type = CrudProvider.class, method = "dynamicSQL")
    int deleteByLogicId(@Param("logicId") String logicId);
}
