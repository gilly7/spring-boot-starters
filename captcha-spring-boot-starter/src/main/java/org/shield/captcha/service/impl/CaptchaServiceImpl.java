package org.shield.captcha.service.impl;

import org.shield.redis.service.CacheService;
import org.shield.captcha.model.Captcha;
import org.shield.captcha.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.core.util.IdUtil;

/**
 * @author zacksleo@gmail.com
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    CacheService service;

    public Captcha create() {
        AbstractCaptcha captcha = CaptchaUtil.createLineCaptcha(75, 40, 4, 60);
        String uid = IdUtil.simpleUUID();
        service.set(uid, captcha.getCode(), 600);
        Captcha model = new Captcha() {
            {
                setKey(uid);
                setCaptcha(captcha);
            }
        };
        return model;
    }
}
