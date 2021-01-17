package org.shield.validation.validator;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidatorContext;

/**
 * @author zacksleo@gmail.com
 */
public abstract class AbstractRegexValidator {

    private String regexp;
    private boolean failOnEmpty;

    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    public void setFailOnEmpty(boolean failOnEmpty) {
        this.failOnEmpty = failOnEmpty;
    }

    public boolean isValid(Object o, ConstraintValidatorContext context) {
        if (o == null) {
            return !failOnEmpty;
        }
        String val = o.toString();
        if (val.isEmpty()) {
            return !failOnEmpty;
        }
        if (!Pattern.matches(regexp, val)) {
            return false;
        }
        return true;
    }
}
