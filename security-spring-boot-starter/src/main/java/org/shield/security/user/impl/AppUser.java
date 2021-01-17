package org.shield.security.user.impl;

import org.shield.security.user.AbstractUser;

/**
 * App用户
 *
 * @author zacksleo@gmail.com
 */
public class AppUser extends AbstractUser {

    private String catalog = "AppUser";

    @Override
    public String getCatalog() {
        return catalog;
    }
}
