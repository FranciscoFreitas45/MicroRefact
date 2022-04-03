package DTO;
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
public class JSONDocumentField implements Serializable{

 private  String field;

 public  String FIELD_VALUE_ID;

 private  JSONLayoutWidgetType widgetType;

 private  Object value;

 private  String valueReason;

 private  Boolean readonly;

 private  String readonlyReason;

 private  Boolean mandatory;

 private  String mandatoryReason;

 private  Boolean displayed;

 private  String displayedReason;

 private  Boolean lookupValuesStale;

 private  String lookupValuesStaleReason;

 private  DocumentValidStatus validStatus;

 private  String viewEditorRenderMode;

 private  JSONDocumentFieldWarning fieldWarning;

 private  Map<String,Object> otherProperties;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public String getValueReason(){
    return valueReason;
}


public Boolean getDisplayed(){
    return displayed;
}


public String getLookupValuesStaleReason(){
    return lookupValuesStaleReason;
}


public Boolean getReadonly(){
    return readonly;
}


public Boolean getLookupValuesStale(){
    return lookupValuesStale;
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


public String getField(){
    return field;
}


public Object getValue(){
    return value;
}


public String getReadonlyReason(){
    return readonlyReason;
}


public String getDisplayedReason(){
    return displayedReason;
}


public JSONDocumentField idField(Object jsonValue){
    // N/A
    final String reason = null;
    return new JSONDocumentField(FIELD_VALUE_ID, JSONLayoutWidgetType.Integer).setValue(jsonValue, reason);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/idField"))

.queryParam("jsonValue",jsonValue);
JSONDocumentField aux = restTemplate.getForObject(builder.toUriString(),JSONDocumentField.class);
return aux;
}


public JSONDocumentField ofNameAndValue(String fieldName,Object jsonValue){
    // N/A
    final String reason = null;
    // N/A
    final JSONLayoutWidgetType widgetType = null;
    return new JSONDocumentField(fieldName, widgetType).setValue(jsonValue, reason);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofNameAndValue"))

.queryParam("fieldName",fieldName);
.queryParam("jsonValue",jsonValue);
JSONDocumentField aux = restTemplate.getForObject(builder.toUriString(),JSONDocumentField.class);
return aux;
}


}