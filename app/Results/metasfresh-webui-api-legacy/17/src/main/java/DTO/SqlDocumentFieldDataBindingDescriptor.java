package DTO;
 import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Optional;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.window.datatypes.ColorValue;
import de.metas.ui.web.window.datatypes.Password;
import de.metas.ui.web.window.descriptor.DocumentFieldDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.util.Check;
public class SqlDocumentFieldDataBindingDescriptor implements SqlEntityFieldBinding,DocumentFieldDataBindingDescriptor{

 private  String fieldName;

 private  String sqlColumnName;

 private  Class<?> sqlValueClass;

 private  boolean virtualColumn;

 private  boolean mandatory;

 private  boolean keyColumn;

 private  DocumentFieldWidgetType widgetType;

 private  Class<?> valueClass;

 private  DocumentFieldValueLoader documentFieldValueLoader;

 private  Boolean numericKey;

 private  SqlSelectValue sqlSelectValue;

 private  SqlSelectDisplayValue sqlSelectDisplayValue;

 private  int defaultOrderByPriority;

 private  boolean defaultOrderByAscending;

 private  String fieldName;

 private  String _sqlTableName;

 private  String _sqlTableAlias;

 private  String _sqlColumnName;

 private  String _sqlColumnSql;

 private  Class<?> _sqlValueClass;

 private  Boolean _virtualColumn;

 private  Boolean mandatory;

 private  Class<?> _valueClass;

 private  DocumentFieldWidgetType _widgetType;

 private  LookupDescriptor _lookupDescriptor;

 private  boolean keyColumn;

 private  boolean encrypted;

 private  boolean orderByAscending;

 private  int orderByPriority;

 private  SqlSelectDisplayValue _sqlSelectDisplayValue;

 private  Boolean _numericKey;

 private  DocumentFieldValueLoader _documentFieldValueLoader;


public Class<?> getValueClass(){
    if (_valueClass != null) {
        return _valueClass;
    }
    return getWidgetType().getValueClass();
}


public Boolean getNumericKey(){
    return _numericKey;
}


public String getColumnName(){
    return _sqlColumnName;
}


public Class<?> getSqlValueClass(){
    if (_sqlValueClass != null) {
        return _sqlValueClass;
    }
    return getValueClass();
}


public String getColumnSql(){
    return _sqlColumnSql;
}


@Override
public int getDefaultOrderByPriority(){
    return defaultOrderByPriority;
}


public DocumentFieldWidgetType getWidgetType(){
    Check.assumeNotNull(_widgetType, "Parameter widgetType is not null");
    return _widgetType;
}


public DocumentFieldValueLoader getDocumentFieldValueLoader(){
    if (_documentFieldValueLoader == null) {
        final String displayColumnName = _sqlSelectDisplayValue != null ? _sqlSelectDisplayValue.getColumnNameAlias() : null;
        _documentFieldValueLoader = createDocumentFieldValueLoader(getColumnName(), displayColumnName, getValueClass(), getWidgetType(), encrypted, getNumericKey());
    }
    return _documentFieldValueLoader;
}


public SqlSelectValue getSqlSelectValue(){
    return sqlSelectValue;
}


public String getTableName(){
    return _sqlTableName;
}


public SqlSelectDisplayValue getSqlSelectDisplayValue(){
    return sqlSelectDisplayValue;
}


public String getTableAlias(){
    return _sqlTableAlias;
}


@Override
public SqlOrderByValue getSqlOrderBy(){
    return SqlOrderByValue.builder().sqlSelectDisplayValue(getSqlSelectDisplayValue()).sqlSelectValue(getSqlSelectValue()).build();
}


public String getFieldName(){
    return fieldName;
}


}