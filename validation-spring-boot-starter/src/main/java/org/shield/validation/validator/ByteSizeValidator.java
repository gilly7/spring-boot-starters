package org.shield.validation.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.shield.validation.validator.annotation.ByteSize;

/**
 * @author zacksleo <zacksleo@gmail.com>
 *
 *         字符长度校验
 */
public class ByteSizeValidator implements ConstraintValidator<ByteSize, String> {

    /**
     * 最小字符长度
     */
    public int minByteSize;

    /**
     * 最小字符长度
     */
    public int maxByteSize;

    public void initialize(ByteSize byteSize) {
        minByteSize = byteSize.min();
        maxByteSize = byteSize.max();
    }

    @Override
    public boolean isValid(String stringValue, ConstraintValidatorContext context) {
        if (stringValue == null) {
            return minByteSize < 1;
        }
        int charSize = stringValue.getBytes().length;
        return charSize > minByteSize && charSize < maxByteSize;
    }
}
