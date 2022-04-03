package de.metas.ui.web.window.datatypes.json;
 import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableSet;
import de.metas.process.BarcodeScannerType;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.window.datatypes.MediaType;
import de.metas.ui.web.window.descriptor.ButtonFieldActionDescriptor;
import de.metas.ui.web.window.descriptor.ButtonFieldActionDescriptor.ButtonFieldActionType;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.ui.web.window.descriptor.ViewEditorRenderMode;
import de.metas.ui.web.window.descriptor.WidgetSize;
import de.metas.util.GuavaCollectors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NonNull;
import lombok.ToString;
@ApiModel("element")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@ToString
public class JSONDocumentLayoutElement {

@JsonProperty("caption")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String caption;

@JsonProperty("description")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String description;

@JsonProperty("widgetType")
 private  JSONLayoutWidgetType widgetType;

@JsonProperty("allowShowPassword")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean allowShowPassword;

@JsonProperty("multilineText")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean multilineText;

@JsonProperty("multilineTextLines")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Integer multilineTextLines;

@JsonProperty("maxLength")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Integer maxLength;

@JsonProperty("buttonProcessId")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  ProcessId buttonProcessId;

@JsonProperty("barcodeScannerType")
@JsonInclude(JsonInclude.Include.NON_NULL)
 final  BarcodeScannerType barcodeScannerType;

@ApiModelProperty(allowEmptyValue = true)
@JsonProperty("type")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  JSONLayoutType type;

@JsonProperty("size")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  WidgetSize size;

@JsonProperty("gridAlign")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  JSONLayoutAlign gridAlign;

@JsonProperty("viewEditorRenderMode")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String viewEditorRenderMode;

@JsonProperty("sortable")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean viewAllowSorting;

@JsonProperty("restrictToMediaTypes")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  Set<MediaType> restrictToMediaTypes;

@JsonProperty("fields")
@JsonInclude(Include.NON_EMPTY)
 private  Set<JSONDocumentLayoutElementField> fields;


public JSONDocumentLayoutElement fromNullable(DocumentLayoutElementDescriptor element,JSONDocumentLayoutOptions jsonOpts){
    if (element == null) {
        return null;
    }
    return new JSONDocumentLayoutElement(element, jsonOpts);
}


public JSONDocumentLayoutElement debuggingField(String fieldName,DocumentFieldWidgetType widgetType){
    return new JSONDocumentLayoutElement(fieldName, widgetType);
}


public boolean hasFields(){
    return !fields.isEmpty();
}


public List<JSONDocumentLayoutElement> ofList(List<DocumentLayoutElementDescriptor> elements,JSONDocumentLayoutOptions jsonOpts){
    return elements.stream().filter(jsonOpts.documentLayoutElementFilter()).map(element -> new JSONDocumentLayoutElement(element, jsonOpts)).filter(// IMPORTANT: we shall avoid having elements without any field, see https://github.com/metasfresh/metasfresh-webui-frontend/issues/870#issuecomment-307770832
    JSONDocumentLayoutElement::hasFields).collect(GuavaCollectors.toImmutableList());
}


public boolean hasField(List<JSONDocumentLayoutElement> elements,String fieldName){
    return elements.stream().anyMatch(element -> element.hasField(fieldName));
}


}