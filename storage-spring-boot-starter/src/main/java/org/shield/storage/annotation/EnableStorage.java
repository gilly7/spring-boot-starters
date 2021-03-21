package org.shield.storage.annotation;

import java.lang.annotation.*;

/**
 * @author zacksleo <zacksleo@gmail.com>
 *
 * 启用文件上传
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableStorage {
}
