package org.shield.captcha.model;

import cn.hutool.captcha.AbstractCaptcha;

/**
 * @author zacksleo@gmail.com
 */
public class Captcha {

    /**
     * Key
     */
    private String key;

    private AbstractCaptcha captcha;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public AbstractCaptcha getCaptcha() {
        return captcha;
    }

    public void setCaptcha(AbstractCaptcha captcha) {
        this.captcha = captcha;
    }
}
