package org.shield.weimob.service;

/**
 * Weimob
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
public interface WeimobService {

    /**
     * 通过 code 获取 accessToken
     *
     * @param code
     * @return
     */
    String getAccessTokenByCode(String code);

    /**
     * 通过 RefreshToken 获取 accessToken
     *
     * @param refreshToken
     * @return
     */
    String getAccessTokenByRefreshToken();
}
