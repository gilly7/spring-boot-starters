package org.shield.mybatis.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 字段唯一判断查询
 *
 * @author zacksleo@gmail.com
 */
@Mapper
public interface UniqueMapper {

    /**
     * 查询某个表中是否存在 ${field}=#{value}
     *
     * @param table
     * @param field
     * @param value
     * @return
     */
    @Select("SELECT EXISTS(SELECT 1 FROM ${table} WHERE `${field}` = #{value})")
    boolean exists(@Param("table") String table, @Param("field") String field, @Param("value") String value);

    /**
     * 查询某个表中满足条件为 ${field}=#{value}，且字段名为 ${sn} 的值
     *
     * @param table
     * @param field
     * @param value
     * @param sn
     * @return
     */
    @Select("SELECT `${sn}` FROM ${table} WHERE `${field}` = #{value}")
    List<String> findByField(@Param("table") String table, @Param("field") String field, @Param("value") String value,
            @Param("sn") String sn);
}
