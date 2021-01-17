package org.shield.validation.validator.annotation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.shield.validation.validator.PasswordValidator;

/**
 * 密码校验
 *
 * @author zacksleo@gmail.com
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Repeatable(Passwords.class)
public @interface Password {

    String message() default "密码应为小写字母、大写字母、数字、特殊符号的两种及以上,8-20个字符";

    /**
     * 正则表达式
     */
    String regexp() default "^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{8,20}$";

    /**
     * 字段为空时，是否验证失败
     */
    boolean failOnEmpty() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
