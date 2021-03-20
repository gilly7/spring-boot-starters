package org.shield.admin.form;

import org.shield.mybatis.form.PageableQuery;
import org.shield.service.annotation.EmptyShouldBeNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zacksleo <zacksleo@gmail.com>
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
public class AdminQueryForm extends PageableQuery {
    /**
     * 编号
     */
    @ApiModelProperty("编号")
    @EmptyShouldBeNull
    private String adminId;
    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    @EmptyShouldBeNull
    private String name;
    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    @EmptyShouldBeNull
    private String phone;
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @EmptyShouldBeNull
    private String username;
    /**
     * 是否有效: 1有效 0无效
     */
    @ApiModelProperty("是否有效: 1有效 0无效")
    private Boolean isActive;
}
