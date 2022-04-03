package de.metas.ui.web.document.filter.json;
 import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import de.metas.process.BarcodeScannerType;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterInlineRenderMode;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
import lombok.NonNull;
import lombok.Value;
@Value
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONDocumentFilterDescriptor {

@JsonProperty("filterId")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String filterId;

@JsonProperty("caption")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String caption;

@JsonProperty("inlineRenderMode")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  DocumentFilterInlineRenderMode inlineRenderMode;

@JsonProperty("barcodeScannerType")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  BarcodeScannerType barcodeScannerType;

@JsonProperty("parametersLayoutType")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  PanelLayoutType parametersLayoutType;

@JsonProperty("parameters")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONDocumentFilterParamDescriptor> parameters;

@JsonProperty("includedFilters")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONDocumentFilterDescriptor> includedFilters;

 private  Map<String,Object> debugProperties;


@JsonAnySetter
public void putDebugProperty(String name,Object value){
    debugProperties.put(name, value);
}


@JsonAnyGetter
public Map<String,Object> getDebugProperties(){
    return debugProperties;
}


public List<JSONDocumentFilterDescriptor> ofCollection(Collection<DocumentFilterDescriptor> filters,JSONDocumentLayoutOptions options){
    if (filters == null || filters.isEmpty()) {
        return ImmutableList.of();
    }
    final ImmutableList<DocumentFilterDescriptor> filtersOrdered = filters.stream().sorted(Comparator.comparing(DocumentFilterDescriptor::getSortNo)).collect(ImmutableList.toImmutableList());
    final ImmutableList<JSONDocumentFilterDescriptor> defaultFiltersList = filtersOrdered.stream().filter(filter -> !filter.isFrequentUsed()).map(filter -> new JSONDocumentFilterDescriptor(filter, options)).collect(ImmutableList.toImmutableList());
    JSONDocumentFilterDescriptor defaultFiltersGroup = !defaultFiltersList.isEmpty() ? new JSONDocumentFilterDescriptor("Filter", defaultFiltersList) : null;
    final ImmutableList.Builder<JSONDocumentFilterDescriptor> result = ImmutableList.builder();
    for (final DocumentFilterDescriptor filter : filtersOrdered) {
        if (filter.isFrequentUsed()) {
            result.add(new JSONDocumentFilterDescriptor(filter, options));
        } else if (defaultFiltersGroup != null) {
            result.add(defaultFiltersGroup);
            defaultFiltersGroup = null;
        }
    }
    if (defaultFiltersGroup != null) {
        result.add(defaultFiltersGroup);
        defaultFiltersGroup = null;
    }
    return result.build();
}


}