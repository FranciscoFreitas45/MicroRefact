package org.jeecgframework.web.cgform.service.impl.config;
 import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.service.config.DbTableHandleI;
import org.jeecgframework.web.cgform.service.impl.config.util.ColumnMeta;
public class TableSQLServerHandleImpl implements DbTableHandleI{


public String dropTableSQL(String tableName){
    return " DROP TABLE " + tableName + " ;";
}


public String getAddColumnSql(ColumnMeta columnMeta){
    return " ADD  " + getAddFieldDesc(columnMeta) + ";";
}


public String getUpdateFieldDesc(ColumnMeta cgfromcolumnMeta,ColumnMeta datacolumnMeta){
    String result = "";
    // update-begin--Author:qinfeng  Date:20180118 for：Sqlserver2008 字段类型适配问题解决、sqlserver注解生成有问题暂时注释掉--------------------
    if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("string")) {
        result = cgfromcolumnMeta.getColumnName() + " varchar(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("date")) {
        result = cgfromcolumnMeta.getColumnName() + " datetime" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("int")) {
        result = cgfromcolumnMeta.getColumnName() + " int " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("double")) {
        result = cgfromcolumnMeta.getColumnName() + " float " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("bigdecimal")) {
        result = cgfromcolumnMeta.getColumnName() + " numeric(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("text")) {
        result = cgfromcolumnMeta.getColumnName() + " text" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("blob")) {
        result = cgfromcolumnMeta.getColumnName() + " varbinary(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    }
    // result += (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault())?" DEFAULT "+cgfromcolumnMeta.getFieldDefault():" ");
    // update-end--Author:qinfeng  Date:20180118 for：Sqlserver2008 字段类型适配问题解决、sqlserver注解生成有问题暂时注释掉--------------------
    return result;
}


public String getReNameFieldName(ColumnMeta columnMeta){
    // sp_rename 'TOA_E_ARTICLE.version','processVersion','COLUMN';
    return "  sp_rename '" + columnMeta.getTableName() + "." + columnMeta.getOldColumnName() + "', '" + columnMeta.getColumnName() + "', 'COLUMN';";
// return "ALTER COLUMN  "+columnMeta.getOldColumnName() +" "+getRenameFieldDesc(columnMeta)+";";
}


public String getRenameFieldDesc(ColumnMeta cgfromcolumnMeta){
    String result = "";
    // update-begin--Author:qinfeng  Date:20180118 for：Sqlserver2008 字段类型适配问题解决-------------------
    if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("string")) {
        result = cgfromcolumnMeta.getColumnName() + " varchar(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("date")) {
        result = cgfromcolumnMeta.getColumnName() + " datetime" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("int")) {
        result = cgfromcolumnMeta.getColumnName() + " int " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("double")) {
        result = cgfromcolumnMeta.getColumnName() + " float " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    }
    // update-end--Author:qinfeng  Date:20180118 for：Sqlserver2008 字段类型适配问题解决--------------------
    return result;
}


public String getUpdateColumnSql(ColumnMeta cgformcolumnMeta,ColumnMeta datacolumnMeta){
    return " ALTER COLUMN  " + getUpdateFieldDesc(cgformcolumnMeta, datacolumnMeta) + ";";
}


public String getDropColumnSql(String fieldName){
    return " DROP COLUMN " + fieldName + ";";
}


public String getSpecialHandle(ColumnMeta cgformcolumnMeta,ColumnMeta datacolumnMeta){
    return null;
}


public String getAddFieldDesc(ColumnMeta cgfromcolumnMeta){
    String result = "";
    // update-begin--Author:qinfeng  Date:20180118 for：Sqlserver2008 字段类型适配问题解决、sqlserver注解生成有问题暂时注释掉--------------------
    if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("string")) {
        result = cgfromcolumnMeta.getColumnName() + " varchar(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("date")) {
        result = cgfromcolumnMeta.getColumnName() + " datetime" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("int")) {
        result = cgfromcolumnMeta.getColumnName() + " int " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("double")) {
        result = cgfromcolumnMeta.getColumnName() + " float " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("bigdecimal")) {
        result = cgfromcolumnMeta.getColumnName() + " numeric(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("text")) {
        result = cgfromcolumnMeta.getColumnName() + " text" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("blob")) {
        result = cgfromcolumnMeta.getColumnName() + " varbinary(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    }
    // update-end--Author:liuht  Date:20131223
    // result += (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault())?" DEFAULT "+cgfromcolumnMeta.getFieldDefault():" ");
    // update-end--Author:qinfeng  Date:20180118 for：Sqlserver2008 字段类型适配问题解决、sqlserver注解生成有问题暂时注释掉--------------------
    return result;
}


public String getMatchClassTypeByDataType(String dataType,int digits){
    // update-begin--Author:qinfeng  Date:20180118 for：Sqlserver2008 字段类型适配问题解决--------------------
    String result = "";
    if (dataType.equalsIgnoreCase("varchar")) {
        result = "string";
    } else if (dataType.equalsIgnoreCase("float")) {
        result = "double";
    } else if (dataType.equalsIgnoreCase("int")) {
        result = "int";
    } else if (dataType.equalsIgnoreCase("Date")) {
        result = "date";
    } else if (dataType.equalsIgnoreCase("Datetime")) {
        result = "date";
    } else if (dataType.equalsIgnoreCase("numeric")) {
        result = "bigdecimal";
    } else if (dataType.equalsIgnoreCase("varbinary")) {
        result = "blob";
    }
    // update-begin--Author:qinfeng  Date:20180118 for：Sqlserver2008 字段类型适配问题解决--------------------
    return result;
}


public String getCommentSql(ColumnMeta columnMeta){
    StringBuffer commentSql = new StringBuffer("EXECUTE ");
    if (StringUtil.isEmpty(columnMeta.getOldColumnName())) {
        commentSql.append("sp_addextendedproperty");
    } else {
        commentSql.append("sp_updateextendedproperty");
    }
    commentSql.append(" N'MS_Description', '");
    commentSql.append(columnMeta.getComment());
    commentSql.append("', N'SCHEMA', N'dbo', N'TABLE', N'");
    commentSql.append(columnMeta.getTableName());
    commentSql.append("', N'COLUMN', N'");
    commentSql.append(columnMeta.getColumnName() + "'");
    return commentSql.toString();
}


}