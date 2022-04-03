package de.metas.ui.web.window.model;
 import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.util.Check;
public class MutableDocumentFieldChangedEvent implements IDocumentFieldChangedEvent{

 private  DocumentPath documentPath;

 private  String fieldName;

 private  DocumentFieldWidgetType widgetType;

 private  Object value;

 private  boolean valueSet;


@Override
public boolean isValueSet(){
    return valueSet;
}


@Override
public Object getValue(){
    return value;
}


@Override
public DocumentFieldWidgetType getWidgetType(){
    return widgetType;
}


public MutableDocumentFieldChangedEvent of(DocumentPath documentPath,String fieldName,DocumentFieldWidgetType widgetType){
    return new MutableDocumentFieldChangedEvent(documentPath, fieldName, widgetType);
}


@Override
public DocumentPath getDocumentPath(){
    return documentPath;
}


public MutableDocumentFieldChangedEvent setValue(Object value){
    this.value = value;
    valueSet = true;
    return this;
}


@Override
public String toString(){
    final ToStringHelper helper = MoreObjects.toStringHelper(this).omitNullValues().add("documentPath", documentPath).add("fieldName", fieldName);
    if (valueSet) {
        helper.add("value", value == null ? "<NULL>" : value);
    }
    return helper.toString();
}


@Override
public String getFieldName(){
    return fieldName;
}


}