package org.shield.admin.form;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zacksleo <zacksleo@gmail.com>
 */
@Data
public class RolePermissionForm {
    /**
     * 权限编号列表
     */
    @ApiModelProperty("权限编号列表")
    @NotEmpty
    List<String> permissions;
}
