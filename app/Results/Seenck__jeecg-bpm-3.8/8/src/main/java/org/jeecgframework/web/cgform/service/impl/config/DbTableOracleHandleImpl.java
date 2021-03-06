package org.jeecgframework.web.cgform.service.impl.config;
 import org.apache.commons.lang.StringUtils;
import org.jeecgframework.web.cgform.service.config.DbTableHandleI;
import org.jeecgframework.web.cgform.service.impl.config.util.ColumnMeta;
public class DbTableOracleHandleImpl implements DbTableHandleI{


public String dropTableSQL(String tableName){
    return " DROP TABLE  " + tableName.toLowerCase() + " ";
}


public String getAddColumnSql(ColumnMeta columnMeta){
    return " ADD  " + getAddFieldDesc(columnMeta) + "";
}


public String getUpdateFieldDesc(ColumnMeta cgformcolumnMeta,ColumnMeta datacolumnMeta){
    String result = "";
    String isnull = "";
    // oracle对于是否为空必须跟原来的比对
    if (!datacolumnMeta.getIsNullable().equals(cgformcolumnMeta.getIsNullable())) {
        isnull = (cgformcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
    }
    // update-begin--Author:scott  Date:20180227 for：oracle针对blob\text\nvarchar2逻辑处理--------------------
    if (cgformcolumnMeta.getColunmType().equalsIgnoreCase("string")) {
        result = cgformcolumnMeta.getColumnName() + " varchar2(" + cgformcolumnMeta.getColumnSize() + ")" + isnull;
    } else if (cgformcolumnMeta.getColunmType().equalsIgnoreCase("date")) {
        result = cgformcolumnMeta.getColumnName() + " date " + isnull;
    } else if (cgformcolumnMeta.getColunmType().equalsIgnoreCase("int")) {
        result = cgformcolumnMeta.getColumnName() + " NUMBER(" + cgformcolumnMeta.getColumnSize() + ") " + isnull;
    } else if (cgformcolumnMeta.getColunmType().equalsIgnoreCase("double")) {
        result = cgformcolumnMeta.getColumnName() + " NUMBER(" + cgformcolumnMeta.getColumnSize() + "," + cgformcolumnMeta.getDecimalDigits() + ") " + isnull;
    } else if (cgformcolumnMeta.getColunmType().equalsIgnoreCase("bigdecimal")) {
        result = cgformcolumnMeta.getColumnName() + " NUMBER(" + cgformcolumnMeta.getColumnSize() + "," + cgformcolumnMeta.getDecimalDigits() + ") " + isnull;
    } else if (cgformcolumnMeta.getColunmType().equalsIgnoreCase("blob")) {
        result = cgformcolumnMeta.getColumnName() + " BLOB " + isnull;
    } else if (cgformcolumnMeta.getColunmType().equalsIgnoreCase("text")) {
        result = cgformcolumnMeta.getColumnName() + " CLOB " + isnull;
    }
    // update-end--Author:scott  Date:20180227 for：oracle针对blob\text\nvarchar2逻辑处理--------------------
    result += (StringUtils.isNotEmpty(cgformcolumnMeta.getFieldDefault()) ? " DEFAULT " + cgformcolumnMeta.getFieldDefault() : " ");
    result += isnull;
    return result;
}


public String getReNameFieldName(ColumnMeta columnMeta){
    return "RENAME COLUMN  " + columnMeta.getOldColumnName() + " TO " + columnMeta.getColumnName() + "";
}


public String getUpdateColumnSql(ColumnMeta cgformcolumnMeta,ColumnMeta datacolumnMeta){
    return " MODIFY   " + getUpdateFieldDesc(cgformcolumnMeta, datacolumnMeta) + "";
}


public String getDropColumnSql(String fieldName){
    return " DROP COLUMN " + fieldName.toUpperCase() + "";
}


public String getSpecialHandle(ColumnMeta cgformcolumnMeta,ColumnMeta datacolumnMeta){
    return null;
}


public String getAddFieldDesc(ColumnMeta columnMeta){
    String result = "";
    if (columnMeta.getColunmType().equalsIgnoreCase("string")) {
        result = columnMeta.getColumnName() + " varchar2(" + columnMeta.getColumnSize() + ")";
    } else if (columnMeta.getColunmType().equalsIgnoreCase("date")) {
        result = columnMeta.getColumnName() + " datetime";
    } else if (columnMeta.getColunmType().equalsIgnoreCase("int")) {
        result = columnMeta.getColumnName() + " NUMBER(" + columnMeta.getColumnSize() + ")";
    } else if (columnMeta.getColunmType().equalsIgnoreCase("double")) {
        result = columnMeta.getColumnName() + " NUMBER(" + columnMeta.getColumnSize() + "," + columnMeta.getDecimalDigits() + ")";
    } else if (columnMeta.getColunmType().equalsIgnoreCase("bigdecimal")) {
        result = columnMeta.getColumnName() + " NUMBER(" + columnMeta.getColumnSize() + "," + columnMeta.getDecimalDigits() + ")";
    } else if (columnMeta.getColunmType().equalsIgnoreCase("text")) {
        result = columnMeta.getColumnName() + " CLOB ";
    } else if (columnMeta.getColunmType().equalsIgnoreCase("blob")) {
        result = columnMeta.getColumnName() + " BLOB ";
    }
    result += (StringUtils.isNotEmpty(columnMeta.getFieldDefault()) ? " DEFAULT " + columnMeta.getFieldDefault() : " ");
    result += (columnMeta.getIsNullable().equals("Y") ? " NULL" : " NOT NULL");
    return result;
}


public String getMatchClassTypeByDataType(String dataType,int digits){
    // update-begin--Author:scott  Date:20180227 for：oracle针对blob\text\nvarchar2逻辑处理--------------------
    String result = "";
    if (dataType.equalsIgnoreCase("varchar2")) {
        result = "string";
    }
    if (dataType.equalsIgnoreCase("nvarchar2")) {
        result = "string";
    } else if (dataType.equalsIgnoreCase("double")) {
        result = "double";
    } else if (dataType.equalsIgnoreCase("number") && digits == 0) {
        result = "int";
    } else if (dataType.equalsIgnoreCase("number") && digits != 0) {
        result = "double";
    } else if (dataType.equalsIgnoreCase("int")) {
        result = "int";
    } else if (dataType.equalsIgnoreCase("Date")) {
        result = "date";
    } else if (dataType.equalsIgnoreCase("Datetime")) {
        result = "date";
    } else if (dataType.equalsIgnoreCase("blob")) {
        result = "blob";
    } else if (dataType.equalsIgnoreCase("clob")) {
        result = "text";
    }
    // update-begin--Author:scott  Date:20180227 for：oracle针对blob\text\nvarchar2逻辑处理--------------------
    return result;
}


public String getCommentSql(ColumnMeta columnMeta){
    return "COMMENT ON COLUMN " + columnMeta.getTableName() + "." + columnMeta.getColumnName() + " IS '" + columnMeta.getComment() + "'";
}


}