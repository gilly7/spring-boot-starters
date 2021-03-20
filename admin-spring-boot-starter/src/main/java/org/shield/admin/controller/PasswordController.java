package org.shield.admin.controller;

import javax.validation.Valid;
import org.shield.admin.form.PasswordChangeForm;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zacksleo <zacksleo@gmail.com>
 */
@Api(tags = "密码管理")
@RestController("AdminConsolePasswordController")
@RequestMapping("admins/passwords")
@Slf4j
public class PasswordController {

    @ApiOperation("修改密码")
    @PutMapping("/change")
    public void change(@RequestHeader("auth-userId") String userId, @Valid @RequestBody PasswordChangeForm form) {
        log.info("userId={}, form={}", userId, form);
    }

    @ApiOperation("重置密码")
    public void reset() {

    }
}
