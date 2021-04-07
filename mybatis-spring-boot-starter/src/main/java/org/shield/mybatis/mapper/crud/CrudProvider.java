package org.shield.mybatis.mapper.crud;

import org.apache.ibatis.mapping.MappedStatement;
import org.shield.mybatis.helper.SqlUtil;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

/**
 * @author zacksleo <zacksleo@gmail.com>
 */
public class CrudProvider extends MapperTemplate {

    public CrudProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 根据逻辑主键进行查询
     *
     * @param ms
     */
    public String selectByLogicId(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        // 将返回值修改为实体类型
        setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
        appendWhereLogicId(sql, entityClass);
        sql.append("LIMIT 1");
        return sql.toString();
    }

    /**
     * 根据逻辑主键删除
     *
     * @param ms
     * @return
     */
    public String deleteByLogicId(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        // 如果是逻辑删除，则修改为更新表，修改逻辑删除字段的值
        if (SqlHelper.hasLogicDeleteColumn(entityClass)) {
            sql.append(SqlHelper.updateTable(entityClass, tableName(entityClass)));
            sql.append("<set>");
            sql.append(SqlHelper.logicDeleteColumnEqualsValue(entityClass, true));
            sql.append("</set>");
        } else {
            sql.append(SqlHelper.deleteFromTable(entityClass, tableName(entityClass)));
        }
        appendWhereLogicId(sql, entityClass);
        return sql.toString();
    }

    /**
     * 通过逻辑主键更新全部字段
     *
     * @param ms
     */
    public String updateByLogicId(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.updateTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.updateSetColumns(entityClass, null, false, false));
        appendWhereLogicId(sql, entityClass);
        return sql.toString();
    }

    /**
     * 通过逻辑主键更新不为null的字段
     *
     * @param ms
     * @return
     */
    public String updateByLogicIdSelective(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.updateTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.updateSetColumns(entityClass, "record", true, isNotEmpty()));
        appendWhereLogicId(sql, entityClass);
        return sql.toString();
    }

    /**
     * 拼接条件
     *
     * @param sql
     * @param entityClass
     */
    private void appendWhereLogicId(StringBuilder sql, Class<?> entityClass) {
        EntityColumn column = SqlUtil.getLogicIdColumn(entityClass);
        sql.append("<where>");
        sql.append(column.getColumn());
        sql.append(" = ");
        sql.append("#{logicId}");
        sql.append(SqlHelper.whereLogicDelete(entityClass, false));
        sql.append("</where>");
    }
}
