package org.shield.admin.form;

import javax.validation.constraints.NotEmpty;
import org.shield.validation.validator.annotation.Phone;
import org.shield.validation.validator.annotation.ValidSmsCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 密码重置表单
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
@Data
public class PasswordResetForm {

    @ApiModelProperty(value = "手机号码", example = "18888888888")
    @NotEmpty
    @Phone
    private String phone;

    @ApiModelProperty(value = "短信验证码", example = "174561")
    @NotEmpty
    @ValidSmsCode
    private String verifyCode;
}
