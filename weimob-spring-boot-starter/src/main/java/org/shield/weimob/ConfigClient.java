package org.shield.weimob;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 商户配置
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
@SuppressWarnings("PMD")
public interface ConfigClient {

    public final static String endpoint = "https://dopen.weimob.com/api/1_0/janus/config";

    /**
     * 登录返回内容
     */
    public class ConfigResponse {

        public final static String SUCCESS_CODE = "0";

        public String globalTicket;

        public Code code;
        public Boolean data;

        /**
         * 0 成功 SUCCESS -1 系统错误 ERROR
         *
         * 80011000000001 参数无效 INVALID_PARAMS
         *
         * 80011000000002 服务错误 SERVER_ERROR
         *
         * 80011002000101 登录失败 LOGIN_FAIL
         *
         * 80011002000102 授权成为微盟用户失败 GET_WID_FAIL
         *
         * 80011003000101 未设置登录回调 INVALID_CALLBACK
         *
         * 80011003000102 获取开放平台授权信息,请重试 GET_TOKEN_FAIL
         */
        public class Code {
            public String errcode;
            public String errmsg;
        }
    }

    /**
     * 设置商户配置（一个商户clientId只有一个配置）
     *
     * 在商户使用H5通用免登前，需要商户进行初始化设置
     *
     * @param accessToken      AccessToken
     * @param loginCallbackUrl 登录回调
     * @param numberScale      用户数量级（默认值1000000）
     * @param expires          过期时间（s）（默认值1296000s 15天）
     * @return
     */
    @RequestLine("POST /setConfig?accesstoken={accessToken}")
    @Headers("Content-Type: application/json")
    ConfigResponse setConfig(@Param("accessToken") String accessToken,
            @Param("loginCallbackUrl") String loginCallbackUrl, @Param("numberScale") Integer numberScale,
            @Param("expires") Long expires);
}
