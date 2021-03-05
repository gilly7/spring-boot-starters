package org.shield.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.shield.validation.validator.annotation.FieldsValueMatch;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author zacksleo <zacksleo@gmail.com>
 */
public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

    private String field;

    private String fieldMatch;

    private boolean failOnMatch;

    public void initialize(FieldsValueMatch fieldMatchValue) {
        field = fieldMatchValue.field();
        fieldMatch = fieldMatchValue.fieldMatch();
        failOnMatch = fieldMatchValue.failOnMatch();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
        boolean isMatch = isMatch(fieldValue, fieldMatchValue);
        return failOnMatch ? !isMatch : isMatch;
    }

    public boolean isMatch(Object fieldValue, Object fieldMatchValue) {
        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }
}
