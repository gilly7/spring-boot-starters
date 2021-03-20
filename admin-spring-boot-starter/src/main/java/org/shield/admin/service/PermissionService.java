package org.shield.admin.service;

import java.util.List;
import org.shield.admin.model.Permission;
import org.shield.crud.service.Service;

/**
 * 权限
 *
 * @author zacksleo@gmail.com
 */
public interface PermissionService extends Service<Permission> {

    /**
     * 查询管理员的路由权限
     *
     * @param adminId
     * @return
     */
    List<String> routes(String adminId);

    /**
     * 权限列表
     *
     * @return
     */
    List<Permission> list();
}
