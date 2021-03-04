package org.shield.validation.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.shield.validation.validator.annotation.CharLength;

/**
 * @author zacksleo <zacksleo@gmail.com>
 *
 *         字符长度校验
 */
public class CharLengthValidator implements ConstraintValidator<CharLength, String> {

    /**
     * 最小字符长度
     */
    public int minCharLength;

    /**
     * 最小字符长度
     */
    public int maxCharLength;

    public void initialize(CharLength charLength) {
        this.minCharLength = charLength.min();
        this.maxCharLength = charLength.max();
    }

    @Override
    public boolean isValid(String stringValue, ConstraintValidatorContext context) {
        if (stringValue == null) {
            return minCharLength < 1;
        }
        int charSize = stringValue.length();
        return charSize >= minCharLength && charSize <= maxCharLength;
    }
}
