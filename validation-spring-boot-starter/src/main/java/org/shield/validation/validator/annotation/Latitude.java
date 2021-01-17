package org.shield.validation.validator.annotation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.shield.validation.validator.LatitudeValidator;

/**
 * 合法纬度
 *
 * @author zacksleo@gmail.com
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint(validatedBy = LatitudeValidator.class)
public @interface Latitude {

    String message() default "纬度不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
