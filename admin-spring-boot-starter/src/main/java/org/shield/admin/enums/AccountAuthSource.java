package org.shield.admin.enums;

import java.util.Objects;

/**
 * 账号认证来源
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
public enum AccountAuthSource implements BaseEnum<Integer> {


    /**
     * 用户名
     */
    USERNAME(0, "用户名"),
    /**
     * 手机号
     */
    PHONE(10, "手机号"),
    /**
     * 邮箱
     */
    EMAIL(20, "邮箱"),
    /**
     * 微信
     */
    WECHAT(30, "微信"),
    /**
     * QQ
     */
    QQ(40, "QQ"),
    /**
     * Apple
     */
    APPLE(50, "Apple");

    public static final String USERNAME_VAL = "0";
    public static final String PHONE_VAL = "10";
    public static final String EMAIL_VAL = "20";
    public static final String WECHAT_VAL = "30";
    public static final String QQ_VAL = "40";
    public static final String APPLE_VAL = "50";

    private Integer value;
    private String description;

    AccountAuthSource(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static AccountAuthSource fromValue(Integer value) {
        for (AccountAuthSource statusEnum : AccountAuthSource.values()) {
            if (Objects.equals(value, statusEnum.value())) {
                return statusEnum;
            }
        }
        throw new IllegalArgumentException();
    }
}
