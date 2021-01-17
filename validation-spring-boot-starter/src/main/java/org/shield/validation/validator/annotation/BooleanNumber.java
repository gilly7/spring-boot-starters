package org.shield.validation.validator.annotation;

import java.lang.annotation.*;
import javax.validation.Payload;
import javax.validation.constraints.Size;

/**
 * 布尔数值： 只能为 0 或者 1
 *
 * @author zacksleo@gmail.com
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Size(min = 0, max = 1)
public @interface BooleanNumber {

    String message() default "字段不合法，只能为 0 或 1";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
