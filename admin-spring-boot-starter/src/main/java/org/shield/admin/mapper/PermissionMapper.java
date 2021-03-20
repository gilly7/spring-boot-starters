package org.shield.admin.mapper;

import java.util.List;
import org.shield.admin.model.Permission;
import org.shield.mybatis.mapper.Mapper;

/**
 * Permission Mapper
 *
 * @author zacksleo@gmail.com
 */
public interface PermissionMapper extends Mapper<Permission> {

    /**
     * 根据权限编号列表查询所有相关路由权限
     *
     * @param permissionIds
     * @return
     */
    List<String> findRoutesByIds(List<String> permissionIds);
}
