package org.shield.mybatis.helper;

import java.util.Set;
import org.shield.mybatis.plugin.LogicId;
import tk.mybatis.mapper.MapperException;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

/**
 * @author zacksleo <zacksleo@gmail.com>
 */
public class SqlUtil {

    /**
     * 获取逻辑主键注解的列
     *
     * @param entityClass
     * @return
     */
    public static EntityColumn getLogicIdColumn(Class<?> entityClass) {
        EntityColumn logicIdColumn = null;
        Set<EntityColumn> columnSet = EntityHelper.getColumns(entityClass);
        boolean hasLogicId = false;
        for (EntityColumn column : columnSet) {
            if (column.getEntityField().isAnnotationPresent(LogicId.class)) {
                if (hasLogicId) {
                    throw new MapperException(
                            entityClass.getCanonicalName() + " 中包含多个带有 @LogicId 注解的字段，一个类中只能存在一个带有 @LogicId 注解的字段!");
                }
                hasLogicId = true;
                logicIdColumn = column;
            }
        }
        if (!hasLogicId) {
            return null;
        }
        return logicIdColumn;
    }
}
