
package org.shield.admin.service.impl;

import org.shield.admin.mapper.AdminRoleMapper;
import org.shield.admin.mapper.PermissionMapper;
import org.shield.admin.mapper.RolePermissionMapper;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.shield.admin.model.Permission;
import org.shield.admin.service.PermissionService;
import org.shield.crud.service.AbstractService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 权限
 *
 * @author zacksleo@gmail.com
 */
@Service
public class PermissionServiceImpl extends AbstractService<Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    private final static String ROOT = "ROOT";

    private final static List<Permission> EMPTY_ARRAY = new ArrayList<>();

    private final static List<String> EMPTY_STR_ARRAY = new ArrayList<>();

    @Override
    public List<String> routes(String adminId) {
        List<String> roleIds = adminRoleMapper.findRoleIds(adminId);
        if (roleIds.isEmpty()) {
            return EMPTY_STR_ARRAY;
        }
        List<String> permissionIds = rolePermissionMapper.findPermissionIds(roleIds);
        if (permissionIds.isEmpty()) {
            return EMPTY_STR_ARRAY;
        }
        return permissionMapper.findRoutesByIds(permissionIds);
    }

    public List<Permission> list() {
        Condition condition = new Condition(Permission.class);
        condition.orderBy("sort");
        List<Permission> permissions = permissionMapper.selectByCondition(condition);
        Map<String, List<Permission>> sets = new TreeMap<>();
        for (Permission permission : permissions) {
            String parentId = permission.getParentId();
            if (!sets.containsKey(parentId)) {
                sets.put(parentId, new LinkedList<Permission>());
            }
            sets.get(parentId).add(permission);
        }
        List<Permission> roots = sets.getOrDefault(ROOT, EMPTY_ARRAY);
        for (Permission root : roots) {
            root.setChildren(findChildren(sets, root));
        }
        return roots;
    }

    /**
     * 遍历子列表
     *
     * @param sets
     * @param node
     * @return
     */
    private List<Permission> findChildren(Map<String, List<Permission>> sets, Permission node) {
        List<Permission> children = sets.getOrDefault(node.getPermissionId(), EMPTY_ARRAY);
        if (children.isEmpty()) {
            return EMPTY_ARRAY;
        }
        for (Permission child : children) {
            child.setChildren(findChildren(sets, child));
        }
        return children;
    }
}
