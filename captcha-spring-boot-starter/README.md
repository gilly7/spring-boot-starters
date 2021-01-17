# Captcha Spring Boot Starter

该组件用于生成和验证验证码

## 使用

### 生成

```java

    @Autowired
    CaptchaService service;

    @ApiOperation("获取验证码")
    @PostMapping
    public CaptchaVo create() {
        Captcha captcha = service.create();
        CaptchaVo vo = new CaptchaVo();
        vo.setBase64(captcha.getCaptcha().getImageBase64Data());
        vo.setKey(captcha.getKey());
        return vo;
    }
```

### 验证

```java

@Data
public class PasswordLoginForm {

    @NotBlank(message = "请输入验证码")
    @ValidCaptcha
    private String verifyCode;
}

```
