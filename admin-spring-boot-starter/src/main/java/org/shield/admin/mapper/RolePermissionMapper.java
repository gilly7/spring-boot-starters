package org.shield.admin.mapper;

import java.util.List;
import org.shield.admin.model.RolePermission;
import org.shield.mybatis.mapper.Mapper;

/**
 * RolePermission Mapper
 *
 * @author zacksleo@gmail.com
 */
public interface RolePermissionMapper extends Mapper<RolePermission> {

    /**
     * 根据角色编号列表，查询所有关联的权限编号列表
     *
     * @param roleIds
     * @return
     */
    List<String> findPermissionIds(List<String> roleIds);
}
