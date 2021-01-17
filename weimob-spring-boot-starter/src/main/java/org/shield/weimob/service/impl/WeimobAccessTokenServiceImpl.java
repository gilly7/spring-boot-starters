
package org.shield.weimob.service.impl;

import org.shield.weimob.autoconfigure.WeimobOauth2ClientProperties;
import org.shield.weimob.service.AccessTokenService;
import org.shield.weimob.service.WeimobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 微盟 AccessToken
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
@Service
public class WeimobAccessTokenServiceImpl implements AccessTokenService {

    private static final Logger log = LoggerFactory.getLogger(WeimobAccessTokenServiceImpl.class);

    @Autowired
    WeimobOauth2ClientProperties props;

    @Autowired
    WeimobService weimobService;

    /**
     * 原 accessToken 有效期为 7200 (2小时)
     */
    @Override
    @Cacheable(value = "WeimobAccessToken@7000", key = "':' + #root.targetClass.getName()")
    public String getAccessToken() {
        return weimobService.getAccessTokenByRefreshToken();
    }

    @CachePut(value = "WeimobAccessToken@7000", key = "':' + #root.targetClass.getName()")
    public String setAccessToken(String accessToken) {
        return accessToken;
    }

    /**
     * RefreshToken 过期，需要通知管理员进行重新授权
     *
     * @todo 通知管理员进行重新授权
     */
    @Cacheable(value = "WeimobRefreshToken@600000", key = "':' + #root.targetClass.getName()")
    public String getRefreshToken() {
        String refreshToken = props.getInitRefreshToken();
        log.debug("初始化refreshToken={}", refreshToken);
        return refreshToken;
    }

    /**
     * 原 refresh_token 有效期默认为7天
     */
    @CachePut(value = "WeimobRefreshToken@600000", key = "':' + #root.targetClass.getName()")
    public String setRefreshToken(String refreshToken) {
        log.info("setRefreshToken={}", refreshToken);
        return refreshToken;
    }
}
