package org.shield.validation.validator;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.shield.validation.validator.annotation.ValidRegex;

/**
 *
 * 验证正则表达式是否合法
 *
 * @author zacksleo
 */
public class ValidRegexValidator implements ConstraintValidator<ValidRegex, Object> {

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        if (o == null) {
            return false;
        }
        String regex = o.toString();
        try {
            Pattern.compile(regex);
        } catch (PatternSyntaxException e) {
            return false;
        }
        return true;
    }
}