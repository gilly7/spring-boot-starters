package org.shield.admin.service;

import org.shield.admin.form.PasswordChangeForm;

/**
 * @author zacksleo <zacksleo@gmail.com>
 */
public interface PasswordService {

    /**
     * 修改密码
     *
     * @param accountId
     * @param form
     */
    void change(String accountId, PasswordChangeForm form);
}
