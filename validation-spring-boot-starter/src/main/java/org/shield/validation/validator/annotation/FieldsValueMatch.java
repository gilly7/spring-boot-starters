package org.shield.validation.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.shield.validation.validator.FieldsValueMatchValidator;

/**
 * @author zacksleo <zacksleo@gmail.com>
 */
@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {

    String message() default "Fields value dont't match!";

    String field();

    String fieldMatch();

    /**
     * 字段匹配时，是否验证失败
     */
    boolean failOnMatch() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }
}
