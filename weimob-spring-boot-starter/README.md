# 微盟组件

## 需要配置 redis 连接信息

```yaml
spring:
  redis:
    host: redis.dev
  security:
    oauth2:
      client:
        registration:
          #微盟
          weimob:
            client-id: 6B91F1E962D65DDD5E4A557B32A29FBF
            client-secret: 740B94B21618B36FF61CC41E04748178
            redirect-uri: http://sandbox-iot.yiwuhaoyun.com/api/qjmotor/store/oauth2/code/weimob
            # 初始刷新令牌
            init-refresh-token: 8982870c-e326-415d-8486-c0f799094197
            # 商户 pid
            public-account-id: 100000894507
            # 登录超时回调
            loginCallbackUrl: http://sandbox-iot.yiwuhaoyun.com/api/app/qjmotor/store/weimob/login
            # 会员规模
            numberScale: 1000000
            # 超时时间
            expires: 25920000
            # Token 自动刷新周期, 毫秒数
            tokenRefreshCycle: 20000
```
