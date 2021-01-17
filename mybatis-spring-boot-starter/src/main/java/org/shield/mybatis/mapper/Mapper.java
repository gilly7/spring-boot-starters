package org.shield.mybatis.mapper;


import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 自定义通用 Mapper, 特别注意，该接口不能被扫描到，否则会出错
 *
 *
 * @author zacksleo@gmail.com
 */
public interface Mapper<T>
        extends BaseMapper<T>, ConditionMapper<T>, IdsMapper<T>, InsertListMapper<T> {

}
