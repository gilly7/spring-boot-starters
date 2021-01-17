package org.shield.validation.validator;

import javax.validation.ConstraintValidator;
import org.shield.validation.validator.annotation.Password;

/**
 *
 * 正则通用验证
 *
 * @author zacksleo
 */
public class PasswordValidator extends AbstractRegexValidator implements ConstraintValidator<Password, Object> {

    @Override
    public void initialize(Password validator) {
        setRegexp(validator.regexp());
        setFailOnEmpty(validator.failOnEmpty());
    }
}
