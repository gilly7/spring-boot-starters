package org.shield.validation.validator;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.shield.validation.validator.annotation.ValidIntegerEnum;
import org.shield.validation.contract.IntegerEnum;

/**
 *
 * 手机号校验
 *
 * @author zacksleo
 */
public class IntegerEnumValidator implements ConstraintValidator<ValidIntegerEnum, Integer> {

    private Set<Integer> values = new HashSet<>();

    private boolean showAllowValues;

    @Override
    public void initialize(ValidIntegerEnum validEnum) {

        Class<?> enumClazz = validEnum.value();
        Object[] enumConstants = enumClazz.getEnumConstants();
        if (null == enumConstants) {
            return;
        }
        for (Object object : enumConstants) {
            values.add(((IntegerEnum) object).value());
        }
        showAllowValues = validEnum.showAllowValues();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (values.contains(value)) {
            return true;
        }
        if (showAllowValues) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("字段取值不合法: " + values.toString()).addConstraintViolation();
        }
        return false;
    }
}
