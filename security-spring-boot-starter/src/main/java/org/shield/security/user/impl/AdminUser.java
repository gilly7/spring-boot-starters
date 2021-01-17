package org.shield.security.user.impl;

import org.shield.security.user.AbstractUser;

/**
 * 管理员用户
 *
 * @author zacksleo@gmail.com
 */
public class AdminUser extends AbstractUser {

    private String catalog = "AdminUser";

    @Override
    public String getCatalog() {
        return catalog;
    }
}
