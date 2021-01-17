package org.shield.captcha.validator.annatation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.shield.captcha.validator.ValidCaptchaValidator;

/**
 * 校验验证码是否合法
 *
 * @author zacksleo@gmail.com
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint(validatedBy = ValidCaptchaValidator.class)
public @interface ValidCaptcha {

    String message() default "验证码错误";

    /**
     * 验证码 key 和 value 的分隔符，默认为:
     */
    String delimiter() default ":";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

