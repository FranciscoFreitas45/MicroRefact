package de.metas.ui.web.window.datatypes.json;
 import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import de.metas.ui.web.devices.JSONDeviceDescriptor;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.FieldType;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.descriptor.factory.NewRecordDescriptorsProvider;
import de.metas.util.GuavaCollectors;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
@ApiModel("field")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONDocumentLayoutElementField {

 private  Map<FieldType,JSONFieldType> fieldType2json;

 private  Map<LookupSource,JSONLookupSource> lookupSource2json;

@JsonProperty(value = "field", required = true)
@Getter
 private  String field;

@JsonProperty(value = "caption", required = true)
 private  String caption;

@JsonProperty("type")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  JSONFieldType type;

@JsonProperty("tooltipIconName")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String tooltipIconName;

@JsonProperty("emptyText")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String emptyText;

@JsonProperty("clearValueText")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String clearValueText;

@JsonProperty("devices")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONDeviceDescriptor> devices;

@JsonProperty("newRecordWindowId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String newRecordWindowId;

@JsonProperty("newRecordCaption")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String newRecordCaption;

@JsonProperty("source")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  JSONLookupSource source;

@JsonProperty("lookupSearchStringMinLength")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Integer lookupSearchStringMinLength;

@JsonProperty("lookupSearchStartDelayMillis")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Integer lookupSearchStartDelayMillis;

@JsonProperty("supportZoomInto")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean supportZoomInto;


public Set<JSONDocumentLayoutElementField> ofSet(Set<DocumentLayoutElementFieldDescriptor> fieldDescriptors,JSONDocumentLayoutOptions options){
    return fieldDescriptors.stream().map(fieldDescriptor -> of(fieldDescriptor, options)).collect(GuavaCollectors.toImmutableSet());
}


public JSONLookupSource fromNullable(LookupSource lookupSource){
    if (lookupSource == null) {
        return null;
    }
    final JSONLookupSource jsonLookupSource = lookupSource2json.get(lookupSource);
    if (jsonLookupSource == null) {
        throw new IllegalArgumentException("Cannot convert " + lookupSource + " to " + JSONLookupSource.class);
    }
    return jsonLookupSource;
}


public JSONDocumentLayoutElementField of(DocumentLayoutElementFieldDescriptor fieldDescriptor,JSONDocumentLayoutOptions jsonOpts){
    return new JSONDocumentLayoutElementField(fieldDescriptor, jsonOpts);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("field", field).add("type", type).add("source", source).add("emptyText", emptyText).add("clearValueText", clearValueText).add("actions", devices.isEmpty() ? null : devices).add("newRecordWindowId", newRecordWindowId).add("supportZoomInto", supportZoomInto).toString();
}


public DocumentEntityDescriptor findNewRecordEntityDescriptor(String lookupTableName,JSONDocumentLayoutOptions jsonOpts){
    if (lookupTableName == null) {
        return null;
    }
    final NewRecordDescriptorsProvider newRecordDescriptorsProvider = jsonOpts.getNewRecordDescriptorsProvider();
    if (newRecordDescriptorsProvider == null) {
        return null;
    }
    return newRecordDescriptorsProvider.getNewRecordEntityDescriptorIfAvailable(lookupTableName);
}


}