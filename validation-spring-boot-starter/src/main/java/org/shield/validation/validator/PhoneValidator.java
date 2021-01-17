package org.shield.validation.validator;

import javax.validation.ConstraintValidator;
import org.shield.validation.validator.annotation.Phone;

/**
 *
 * 手机号校验
 *
 * @author zacksleo
 */
public class PhoneValidator extends AbstractRegexValidator implements ConstraintValidator<Phone, Object> {

    @Override
    public void initialize(Phone validator) {
        setRegexp(validator.regexp());
        setFailOnEmpty(validator.failOnEmpty());
    }
}
