package org.shield.weimob;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * AccessToken
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
@SuppressWarnings("PMD")
public interface CoreClient {

    public final static String endpoint = "https://dopen.weimob.com/api/1_0/janus/core";

    /**
     * 登录返回内容
     */
    public class CoreLoginResponse {

        public final static String SUCCESS_CODE = "0";

        public String globalTicket;

        public Code code;
        public AppTicketData data;

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

        public class AppTicketData {

            /**
             * 使用appTicket 登录之后的有效时间（秒），与配置中的有效时间一致
             */
            public Long expires;

            /**
             * 一次性认证票据，有效时间5分钟
             */
            public String appTicket;
        }
    }

    /**
     * 登录接口（注册wid修改为 微盟用户获取登录票据）
     * <ul>
     * <li>用户点击微盟平台应用时，商户需要使用当前的用户openUserId登录，并将返回值的appTicket拼接到微盟前端参数</li>
     * <li>登录接口需要商户严格验证当前用户的登录状态</li>
     * <li>使用登录接口后，会自动注册微盟用户</li>
     * <li>商户pid+appClientId+openUserId与微盟用户唯一对应</li>
     * </p>
     *
     * <p>
     * <ul>
     * <li>登录接口的中的用户信息，会作为当前用户信息展示给前端。</li>
     * <li>手机号仅作为展示信息，不作为微盟身份标识，即无法通过手机号+验证码的方式登录。</li>
     * <li>如果需要手机号做为身份认证，需要额外绑定openUserId和手机号。</li>
     * </ul>
     * </p>
     *
     * @param accessToken AccessToken
     * @param openUserId  用户在第三方身份标识ID
     * @param nickname    用户名
     * @param headUrl     头像地址
     * @param phone       联系方式，注意格式11位数字
     * @return
     */
    @RequestLine("POST /login?accesstoken={accessToken}")
    @Headers("Content-Type: application/json")
    CoreLoginResponse login(@Param("accessToken") String accessToken, @Param("openUserId") String openUserId,
            @Param("nickname") String nickname, @Param("headurl") String headUrl, @Param("telephone") String phone);
}
