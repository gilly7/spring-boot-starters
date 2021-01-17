package org.shield.captcha.autoconfigure;

import org.shield.captcha.service.CaptchaService;
import org.shield.captcha.service.impl.CaptchaServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zacksleo@gmail.com
 */
@Configuration
public class CaptchaAutoConfiguration {
    @Bean
    public CaptchaService captchaService() {
        return new CaptchaServiceImpl();
    }
}
