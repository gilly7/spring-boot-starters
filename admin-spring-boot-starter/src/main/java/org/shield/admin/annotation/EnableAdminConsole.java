package org.shield.admin.annotation;

import java.lang.annotation.*;

/**
 * @author zacksleo <zacksleo@gmail.com>
 *
 * 启用系统管理
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableAdminConsole {
}
