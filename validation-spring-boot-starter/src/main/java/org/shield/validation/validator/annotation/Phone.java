package org.shield.validation.validator.annotation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.shield.validation.validator.PhoneValidator;

/**
 * 手机号格式校验
 *
 * @author zacksleo@gmail.com
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint(validatedBy = PhoneValidator.class)
public @interface Phone {

    String message() default "手机号码格式不正确";

    /**
     * 正则表达式
     */
    String regexp() default "^1[3-9]\\d{9}$";

    /**
     * 字段为空时，是否验证失败
     */
    boolean failOnEmpty() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
