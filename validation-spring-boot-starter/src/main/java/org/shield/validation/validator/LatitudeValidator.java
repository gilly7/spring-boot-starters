package org.shield.validation.validator;

import java.math.BigDecimal;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.shield.validation.validator.annotation.Latitude;

/**
 *
 * 验证经度合法
 *
 * @author zacksleo
 * @see https://stackoverflow.com/a/31408260/4382692
 */
public class LatitudeValidator implements ConstraintValidator<Latitude, Object> {

    protected static final String LATITUDE_PATTERN =
            "^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,15})?))$";

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        if (o == null) {
            return false;
        }
        String val = o.toString();
        if (!Pattern.matches(LATITUDE_PATTERN, val.toString())) {
            return false;
        }
        if (BigDecimal.ZERO.compareTo(new BigDecimal(val)) == 0) {
            return false;
        }
        return true;
    }
}
