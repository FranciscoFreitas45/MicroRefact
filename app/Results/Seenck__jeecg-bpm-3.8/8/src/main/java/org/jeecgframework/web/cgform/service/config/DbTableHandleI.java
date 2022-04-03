package org.jeecgframework.web.cgform.service.config;
 import org.jeecgframework.web.cgform.exception.DBException;
import org.jeecgframework.web.cgform.service.impl.config.util.ColumnMeta;
public interface DbTableHandleI {


public String dropTableSQL(String tableName)
;

public String getAddColumnSql(ColumnMeta columnMeta)
;

public String getReNameFieldName(ColumnMeta columnMeta)
;

public String getUpdateColumnSql(ColumnMeta cgformcolumnMeta,ColumnMeta datacolumnMeta)
;

public String getDropColumnSql(String fieldName)
;

public String getSpecialHandle(ColumnMeta cgformcolumnMeta,ColumnMeta datacolumnMeta)
;

public String getMatchClassTypeByDataType(String dataType,int digits)
;

public String getCommentSql(ColumnMeta columnMeta)
;

}