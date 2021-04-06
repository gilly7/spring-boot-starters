package org.shield.mybatis.mappers.crud;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 通用Mapper接口, 按逻辑主键更新
 * @param <T> 不能为空
 * @author zacksleo <zacksleo@gmail.com>
 */
@RegisterMapper
public interface UpdateByLogicIdMapper<T> {

    /**
     * 根据逻辑主键更新实体全部字段，null值会被更新
     *
     * @param logicId
     * @param record
     * @return
     */
    @UpdateProvider(type = CrudProvider.class, method = "dynamicSQL")
    int updateByLogicId(@Param("logicId") String logicId, @Param("record") T record);
}
