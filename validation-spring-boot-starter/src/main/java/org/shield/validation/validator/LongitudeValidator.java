package org.shield.validation.validator;

import java.math.BigDecimal;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.shield.validation.validator.annotation.Longitude;

/**
 *
 * 验证经度合法
 *
 * @author zacksleo
 * @see https://stackoverflow.com/a/31408260/4382692
 */
public class LongitudeValidator implements ConstraintValidator<Longitude, Object> {

    private static final String LONGITUDE_PATTERN =
            "^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,14})?))$";

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        if (o == null) {
            return false;
        }
        String val = o.toString();
        if (!Pattern.matches(LONGITUDE_PATTERN, val.toString())) {
            return false;
        }
        if (BigDecimal.ZERO.compareTo(new BigDecimal(val)) == 0) {
            return false;
        }
        return true;
    }
}
