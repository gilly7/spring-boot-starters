package org.shield.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zacksleo@gmail.com
 */
@ApiModel("验证码")
@Data
public class CaptchaVo {

    @ApiModelProperty("key")
    private String key;

    @ApiModelProperty("图片base64值")
    private String base64;
}
