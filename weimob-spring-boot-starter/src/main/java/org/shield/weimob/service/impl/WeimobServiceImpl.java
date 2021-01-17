
package org.shield.weimob.service.impl;

import org.shield.weimob.autoconfigure.WeimobOauth2ClientProperties;
import org.shield.weimob.service.AccessTokenService;
import org.shield.weimob.service.WeimobService;
import org.shield.weimob.AccessToken;
import org.shield.weimob.AccessToken.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import feign.Feign;
import feign.Logger.Level;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

/**
 * Weimob
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
@Service
public class WeimobServiceImpl implements WeimobService {

    @Autowired
    WeimobOauth2ClientProperties props;

    @Autowired
    AccessTokenService accessTokenService;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public String getAccessTokenByCode(String code) {
        AccessToken weimo =
                Feign.builder().decoder(new JacksonDecoder()).target(AccessToken.class, AccessToken.endpoint);
        AccessTokenResponse response =
                weimo.generateToken(code, props.getClientId(), props.getClientSecret(), props.getRedirectUri());
        accessTokenService.setAccessToken(response.access_token);
        accessTokenService.setRefreshToken(response.refresh_token);
        return response.access_token;
    }

    @Override
    public String getAccessTokenByRefreshToken() {
        return getAccessTokenByRefreshToken(accessTokenService.getRefreshToken());
    }

    public String getAccessTokenByRefreshToken(String refreshToken) {
        AccessToken weimo = Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder())
                .logger(new Slf4jLogger()).logLevel(Level.FULL).target(AccessToken.class, AccessToken.endpoint);
        AccessTokenResponse response =
                weimo.refreshToken(accessTokenService.getRefreshToken(), props.getClientId(), props.getClientSecret());
        accessTokenService.setRefreshToken(response.refresh_token);
        return response.access_token;
    }
}
