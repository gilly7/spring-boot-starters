package org.shield.admin.controller;

import java.util.List;
import javax.validation.Valid;
import org.shield.admin.form.RolePermissionForm;
import org.shield.admin.service.RolePermissionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色的权限
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
@Api(tags = "角色的权限")
@RestController("AdminConsoleRolePermissionController")
@RequestMapping("roles/{roleId}/permissions")
public class RolePermissionController {

    @Autowired
    private RolePermissionService service;

    @ApiOperation("查询")
    @GetMapping
    public List<String> list(@PathVariable("roleId") String roleId) {
        return service.list(roleId);
    }

    @ApiOperation("更新")
    @PutMapping
    public List<String> update(@PathVariable("roleId") String roleId, @Valid @RequestBody RolePermissionForm form) {
        return service.update(roleId, form);
    }
}
