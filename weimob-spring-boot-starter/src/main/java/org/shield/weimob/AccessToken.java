package org.shield.weimob;

import feign.Param;
import feign.RequestLine;

/**
 * AccessToken
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
@SuppressWarnings("PMD")
public interface AccessToken {

    public final static String endpoint = "https://dopen.weimob.com";

    /**
     * AccessToken 返回内容
     */
    public class AccessTokenResponse {

        public String access_token;

        /**
         * Access_token 过期时间(秒)
         *
         */
        public Integer expires_in;
        /**
         * Access_token的类型目前只支持 bearer
         */

        public String token_type;

        /**
         * Refresh_token，可用来刷新access_token
         */
        public String refresh_token;

        /**
         * Refresh_token有效期, 默认7天
         */
        public Integer refresh_token_expires_in;

        /**
         * 授权范围
         */
        public String scope;

        /**
         * 微盟商户店铺id（新云）/商户公众号id（老云）
         */
        public String public_account_id;

        /**
         * 微盟商户id
         */
        public String business_id;
    }

    /**
     * 通过授权码获取 AccessToken
     *
     * @param code
     * @param clientId
     * @param clientSecret
     * @param redirectUri
     * @return
     */
    default AccessTokenResponse generateToken(String code, String clientId, String clientSecret, String redirectUri) {
        return generateToken(code, "authorization_code", clientId, clientSecret, redirectUri);
    }

    /**
     * 通过授权码获取 AccessToken
     *
     * @param code         授权码 code
     * @param grantType    授权类型，值为 authorization_code
     * @param clientId     创建应用时生成的 ClientID
     * @param clientSecret 创建应用时生成的 ClientSecret
     * @param redirectUri  回调地址，是在你选择好需要授权店铺后跳转的地址，请确保你能在该地址进行数据获取操作
     */
    @RequestLine("POST /fuwu/b/oauth2/token?code={code}&grant_type={grant_type}&client_id={grant_type}&client_secret={grant_type}")
    AccessTokenResponse generateToken(@Param("code") String code, @Param("grant_type") String grantType,
            @Param("client_id") String clientId, @Param("client_secret") String clientSecret,
            @Param("redirectUri") String redirectUri);


    /**
     * 通过refreshToken 获取 AccessToken
     *
     * @param refreshToken
     * @param clientId
     * @param clientSecret
     * @return
     */
    default AccessTokenResponse refreshToken(String refreshToken, String clientId, String clientSecret) {
        return refershToken(refreshToken, "refresh_token", clientId, clientSecret);
    }

    /**
     * 通过 refreshToken 获取 AccessToken
     *
     * @param refershToken 刷新令牌
     * @param grantType    授权类型，值为 refresh_token
     * @param clientId     创建应用时生成的 ClientID
     * @param clientSecret 创建应用时生成的 ClientSecret
     * @param redirectUri  回调地址，是在你选择好需要授权店铺后跳转的地址，请确保你能在该地址进行数据获取操作
     */
    @RequestLine("POST /fuwu/b/oauth2/token?refresh_token={refresh_token}&grant_type={grant_type}&client_id={client_id}&client_secret={client_secret}")
    AccessTokenResponse refershToken(@Param("refresh_token") String refreshToken, @Param("grant_type") String grantType,
            @Param("client_id") String clientId, @Param("client_secret") String clientSecret);

}
