package org.shield.admin.service;

import java.util.List;
import org.shield.admin.model.RolePermission;
import org.shield.admin.form.RolePermissionForm;
import org.shield.crud.service.Service;

/**
 * 角色的权限
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
public interface RolePermissionService extends Service<RolePermission> {

    /**
     * 更新角色的权限
     *
     * @param roleId
     * @param form
     * @return
     */
    List<String> update(String roleId, RolePermissionForm form);

    /**
     * 查询角色的权限
     *
     * @param roleId
     * @return
     */
    List<String> list(String roleId);
}
