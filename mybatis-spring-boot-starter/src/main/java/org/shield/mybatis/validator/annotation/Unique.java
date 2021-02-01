package org.shield.mybatis.validator.annotation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.shield.mybatis.validator.UniqueValidator;

/**
 * 字段唯一
 *
 *  <p>支持多重用法：</p>
 *  <ul>
 *  <li>1. 参数/属性级别的注解 `@Unique(table = "merchant_info", field = "name", message = "商户名已存在")`</li>
 *  <li>2. 类级别的注解 `@Unique(table = "merchant_info", field = "phone", message = "手机号已存在", sn = "merchantId")`</li>
 *  <li>只在类级别注解，才支持 sn 配置，sn 为更新时使用的唯一查找字段，驼峰格式，用于更新时的唯一逻辑判断 </li>
 *  <li> 3. 支持重复注解，如</li>
 *  <pre>
 *   @Unique(table = "merchant_info", field = "phone", message = "手机号已存在", sn = "merchantId")
 *   @Unique(table="merchant_info", field="name", message="商户名已存在", sn="merchantId")
 *  </pre>
 *
 * @author zacksleo@gmail.com
 */
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint(validatedBy = UniqueValidator.class)
@Repeatable(Uniques.class)
public @interface Unique {

    /**
     * 数据表
     *
     * @return
     */
    String table();

    /**
     * 字段
     */
    String field();

    /**
     * 更新时使用的编号
     *
     * @return
     */
    String sn() default "";

    String message() default "字段内容重复";

    /**
     * 排查软删除的数据
     */
    boolean exceptDeleted() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
