package org.shield.admin.form;

import javax.validation.constraints.NotBlank;
import org.shield.captcha.validator.annatation.ValidCaptcha;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zacksleo@gmail.com
 */
@ApiModel("密码登录")
@Data
public class PasswordLoginForm {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", example = "username", required = true)
    @NotBlank(message = "请输入用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotBlank(message = "请输入密码")
    private String password;

    /**
     * 验证码
     */
    @ApiModelProperty("验证码")
    @NotBlank(message = "请输入验证码")
    @ValidCaptcha
    private String verifyCode;
}
