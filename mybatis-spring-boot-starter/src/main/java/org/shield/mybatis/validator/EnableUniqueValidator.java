package org.shield.mybatis.validator;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.shield.mybatis.validator.annotation.EnableUnique;
import org.shield.mybatis.validator.annotation.Unique;
import tk.mybatis.mapper.util.StringUtil;
import org.shield.mybatis.mappers.UniqueMapper;

/**
 *
 * 某个字段唯一
 *
 * @author zacksleo
 */
public class EnableUniqueValidator implements ConstraintValidator<EnableUnique, Object> {

    static private final String REGX = "[a-zA-Z0-9_]+";

    private String table;
    private String sn;
    private String message;
    private Object object;
    private String affixCondition;
    private ConstraintValidatorContext context;

    @Resource
    UniqueMapper mapper;

    @Override
    public void initialize(EnableUnique validator) {
        this.table = validator.table();
        this.sn = validator.sn();
        this.message = validator.message();
        this.affixCondition = validator.affixCondition();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        object = o;
        this.context = context;
        return valid();
    }


    private boolean valid() {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                if (f.isAnnotationPresent(Unique.class)) {
                    String table = getTable(f);
                    if (table.isEmpty()) {
                        return failValidate("@EnableUnique/@Unique 至少配置一个 table");
                    }

                    String sn = getSn(f);

                    if (sn.isEmpty()) {
                        return failValidate("@EnableUnique/@Unique 至少配置一个 sn");
                    }

                    String message = getMessage(f);

                    String snVal = getSnVal(sn);

                    String field = f.getAnnotation(Unique.class).field();
                    String fieldValue = f.get(object).toString();

                    if (!Pattern.matches(REGX, table) || !Pattern.matches(REGX, field)) {
                        return failValidate("@EnableUnique/@Unique 配置格式错误");
                    }

                    if (sn.isEmpty() || snVal == null || snVal.isEmpty()) {
                        if (mapper.exists(table, field, fieldValue, affixCondition)) {
                            return failValidate(message);
                        }
                    }
                    List<String> sns = mapper.findByField(table, field, fieldValue, StringUtil.camelhumpToUnderline(sn),
                            affixCondition);
                    if (sns.isEmpty()) {
                        continue;
                    }
                    if (sns.size() > 1) {
                        return failValidate(message);
                    }
                    if (!snVal.equals(sns.get(0))) {
                        return failValidate(message);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private boolean failValidate(String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return false;
    }

    private String getTable(Field f) {
        String defaultVal = table;
        String current = f.getAnnotation(Unique.class).table();
        if (!current.isEmpty()) {
            defaultVal = current;
        }
        return defaultVal;
    }

    private String getSn(Field f) {
        String defaultVal = sn;
        String current = f.getAnnotation(Unique.class).sn();
        if (!current.isEmpty()) {
            defaultVal = current;
        }
        return defaultVal;
    }

    private String getMessage(Field f) {
        String defaultVal = message;
        String current = f.getAnnotation(Unique.class).message();
        if (!current.isEmpty()) {
            defaultVal = current;
        }
        return defaultVal;
    }

    private String getSnVal(String sn) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                if (f.getName().equals(sn)) {
                    if (f.get(object) == null) {
                        return null;
                    }
                    return f.get(object).toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
