package org.shield.validation.validator.annotation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.shield.validation.validator.ValidRegexValidator;

/**
 * 合法的正则表达式
 *
 * @author zacksleo@gmail.com
 */
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint(validatedBy = ValidRegexValidator.class)
public @interface ValidRegex {

    String message() default "正则表达式不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
