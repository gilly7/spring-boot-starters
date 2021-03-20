package org.shield.admin.service;

import java.util.List;
import org.shield.admin.model.AdminRole;
import org.shield.admin.form.AdminRoleForm;
import org.shield.crud.service.Service;

/**
 * 角色的权限
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
public interface AdminRoleService extends Service<AdminRole> {

    /**
     * 更新管理员的角色
     *
     * @param adminId
     * @param form
     * @return
     */
    List<String> update(String adminId, AdminRoleForm form);

    /**
     * 查询管理员的角色
     *
     * @param adminId
     * @return
     */
    List<String> list(String adminId);
}
