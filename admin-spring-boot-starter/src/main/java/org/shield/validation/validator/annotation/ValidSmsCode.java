package org.shield.validation.validator.annotation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.shield.captcha.validator.ValidCaptchaValidator;

/**
 * 校验短信验证码是否合法
 *
 * @author zacksleo@gmail.com
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint(validatedBy = ValidCaptchaValidator.class)
public @interface ValidSmsCode {

    String message() default "短信验证码错误";

    /**
     * 验证码 key 和 value 的分隔符，默认为:
     */
    String delimiter() default ":";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

