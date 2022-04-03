package de.metas.ui.web.window.descriptor.sql;
 import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
public interface SqlEntityFieldBinding {


public Class<?> getSqlValueClass()
;

public DocumentFieldWidgetType getWidgetType()
;

public SqlOrderByValue getSqlOrderBy()
;

public SqlSelectValue getSqlSelectValue()
;

public String getColumnName()
;

public boolean isVirtualColumn()
;

}