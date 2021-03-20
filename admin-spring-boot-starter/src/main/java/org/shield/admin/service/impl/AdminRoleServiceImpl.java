
package org.shield.admin.service.impl;

import org.shield.admin.mapper.AdminRoleMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.shield.admin.model.AdminRole;
import org.shield.admin.form.AdminRoleForm;
import org.shield.admin.service.AdminRoleService;
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
public class AdminRoleServiceImpl extends AbstractService<AdminRole> implements AdminRoleService {
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public List<String> update(String adminId, AdminRoleForm form) {
        Condition condition = new Condition(AdminRole.class);
        condition.createCriteria().andEqualTo("adminId", adminId);
        adminRoleMapper.deleteByCondition(condition);
        for (String roleId : form.getRoles()) {
            AdminRole record = new AdminRole();
            record.setAdminId(adminId);
            record.setRoleId(roleId);
            create(record);
        }
        return form.getRoles();
    }

    @Override
    public List<String> list(String adminId) {
        Condition condition = new Condition(AdminRole.class);
        condition.createCriteria().andEqualTo("adminId", adminId);
        condition.selectProperties("roleId");
        return findByCondition(condition).stream().map(e -> e.getRoleId()).collect(Collectors.toList());
    }
}
