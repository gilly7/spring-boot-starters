package org.shield.captcha.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.shield.redis.service.CacheService;
import org.shield.captcha.validator.annatation.ValidCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 校验验证码是否合法
 *
 * @author zacksleo@gmail.com
 */
@Component
public class ValidCaptchaValidator implements ConstraintValidator<ValidCaptcha, String> {

    @Autowired
    private CacheService cache;

    private String delimiter;

    /**
     * 分割后的字符串数组应大小2
     */
    private final static int VALID_LEN = 2;

    @Override
    public void initialize(ValidCaptcha validator) {
        this.delimiter = validator.delimiter();
    }

    @Override
    public boolean isValid(String verifyCode, ConstraintValidatorContext context) {
        if (verifyCode.isEmpty()) {
            return failValidate(context, "验证码不能为空");
        }
        String[] arr = verifyCode.split(delimiter);
        if (arr.length != VALID_LEN) {
            return failValidate(context, "验证码格式不正确");
        }
        String validCode = cache.get(arr[0]);
        if (validCode == null) {
            return failValidate(context, "验证码已失效，请重新获取");
        }
        // 验证码只能使用一次
        cache.delete(arr[0]);
        if (!validCode.equals(arr[1].toLowerCase())) {
            return failValidate(context, "验证码不正确，请重新输入");
        }
        return true;
    }

    private boolean failValidate(ConstraintValidatorContext context, String errTxt) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errTxt).addConstraintViolation();
        return false;
    }
}
