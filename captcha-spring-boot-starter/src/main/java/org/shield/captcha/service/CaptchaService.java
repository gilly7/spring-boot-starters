package org.shield.captcha.service;

import org.shield.captcha.model.Captcha;

/**
 * @author zacksleo@gmail.com
 */
public interface CaptchaService {

    /**
     * 生成验证码
     *
     * @return
     */
    Captcha create();
}
