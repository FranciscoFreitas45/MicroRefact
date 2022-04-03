package de.metas.ui.web.window.datatypes.json;
 import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.process.IProcessInstanceParameter;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.Password;
import de.metas.ui.web.window.descriptor.ViewEditorRenderMode;
import de.metas.ui.web.window.model.DocumentFieldChange;
import de.metas.ui.web.window.model.DocumentValidStatus;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.ui.web.window.model.IDocumentFieldView;
import io.swagger.annotations.ApiModel;
@ApiModel("document-field")
@JsonPropertyOrder({ "field", "value", "value-reason", "readonly", "readonly-reason", "mandatory", "mandatory-reason", "displayed", "displayed-reason", "lookupValuesStale", "lookupValuesStale-reason", "valid", "validReason" })
@SuppressWarnings("serial")
public class JSONDocumentField implements Serializable{

@JsonProperty("field")
@JsonInclude(JsonInclude.Include.ALWAYS)
 private  String field;

 public  String FIELD_VALUE_ID;

@JsonProperty("widgetType")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  JSONLayoutWidgetType widgetType;

@JsonProperty("value")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Object value;

@JsonProperty("value-reason")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String valueReason;

@JsonProperty("readonly")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean readonly;

@JsonProperty("readonly-reason")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String readonlyReason;

@JsonProperty("mandatory")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean mandatory;

@JsonProperty("mandatory-reason")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String mandatoryReason;

@JsonProperty("displayed")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean displayed;

@JsonProperty("displayed-reason")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String displayedReason;

@JsonProperty("lookupValuesStale")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean lookupValuesStale;

@JsonProperty("lookupValuesStale-reason")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String lookupValuesStaleReason;

@JsonProperty("validStatus")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  DocumentValidStatus validStatus;

@JsonProperty("viewEditorRenderMode")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String viewEditorRenderMode;

@JsonProperty("warning")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  JSONDocumentFieldWarning fieldWarning;

 private  Map<String,Object> otherProperties;


public JSONDocumentField idField(Object jsonValue){
    // N/A
    final String reason = null;
    return new JSONDocumentField(FIELD_VALUE_ID, JSONLayoutWidgetType.Integer).setValue(jsonValue, reason);
}


public boolean isReadonly(){
    return readonly != null && readonly;
}


public JSONDocumentField setMandatory(boolean mandatory){
    // N/A
    final String reason = null;
    setMandatory(mandatory, reason);
    return this;
}


public String getValueReason(){
    return valueReason;
}


public Boolean getDisplayed(){
    return displayed;
}


public void putDebugProperties(Map<String,Object> debugProperties){
    if (debugProperties == null || debugProperties.isEmpty()) {
        return;
    }
    for (final Map.Entry<String, Object> e : debugProperties.entrySet()) {
        putDebugProperty(e.getKey(), e.getValue());
    }
}


public String getLookupValuesStaleReason(){
    return lookupValuesStaleReason;
}


public JSONDocumentField ofDocumentFieldChangedEvent(DocumentFieldChange event,JSONOptions jsonOpts){
    final JSONLayoutWidgetType widgetType = JSONLayoutWidgetType.fromNullable(event.getWidgetType());
    final JSONDocumentField jsonField = new JSONDocumentField(event.getFieldName(), widgetType);
    if (event.isValueSet()) {
        jsonField.setValue(event.getValueAsJsonObject(jsonOpts), ReasonSupplier.toDebugString(event.getValueReason()));
    }
    final LogicExpressionResult readonly = event.getReadonly();
    if (readonly != null) {
        jsonField.setReadonly(readonly.booleanValue(), ReasonSupplier.toDebugString(event.getReadonlyReason()));
    }
    final LogicExpressionResult mandatory = event.getMandatory();
    if (mandatory != null) {
        jsonField.setMandatory(mandatory.booleanValue(), ReasonSupplier.toDebugString(event.getMandatoryReason()));
    }
    final LogicExpressionResult displayed = event.getDisplayed();
    if (displayed != null) {
        jsonField.setDisplayed(displayed.booleanValue(), ReasonSupplier.toDebugString(event.getDisplayedReason()));
    }
    final Boolean lookupValuesStale = event.getLookupValuesStale();
    if (lookupValuesStale != null) {
        jsonField.setLookupValuesStale(lookupValuesStale, ReasonSupplier.toDebugString(event.getLookupValuesStaleReason()));
    }
    final DocumentValidStatus validStatus = event.getValidStatus();
    if (validStatus != null) {
        jsonField.setValidStatus(validStatus);
    }
    jsonField.setFieldWarning(JSONDocumentFieldWarning.ofNullable(event.getFieldWarning(), jsonOpts.getAdLanguage()));
    jsonField.putDebugProperties(event.getDebugProperties());
    return jsonField;
}


public Boolean getReadonly(){
    return readonly;
}


public void unboxPasswordField(){
    final Object value = this.value;
    if (value instanceof Password) {
        this.value = ((Password) value).getAsString();
    }
}


public JSONDocumentField setLookupValuesStale(boolean lookupValuesStale,String reason){
    this.lookupValuesStale = lookupValuesStale;
    lookupValuesStaleReason = reason;
    return this;
}


public JSONDocumentField setFieldWarning(JSONDocumentFieldWarning fieldWarning){
    this.fieldWarning = fieldWarning;
    return this;
}


public JSONDocumentField setValidStatus(DocumentValidStatus validStatus){
    this.validStatus = validStatus;
    return this;
}


public JSONDocumentField setDisplayed(boolean displayed){
    // N/A
    final String reason = null;
    setDisplayed(displayed, reason);
    return this;
}


public Boolean getLookupValuesStale(){
    return lookupValuesStale;
}


public JSONDocumentField ofProcessParameter(IProcessInstanceParameter parameter,JSONOptions jsonOpts){
    final String name = parameter.getParameterName();
    final JSONLayoutWidgetType jsonWidgetType = JSONLayoutWidgetType.fromNullable(parameter.getWidgetType());
    final Object valueJSON = parameter.getValueAsJsonObject(jsonOpts);
    // N/A
    final String reason = null;
    final JSONDocumentField jsonField = new JSONDocumentField(name, jsonWidgetType).setValue(valueJSON, reason).setReadonly(parameter.getReadonly()).setMandatory(parameter.getMandatory()).setDisplayed(parameter.getDisplayed()).setValidStatus(parameter.getValidStatus());
    if (WindowConstants.isProtocolDebugging()) {
        jsonField.putDebugProperty(DocumentFieldChange.DEBUGPROPERTY_FieldInfo, parameter.toString());
    }
    return jsonField;
}


public Boolean getMandatory(){
    return mandatory;
}


public String getMandatoryReason(){
    return mandatoryReason;
}


@JsonAnyGetter
public Map<String,Object> getOtherProperties(){
    return otherProperties;
}


public JSONDocumentField ofNameAndValue(String fieldName,Object jsonValue){
    // N/A
    final String reason = null;
    // N/A
    final JSONLayoutWidgetType widgetType = null;
    return new JSONDocumentField(fieldName, widgetType).setValue(jsonValue, reason);
}


public String getField(){
    return field;
}


public JSONDocumentField setReadonly(boolean readonly){
    // N/A
    final String reason = null;
    setReadonly(readonly, reason);
    return this;
}


public Object getValue(){
    return value;
}


public String getReadonlyReason(){
    return readonlyReason;
}


public JSONDocumentField putDebugProperty(String name,Object jsonValue){
    otherProperties.put("debug-" + name, jsonValue);
    return this;
}


public JSONDocumentField ofDocumentField(IDocumentFieldView field,JSONOptions jsonOpts){
    final String name = field.getFieldName();
    final JSONLayoutWidgetType jsonWidgetType = JSONLayoutWidgetType.fromNullable(field.getWidgetType());
    final Object valueJSON = field.getValueAsJsonObject(jsonOpts);
    // N/A
    final String reason = null;
    final JSONDocumentField jsonField = new JSONDocumentField(name, jsonWidgetType).setValue(valueJSON, reason).setReadonly(field.getReadonly()).setMandatory(field.getMandatory()).setDisplayed(field.getDisplayed()).setValidStatus(field.getValidStatus());
    if (field.isLookupValuesStale()) {
        jsonField.setLookupValuesStale(true, reason);
    }
    if (WindowConstants.isProtocolDebugging()) {
        jsonField.putDebugProperty(DocumentFieldChange.DEBUGPROPERTY_FieldInfo, field.toString());
    }
    return jsonField;
}


public String getDisplayedReason(){
    return displayedReason;
}


public JSONDocumentField setWidgetType(JSONLayoutWidgetType widgetType){
    this.widgetType = widgetType;
    return this;
}


public JSONDocumentField setValue(Object jsonValue,String reason){
    value = JSONNullValue.wrapIfNull(jsonValue);
    valueReason = reason;
    return this;
}


public JSONDocumentField setViewEditorRenderMode(ViewEditorRenderMode viewEditorRenderMode){
    this.viewEditorRenderMode = viewEditorRenderMode != null ? viewEditorRenderMode.toJson() : null;
    return this;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("field", field).add("value", value).add("value-reason", valueReason).add("mandatory", mandatory).add("mandatory-reason", mandatoryReason).add("readonly", readonly).add("readonly-reason", readonlyReason).add("displayed", displayed).add("displayed-reason", displayedReason).add("lookupValuesStale", lookupValuesStale).add("lookupValuesStale-reason", lookupValuesStaleReason).add("validStatus", validStatus).add("otherProperties", otherProperties).toString();
}


@JsonAnySetter
public void putOtherProperty(String name,Object jsonValue){
    otherProperties.put(name, jsonValue);
}


}