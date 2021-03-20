package org.shield.admin.enums;

/**
 * BaseEnum
 *
 * @author zacksleo@gmail.com
 */
public interface BaseEnum<T> {

    /**
     * 值
     *
     * @return
     */
    T value();

    /**
     * 文字描述
     *
     * @return
     */
    String description();
}
