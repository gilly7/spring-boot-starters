package org.shield.weimob.service;

/**
 * AccessToken
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
public interface AccessTokenService {

    /**
     * 获取 AccessToken
     *
     * @return
     */
    String getAccessToken();

    /**
     * 设置 AccessToken
     *
     * @param accessToken
     * @return
     */
    String setAccessToken(String accessToken);


    /**
     * 获取 AccessToken
     *
     * @return
     */
    String getRefreshToken();

    /**
     * 设置 RefreshToken
     *
     * @param refreshToken
     * @return
     */
    String setRefreshToken(String refreshToken);
}
