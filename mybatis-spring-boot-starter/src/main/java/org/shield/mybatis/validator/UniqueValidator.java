package org.shield.mybatis.validator;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.shield.mybatis.validator.annotation.Unique;
import tk.mybatis.mapper.util.StringUtil;
import org.shield.mybatis.mappers.UniqueMapper;

/**
 *
 * 某个字段唯一
 *
 * @author zacksleo
 */
public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    static private final String REGX = "[a-zA-Z0-9_]+";

    private String table;
    private String field;
    private String fieldValue;
    private String sn;
    private String snVal;
    private Object object;
    private boolean exceptDeleted;

    @Resource
    UniqueMapper mapper;

    @Override
    public void initialize(Unique validator) {
        field = validator.field();
        table = validator.table();
        sn = validator.sn();
        exceptDeleted = validator.exceptDeleted();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        object = o;
        if (o instanceof String) {
            fieldValue = o.toString();
        } else {
            getFieldValue();
        }
        if (!Pattern.matches(REGX, table) || !Pattern.matches(REGX, field)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("@Unique 配置格式错误").addConstraintViolation();
            return false;
        }

        return valid();
    }

    /**
     * 获取id、校验字段值
     */
    public void getFieldValue() {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                if (f.getName().equals(field)) {
                    fieldValue = f.get(object).toString();
                }
                if (f.getName().equals(sn)) {
                    snVal = f.get(object).toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean valid() {
        if (sn.isEmpty() || snVal == null || snVal.isEmpty()) {
            return exceptDeleted ? !mapper.existsExceptDeleted(table, field, fieldValue)
                    : !mapper.exists(table, field, fieldValue);
        }
        List<String> sns = exceptDeleted
                ? mapper.findByFieldExceptDeleted(table, field, fieldValue, StringUtil.camelhumpToUnderline(sn))
                : mapper.findByField(table, field, fieldValue, StringUtil.camelhumpToUnderline(sn));
        if (sns.isEmpty()) {
            return true;
        }
        if (sns.size() > 1) {
            return false;
        }
        return snVal.equals(sns.get(0));
    }
}
