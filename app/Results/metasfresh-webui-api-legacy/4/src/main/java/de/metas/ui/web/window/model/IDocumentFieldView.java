package de.metas.ui.web.window.model;
 import java.util.Optional;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.lookup.DocumentZoomIntoInfo;
public interface IDocumentFieldView {


public DocumentFieldDescriptor getDescriptor()
;

public Class<?> getValueClass(){
    return getDescriptor().getValueClass();
}
;

public int getValueAsInt(int defaultValueWhenNull)
;

public Object getInitialValue()
;

public boolean isReadonly(){
    return getReadonly().booleanValue();
}
;

public DocumentZoomIntoInfo getZoomIntoInfo()
;

public LogicExpressionResult getDisplayed()
;

public boolean isCalculated(){
    return getDescriptor().isCalculated();
}
;

public LogicExpressionResult getReadonly()
;

public T getValueAs(Class<T> returnType)
;

public boolean hasChangesToSave()
;

public boolean isMandatory(){
    return getMandatory().booleanValue();
}
;

public Object getValueAsJsonObject(JSONOptions jsonOpts)
;

public boolean isParentLink(){
    return getDescriptor().isParentLink();
}
;

public boolean getValueAsBoolean()
;

public boolean isLookupValuesStale()
;

public LogicExpressionResult getMandatory()
;

public DocumentFieldWidgetType getWidgetType(){
    return getDescriptor().getWidgetType();
}
;

public Optional<WindowId> getZoomIntoWindowId()
;

public boolean isAdvancedField(){
    return getDescriptor().hasCharacteristic(Characteristic.AdvancedField);
}
;

public boolean isKey(){
    return getDescriptor().isKey();
}
;

public boolean isDisplayed(){
    return getDisplayed().booleanValue();
}
;

public DocumentValidStatus getValidStatus()
;

public boolean isPublicField(){
    return getDescriptor().hasCharacteristic(Characteristic.PublicField);
}
;

public Object getOldValue()
;

public Object getValue()
;

public DocumentPath getDocumentPath()
;

public boolean isAlwaysUpdateable(){
    return getDescriptor().isAlwaysUpdateable();
}
;

public boolean isVirtualField(){
    return getDescriptor().isVirtualField();
}
;

public String getFieldName(){
    return getDescriptor().getFieldName();
}
;

}