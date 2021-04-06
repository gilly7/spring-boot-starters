package org.shield.mybatis.mappers.crud;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 通用Mapper接口, 按逻辑主键更新
 *
 * @param <T> 不能为空
 * @author zacksleo <zacksleo@gmail.com>
 */
@RegisterMapper
public interface UpdateByLogicIdSelectiveMapper<T> {

    /**
     * 根据逻辑主键更新属性不为null的值
     *
     * @param logicId
     * @param record
     * @return
     */
    @UpdateProvider(type = CrudProvider.class, method = "dynamicSQL")
    int updateByLogicIdSelective(@Param("logicId") String logicId, @Param("record") T record);
}
