package org.shield.weimob.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zacksleo@gmail.com
 */
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.weimob")
public class WeimobOauth2ClientProperties {

    /**
     * ClientId
     */
    String clientId;

    /**
     * ClientSecret
     */
    String clientSecret;

    /**
     * 回调地址
     */
    String redirectUri;

    /**
     * 初始化刷新令牌
     */
    String initRefreshToken;

    /**
     * 商户 PID
     */
    String publicAccountId;

    /**
     * 登录回调
     */
    String loginCallbackUrl;

    /**
     * 用户数量级（默认值1000000）
     */
    Integer numberScale;

    /**
     * 过期时间（s）（默认值1296000s 15天）
     */
    Long expires;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getInitRefreshToken() {
        return initRefreshToken;
    }

    public void setInitRefreshToken(String initRefreshToken) {
        this.initRefreshToken = initRefreshToken;
    }

    public String getPublicAccountId() {
        return publicAccountId;
    }

    public void setPublicAccountId(String publicAccountId) {
        this.publicAccountId = publicAccountId;
    }

    public String getLoginCallbackUrl() {
        return loginCallbackUrl;
    }

    public void setLoginCallbackUrl(String loginCallbackUrl) {
        this.loginCallbackUrl = loginCallbackUrl;
    }

    public Integer getNumberScale() {
        return numberScale;
    }

    public void setNumberScale(Integer numberScale) {
        this.numberScale = numberScale;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }
}
