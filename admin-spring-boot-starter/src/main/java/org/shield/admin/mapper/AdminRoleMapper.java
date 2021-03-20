package org.shield.admin.mapper;

import java.util.List;
import org.shield.admin.model.AdminRole;
import org.apache.ibatis.annotations.Select;
import org.shield.mybatis.mapper.Mapper;

/**
 * AdminRole Mapper
 *
 * @author zacksleo@gmail.com
 */
public interface AdminRoleMapper extends Mapper<AdminRole> {

    /**
     * 查询管理员的角色编号列表
     *
     * @param adminId
     * @return
     */
    @Select("select role_id from admin_role where admin_id=#{adminId} and is_deleted=0")
    List<String> findRoleIds(String adminId);
}
