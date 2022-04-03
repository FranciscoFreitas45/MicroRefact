package de.metas.ui.web.window.model;
 import java.util.LinkedHashMap;
import java.util.Map;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.datatypes.Values;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import lombok.NonNull;
public class DocumentFieldChange {

 private  Logger logger;

 public  String DEBUGPROPERTY_FieldInfo;

 private  String fieldName;

 private  boolean key;

 private  boolean publicField;

 private  boolean advancedField;

 private  DocumentFieldWidgetType widgetType;

 private  boolean valueSet;

 private  Object value;

 private  ReasonSupplier valueReason;

 private  LogicExpressionResult readonly;

 private  ReasonSupplier readonlyReason;

 private  LogicExpressionResult mandatory;

 private  ReasonSupplier mandatoryReason;

 private  LogicExpressionResult displayed;

 private  ReasonSupplier displayedReason;

 private  Boolean lookupValuesStale;

 private  ReasonSupplier lookupValuesStaleReason;

 private  DocumentValidStatus validStatus;

 private  DocumentFieldWarning fieldWarning;

 private  LinkedHashMap<String,Object> debugProperties;


public void setMandatory(LogicExpressionResult mandatory,ReasonSupplier reason){
    this.mandatory = mandatory;
    mandatoryReason = reason;
    if (logger.isTraceEnabled()) {
        logger.trace("collect {} mandatory: {} -- {}", fieldName, mandatory, reason == null ? null : reason.get());
    }
}


public ReasonSupplier getValueReason(){
    return valueReason;
}


public LogicExpressionResult getDisplayed(){
    return displayed;
}


public void putDebugProperties(Map<String,Object> debugProperties){
    if (debugProperties == null || debugProperties.isEmpty()) {
        return;
    }
    if (this.debugProperties == null) {
        this.debugProperties = new LinkedHashMap<>();
    }
    this.debugProperties.putAll(debugProperties);
}


public ReasonSupplier getLookupValuesStaleReason(){
    return lookupValuesStaleReason;
}


public LogicExpressionResult getReadonly(){
    return readonly;
}


public boolean isValueSet(){
    return valueSet;
}


public void setLookupValuesStale(Boolean lookupValuesStale,ReasonSupplier reason){
    this.lookupValuesStale = lookupValuesStale;
    lookupValuesStaleReason = reason;
    logger.trace("collect {} lookupValuesStale: {} -- {}", fieldName, lookupValuesStale, lookupValuesStaleReason);
}


public DocumentFieldChange of(String fieldName,boolean key,boolean publicField,boolean advancedField,DocumentFieldWidgetType widgetType){
    return new DocumentFieldChange(fieldName, key, publicField, advancedField, widgetType);
}


public void setFieldWarning(DocumentFieldWarning fieldWarning){
    this.fieldWarning = fieldWarning;
}


public void setValidStatus(DocumentValidStatus validStatus){
    this.validStatus = validStatus;
    logger.trace("collect {} validStatus: {}", fieldName, validStatus);
}


public void setDisplayed(LogicExpressionResult displayed,ReasonSupplier reason){
    this.displayed = displayed;
    displayedReason = reason;
    if (logger.isTraceEnabled()) {
        logger.trace("collect {} displayed: {} -- {}", fieldName, displayed, reason == null ? null : reason.get());
    }
}


public Object getValueAsJsonObject(JSONOptions jsonOpts){
    return Values.valueToJsonObject(value, jsonOpts);
}


public Boolean getLookupValuesStale(){
    return lookupValuesStale;
}


public LogicExpressionResult getMandatory(){
    return mandatory;
}


public ReasonSupplier getMandatoryReason(){
    return mandatoryReason;
}


public Map<String,Object> getDebugProperties(){
    return debugProperties == null ? ImmutableMap.of() : debugProperties;
}


public DocumentFieldWidgetType getWidgetType(){
    return widgetType;
}


public boolean isAdvancedField(){
    return advancedField;
}


public boolean isKey(){
    return key;
}


public DocumentValidStatus getValidStatus(){
    return validStatus;
}


public boolean isPublicField(){
    return publicField;
}


public DocumentFieldWarning getFieldWarning(){
    return fieldWarning;
}


public void setReadonly(LogicExpressionResult readonly,ReasonSupplier reason){
    this.readonly = readonly;
    readonlyReason = reason;
    if (logger.isTraceEnabled()) {
        logger.trace("collect {} readonly: {} -- {}", fieldName, readonly, reason == null ? null : reason.get());
    }
}


public Object getValue(){
    return value;
}


public ReasonSupplier getReadonlyReason(){
    return readonlyReason;
}


public void putDebugProperty(String name,Object value){
    if (debugProperties == null) {
        debugProperties = new LinkedHashMap<>();
    }
    debugProperties.put(name, value);
}


public ReasonSupplier getDisplayedReason(){
    return displayedReason;
}


public void mergeFrom(IDocumentFieldChangedEvent fromEvent){
    if (fromEvent.isValueSet()) {
        valueSet = true;
        value = fromEvent.getValue();
        // N/A
        valueReason = null;
    }
}


public void setValue(Object value,ReasonSupplier reason){
    valueSet = true;
    this.value = value;
    valueReason = reason;
    if (logger.isTraceEnabled()) {
        logger.trace("collect {} value: {} -- {}", fieldName, value, reason == null ? null : reason.get());
    }
}


@Override
public String toString(){
    final ToStringHelper toStringBuilder = MoreObjects.toStringHelper(this).omitNullValues().add("fieldName", fieldName).add("key", key ? Boolean.TRUE : null).add("privateField", !publicField ? Boolean.TRUE : null).add("advancedField", advancedField ? Boolean.TRUE : null).add("widgetType", widgetType).add("validStatus", validStatus);
    if (valueSet) {
        toStringBuilder.add("value", value == null ? "<NULL>" : value);
        toStringBuilder.add("valueReason", valueReason);
    }
    if (readonly != null) {
        toStringBuilder.add("readonly", readonly);
        toStringBuilder.add("readonlyReason", readonlyReason);
    }
    if (mandatory != null) {
        toStringBuilder.add("mandatory", mandatory);
        toStringBuilder.add("mandatoryReason", mandatoryReason);
    }
    if (displayed != null) {
        toStringBuilder.add("displayed", displayed);
        toStringBuilder.add("displayedReason", displayedReason);
    }
    if (lookupValuesStale != null) {
        toStringBuilder.add("lookupValuesStale", lookupValuesStale);
        toStringBuilder.add("lookupValuesStaleReason", lookupValuesStaleReason);
    }
    if (debugProperties != null && !debugProperties.isEmpty()) {
        toStringBuilder.add("debugProperties", debugProperties);
    }
    return toStringBuilder.toString();
}


public String getFieldName(){
    return fieldName;
}


}