package org.shield.mybatis.validator.annotation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.shield.mybatis.validator.EnableUniqueValidator;

/**
 * 开启字段唯检查，配合 @Unique 一起使用
 *
 * <pre>
 *   @EnableUnique(table = "merchant_info")
 *   @EnableUnique(table = "merchant_info", sn = "phone")
 * </pre>
 *
 * @author zacksleo@gmail.com
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint(validatedBy = EnableUniqueValidator.class)
public @interface EnableUnique {

    /**
     * 数据表
     *
     * @return
     */
    String table() default "";

    /**
     * 更新时使用的编号
     *
     * @return
     */
    String sn() default "";

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
