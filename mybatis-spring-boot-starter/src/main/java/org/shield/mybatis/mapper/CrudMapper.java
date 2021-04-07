package org.shield.mybatis.mapper;

import org.shield.mybatis.mapper.crud.DeleteByLogicIdMapper;
import org.shield.mybatis.mapper.crud.SelectByLogicIdMapper;
import org.shield.mybatis.mapper.crud.UpdateByLogicIdMapper;
import org.shield.mybatis.mapper.crud.UpdateByLogicIdSelectiveMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 通用Mapper接口,基础删除, 特别注意，该接口不能被扫描到，否则会出错
 *
 * @param <T> 不能为空
 * @author liuzh
 */
@RegisterMapper
public interface CrudMapper<T> extends DeleteByLogicIdMapper<T>, SelectByLogicIdMapper<T>, UpdateByLogicIdMapper<T>,
        UpdateByLogicIdSelectiveMapper<T> {
}
