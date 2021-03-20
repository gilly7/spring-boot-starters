package org.shield.admin.vo;

import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zacksleo <zacksleo@gmail.com>
 */
@Data
@ApiModel
public class TokenVo {

    @ApiModelProperty(value = "令牌")
    private String token;

    @ApiModelProperty(value = "姓名", example = "张明")
    private String name;

    @ApiModelProperty(value = "权限列表")
    private List<String> premissions;
}
