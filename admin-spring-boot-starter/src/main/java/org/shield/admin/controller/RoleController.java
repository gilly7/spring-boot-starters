package org.shield.admin.controller;

import org.shield.admin.model.Role;
import org.shield.mybatis.form.PageableQuery;
import org.shield.admin.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色
 *
 * @author zacksleo@gmail.com
 */
@Api(tags = "角色")
@RestController("AdminConsoleRoleController")
@RequestMapping("roles")
public class RoleController {

    @Autowired
    private RoleService service;

    @ApiOperation("创建")
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Role create(@RequestBody Role role) {
        return service.create(role);
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.deleteById(id);
    }

    @ApiOperation("查询")
    @GetMapping
    public PageInfo<Role> list(PageableQuery form) {
        Page<Role> page = PageHelper.startPage(form);
        service.findAll();
        return PageInfo.of(page);
    }

    @ApiOperation("更新")
    @PutMapping("/{id}")
    public Role update(@PathVariable String id, @RequestBody Role role) {
        return service.update(id, role);
    }

    @ApiOperation("详情")
    @GetMapping("/{id}")
    public Role view(@PathVariable String id) {
        return service.findById(id);
    }
}