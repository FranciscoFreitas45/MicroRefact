package de.metas.ui.web.window.descriptor.sql;
 import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
public class PlainSqlEntityFieldBinding implements SqlEntityFieldBinding{

 private String columnName;

 private SqlSelectValue sqlSelectValue;

 private DocumentFieldWidgetType widgetType;

 private Class<?> sqlValueClass;

 private SqlOrderByValue sqlOrderBy;

 private boolean virtualColumn;


public PlainSqlEntityFieldBinding intField(String columnName){
    return builder().columnName(columnName).widgetType(DocumentFieldWidgetType.Integer).build();
}


}