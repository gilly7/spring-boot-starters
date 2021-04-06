package org.shield.mybatis.mappers.crud;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 通用Mapper接口,基础删除
 *
 * @param <T> 不能为空
 * @author liuzh
 */
@RegisterMapper
public interface CrudMapper<T> extends DeleteByLogicIdMapper<T>, SelectByLogicIdMapper<T>, UpdateByLogicIdMapper<T>,
        UpdateByLogicIdSelectiveMapper<T> {
}
