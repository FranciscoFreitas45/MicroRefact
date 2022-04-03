package de.metas.ui.web.document.filter.json;
 import java.util.Collection;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.process.BarcodeScannerType;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.window.datatypes.Values;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
import de.metas.ui.web.window.datatypes.json.JSONLayoutType;
import de.metas.ui.web.window.datatypes.json.JSONLayoutWidgetType;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.util.GuavaCollectors;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@ToString
public class JSONDocumentFilterParamDescriptor {

@JsonProperty("caption")
 private  String caption;

@JsonProperty("parameterName")
 private  String parameterName;

@JsonProperty("widgetType")
 private  JSONLayoutWidgetType widgetType;

@JsonProperty("multiListValue")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean multiListValue;

@JsonProperty("range")
 private  boolean rangeParameter;

@JsonProperty("defaultValue")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  Object defaultValue;

@JsonProperty("defaultValueTo")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  Object defaultValueTo;

@JsonProperty("mandatory")
 private  boolean mandatory;

@JsonProperty("displayed")
 private  boolean displayed;

@JsonProperty("readonly")
 private  boolean readonly;

@ApiModelProperty(allowEmptyValue = false)
@JsonProperty("type")
 private  JSONLayoutType type;

@JsonProperty("showIncrementDecrementButtons")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean showIncrementDecrementButtons;

@JsonProperty("barcodeScannerType")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  BarcodeScannerType barcodeScannerType;


public JSONLayoutType toJSONLayoutType(JSONLayoutWidgetType widgetType){
    // Checkboxes
    // see https://github.com/metasfresh/metasfresh-webui-api/issues/352
    if (widgetType == JSONLayoutWidgetType.YesNo || widgetType == JSONLayoutWidgetType.Switch) {
        return JSONLayoutType.primaryLongLabels;
    } else // Default "primary"
    // see https://github.com/metasfresh/metasfresh-webui-api/issues/334
    {
        return JSONLayoutType.primary;
    }
}


public JSONDocumentFilterParamDescriptor of(DocumentFilterParamDescriptor param,JSONDocumentLayoutOptions jsonOpts){
    return new JSONDocumentFilterParamDescriptor(param, jsonOpts);
}


public List<JSONDocumentFilterParamDescriptor> ofCollection(Collection<DocumentFilterParamDescriptor> params,JSONDocumentLayoutOptions options){
    return params.stream().map(filter -> of(filter, options)).collect(GuavaCollectors.toImmutableList());
}


}