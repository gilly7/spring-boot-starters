package org.shield.admin.controller;

import org.shield.admin.service.TokenService;
import org.shield.admin.vo.TokenVo;
import org.shield.admin.form.PasswordLoginForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;

/**
 * 令牌
 *
 * @author zacksleo@gmail.com
 */
@Api(tags = "登录")
@RestController("AdminConsoleTokenController")
@RequestMapping("tokens")
public class TokenController {

    @Autowired
    private TokenService<PasswordLoginForm> service;

    /**
     * 账号密码登录
     *
     * @param form
     * @return
     */
    @ApiOperation("获取令牌")
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public TokenVo create(@Valid @RequestBody PasswordLoginForm form) throws Exception {
        return service.create(form);
    }
}
