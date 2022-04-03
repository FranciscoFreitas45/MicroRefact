package de.metas.ui.web.window.model;
 import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
public interface IDocumentFieldChangedEvent {


public boolean isValueSet()
;

public Object getValue()
;

public DocumentFieldWidgetType getWidgetType()
;

public DocumentPath getDocumentPath()
;

public String getFieldName()
;

}