package org.shield.admin.service;

import java.util.List;

import javax.validation.Valid;
import org.shield.admin.model.Admin;
import org.shield.admin.form.AdminForm;
import org.shield.admin.form.AdminQueryForm;
import org.shield.admin.vo.AdminVo;
import org.shield.validation.validator.annotation.OnUpdate;
import org.shield.crud.service.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 管理员
 *
 * @author zacksleo@gmail.com
 */
@Validated
public interface AdminService extends Service<Admin> {
    /**
     * 创建管理员
     *
     * @param form
     * @return
     */
    Admin create(AdminForm form);

    /**
     * 更新管理员
     *
     * @param form
     * @return
     */
    @Validated(OnUpdate.class)
    AdminForm update(@Valid AdminForm form);

    /**
     * 列表查询
     *
     * @param form
     * @return
     */
    List<Admin> list(AdminQueryForm form);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    AdminVo view(String id);
}
