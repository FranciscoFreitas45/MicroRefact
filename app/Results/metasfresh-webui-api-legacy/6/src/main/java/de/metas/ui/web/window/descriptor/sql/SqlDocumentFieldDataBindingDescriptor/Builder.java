package de.metas.ui.web.window.descriptor.sql.SqlDocumentFieldDataBindingDescriptor;
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
public class Builder {

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


public Builder setTableName(String tableName){
    this._sqlTableName = tableName;
    return this;
}


public Class<?> getValueClass(){
    if (_valueClass != null) {
        return _valueClass;
    }
    return getWidgetType().getValueClass();
}


public Boolean getNumericKey(){
    return _numericKey;
}


public Builder setMandatory(boolean mandatory){
    this.mandatory = mandatory;
    return this;
}


public Builder setColumnSql(String columnSql){
    this._sqlColumnSql = columnSql;
    return this;
}


public Builder setFieldName(String fieldName){
    this.fieldName = fieldName;
    return this;
}


public String getColumnName(){
    return _sqlColumnName;
}


public Builder setDefaultOrderBy(int priority){
    if (priority >= 0) {
        orderByPriority = priority;
        orderByAscending = true;
    } else {
        orderByPriority = -priority;
        orderByAscending = false;
    }
    return this;
}


public boolean isVirtualColumn(){
    return _virtualColumn;
}


public Builder setVirtualColumn(boolean virtualColumn){
    this._virtualColumn = virtualColumn;
    return this;
}


public SqlSelectValue buildSqlSelectValue(){
    final String columnSql = getColumnSql();
    final String columnName = getColumnName();
    // 
    // Case: the SQL binding doesn't have any column set.
    // Usually that's the case when the actual value it's not in the table name but it will be fetched by loader (from other tables).
    // Check the Labels case for example.
    if (Check.isEmpty(columnName, true)) {
        return SqlSelectValue.builder().virtualColumnSql("NULL").columnNameAlias(getFieldName()).build();
    } else // 
    // Virtual column
    if (isVirtualColumn()) {
        return SqlSelectValue.builder().virtualColumnSql(columnSql).columnNameAlias(columnName).build();
    } else // 
    // Regular table column
    {
        return SqlSelectValue.builder().tableNameOrAlias(getTableName()).columnName(columnSql).columnNameAlias(columnName).build();
    }
}


public DocumentFieldValueLoader createDocumentFieldValueLoader(String sqlColumnName,String displayColumnName,Class<?> valueClass,DocumentFieldWidgetType widgetType,boolean encrypted,Boolean numericKey){
    if (!Check.isEmpty(displayColumnName)) {
        return DocumentFieldValueLoaders.toLookupValue(sqlColumnName, displayColumnName, /* descriptionColumnName, */
        numericKey);
    } else if (java.lang.String.class == valueClass) {
        return DocumentFieldValueLoaders.toString(sqlColumnName, encrypted);
    } else if (Password.class == valueClass) {
        return DocumentFieldValueLoaders.toPassword(sqlColumnName, encrypted);
    } else if (java.lang.Integer.class == valueClass) {
        return DocumentFieldValueLoaders.toInteger(sqlColumnName, encrypted);
    } else if (java.math.BigDecimal.class == valueClass) {
        final Integer precision = widgetType.getStandardNumberPrecision();
        return DocumentFieldValueLoaders.toBigDecimal(sqlColumnName, encrypted, precision);
    } else // 
    // Date & times
    if (java.util.Date.class.isAssignableFrom(valueClass)) {
        return DocumentFieldValueLoaders.toJULDate(sqlColumnName, encrypted);
    } else if (ZonedDateTime.class == valueClass) {
        return DocumentFieldValueLoaders.toZonedDateTime(sqlColumnName, encrypted);
    } else if (Instant.class == valueClass) {
        return DocumentFieldValueLoaders.toInstant(sqlColumnName, encrypted);
    } else if (LocalDate.class == valueClass) {
        return DocumentFieldValueLoaders.toLocalDate(sqlColumnName, encrypted);
    } else if (LocalTime.class == valueClass) {
        return DocumentFieldValueLoaders.toLocalTime(sqlColumnName, encrypted);
    } else // YesNo
    if (Boolean.class == valueClass) {
        return DocumentFieldValueLoaders.toBoolean(sqlColumnName, encrypted);
    } else // LOB
    if (byte[].class == valueClass) {
        return DocumentFieldValueLoaders.toByteArray(sqlColumnName, encrypted);
    } else // Labels
    if (DocumentFieldWidgetType.Labels.getValueClass().equals(valueClass)) {
        return DocumentFieldValueLoaders.toLabelValues(sqlColumnName);
    } else if (ColorValue.class == valueClass) {
        return DocumentFieldValueLoaders.toColor(sqlColumnName);
    } else {
        return DocumentFieldValueLoaders.toString(sqlColumnName, encrypted);
    }
}


public Class<?> getSqlValueClass(){
    if (_sqlValueClass != null) {
        return _sqlValueClass;
    }
    return getValueClass();
}


public SqlSelectDisplayValue buildSqlSelectDisplayValue(){
    final ISqlLookupDescriptor sqlLookupDescriptor = _lookupDescriptor != null ? _lookupDescriptor.castOrNull(ISqlLookupDescriptor.class) : null;
    return SqlSelectDisplayValue.builder().joinOnTableNameOrAlias(getTableAlias()).joinOnColumnName(getColumnName()).sqlExpression(sqlLookupDescriptor.getSqlForFetchingLookupByIdExpression()).columnNameAlias(getColumnName() + "$Display").build();
}


public Builder setColumnName(String columnName){
    this._sqlColumnName = columnName;
    return this;
}


public String getColumnSql(){
    return _sqlColumnSql;
}


public DocumentFieldValueLoader getDocumentFieldValueLoader(){
    if (_documentFieldValueLoader == null) {
        final String displayColumnName = _sqlSelectDisplayValue != null ? _sqlSelectDisplayValue.getColumnNameAlias() : null;
        _documentFieldValueLoader = createDocumentFieldValueLoader(getColumnName(), displayColumnName, getValueClass(), getWidgetType(), encrypted, getNumericKey());
    }
    return _documentFieldValueLoader;
}


public DocumentFieldWidgetType getWidgetType(){
    Check.assumeNotNull(_widgetType, "Parameter widgetType is not null");
    return _widgetType;
}


public String getTableName(){
    return _sqlTableName;
}


public Builder setValueClass(Class<?> valueClass){
    this._valueClass = valueClass;
    return this;
}


public Builder setTableAlias(String tableAlias){
    this._sqlTableAlias = tableAlias;
    return this;
}


public Builder setLookupDescriptor(LookupDescriptor lookupDescriptor){
    this._lookupDescriptor = lookupDescriptor;
    return this;
}


public String getTableAlias(){
    return _sqlTableAlias;
}


public Builder setEncrypted(boolean encrypted){
    this.encrypted = encrypted;
    return this;
}


public SqlDocumentFieldDataBindingDescriptor build(){
    // 
    // Display column
    final String sqlColumnName = getColumnName();
    if (_lookupDescriptor != null && // in case of Labels, sqlColumnName is null
    sqlColumnName != null && _lookupDescriptor instanceof ISqlLookupDescriptor) {
        _numericKey = _lookupDescriptor.isNumericKey();
        _sqlSelectDisplayValue = buildSqlSelectDisplayValue();
    } else {
        _numericKey = null;
        _sqlSelectDisplayValue = null;
    }
    return new SqlDocumentFieldDataBindingDescriptor(this);
}


public Builder setWidgetType(DocumentFieldWidgetType widgetType){
    this._widgetType = widgetType;
    return this;
}


public Builder setSqlValueClass(Class<?> sqlValueClass){
    this._sqlValueClass = sqlValueClass;
    return this;
}


public Builder setKeyColumn(boolean keyColumn){
    this.keyColumn = keyColumn;
    return this;
}


public String getFieldName(){
    return fieldName;
}


}