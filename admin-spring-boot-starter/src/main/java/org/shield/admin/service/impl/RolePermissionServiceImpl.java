
package org.shield.admin.service.impl;

import org.shield.admin.mapper.RolePermissionMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.shield.admin.model.RolePermission;
import org.shield.admin.form.RolePermissionForm;
import org.shield.admin.service.RolePermissionService;
import org.shield.crud.service.AbstractService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 角色的权限
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
@Service
public class RolePermissionServiceImpl extends AbstractService<RolePermission> implements RolePermissionService {
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<String> update(String roleId, RolePermissionForm form) {
        Condition condition = new Condition(RolePermission.class);
        condition.createCriteria().andEqualTo("roleId", roleId);
        rolePermissionMapper.deleteByCondition(condition);
        for (String permissionId : form.getPermissions()) {
            RolePermission record = new RolePermission();
            record.setRoleId(roleId);
            record.setPermissionId(permissionId);
            create(record);
        }
        return form.getPermissions();
    }

    @Override
    public List<String> list(String roleId) {
        Condition condition = new Condition(RolePermission.class);
        condition.createCriteria().andEqualTo("roleId", roleId);
        condition.selectProperties("permissionId");
        return findByCondition(condition).stream().map(e -> e.getPermissionId()).collect(Collectors.toList());
    }
}
