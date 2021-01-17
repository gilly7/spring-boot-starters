package org.shield.validation.validator.annotation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.shield.validation.validator.LongitudeValidator;

/**
 * 合法经度
 *
 * @author zacksleo@gmail.com
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint(validatedBy = LongitudeValidator.class)
public @interface Longitude {

    String message() default "经纬不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
