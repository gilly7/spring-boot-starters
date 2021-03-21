package org.shield.admin.mapper;

import org.shield.admin.model.AdminAccountAuth;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.shield.mybatis.mapper.Mapper;

/**
 * AdminAccountAuth Mapper
 *
 * @author zacksleo@gmail.com
 */
public interface AdminAccountAuthMapper extends Mapper<AdminAccountAuth> {

    /**
     * 按认证方式和认证标识查询用户编号
     *
     * @param source
     * @param sourceId
     * @return
     */
    @Select("SELECT * FROM admin_account_auth WHERE `source` = #{source} AND `source_id` = #{sourceId} AND is_active=1 AND is_deleted=0 limit 1")
    AdminAccountAuth findAuth(@Param("source") Integer source, @Param("sourceId") String sourceId);

    /**
     * 根据用户编号和认证方式查询一条记录
     *
     * @param source
     * @param accountId
     * @return
     */
    @Select("SELECT * FROM admin_account_auth WHERE `source` = #{source} AND `account_id` = #{accountId} AND is_active=1 AND is_deleted=0 limit 1")
    AdminAccountAuth findByIdAndSource(@Param("source") Integer source, @Param("accountId") String accountId);
}
