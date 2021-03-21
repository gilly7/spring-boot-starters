package org.shield.admin.form;

import javax.validation.constraints.NotBlank;
import org.shield.validation.validator.annotation.FieldsValueMatch;
import org.shield.validation.validator.annotation.Password;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zacksleo@gmail.com
 */
@ApiModel("修改密码")
@Data
@FieldsValueMatch.List({@FieldsValueMatch(field = "oldPassword", fieldMatch = "newPassword", failOnMatch = true,
        message = "新密码不能与原密码相同")})
public class PasswordChangeForm {

    /**
     * 手机号码
     */
    @ApiModelProperty("原密码")
    @NotBlank(message = "请输入原密码")
    private String oldPassword;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotBlank(message = "请输入密码")
    @Password
    private String newPassword;
}
