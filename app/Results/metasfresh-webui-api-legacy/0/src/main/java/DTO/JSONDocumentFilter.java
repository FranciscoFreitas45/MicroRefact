package DTO;
 import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.DocumentFilterParam;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import lombok.NonNull;
import lombok.Value;
public class JSONDocumentFilter {

 private  String filterId;

 private  String caption;

 private  List<JSONDocumentFilterParam> parameters;


public DocumentFilter unwrapAsGenericFilter(JSONDocumentFilter jsonFilter){
    return DocumentFilter.builder().setFilterId(jsonFilter.getFilterId()).setParameters(jsonFilter.getParameters().stream().map(jsonParam -> DocumentFilterParam.builder().setFieldName(jsonParam.getParameterName()).setValue(jsonParam.getValue()).setValueTo(jsonParam.getValueTo()).setOperator().build()).collect(GuavaCollectors.toImmutableList())).build();
}


public DocumentFilter unwrapUsingDescriptor(JSONDocumentFilter jsonFilter,DocumentFilterDescriptor filterDescriptor){
    final DocumentFilter.Builder filter = DocumentFilter.builder().setFilterId(jsonFilter.getFilterId()).setFacetFilter(filterDescriptor.isFacetFilter());
    final Map<String, JSONDocumentFilterParam> jsonParams = Maps.uniqueIndex(jsonFilter.getParameters(), JSONDocumentFilterParam::getParameterName);
    for (final DocumentFilterParamDescriptor paramDescriptor : filterDescriptor.getParameters()) {
        final String parameterName = paramDescriptor.getParameterName();
        final JSONDocumentFilterParam jsonParam = jsonParams.get(parameterName);
        // If parameter is missing: skip it if no required, else throw exception
        if (jsonParam == null) {
            if (paramDescriptor.isMandatory()) {
                throw new IllegalArgumentException("Parameter '" + parameterName + "' was not provided");
            }
            continue;
        }
        final Object value = paramDescriptor.convertValueFromJson(jsonParam.getValue());
        final Object valueTo = paramDescriptor.convertValueFromJson(jsonParam.getValueTo());
        // If there was no value/valueTo provided: skip it if no required, else throw exception
        if (value == null && valueTo == null) {
            if (paramDescriptor.isMandatory()) {
                throw new IllegalArgumentException("Parameter '" + parameterName + "' has no value");
            }
            continue;
        }
        filter.addParameter(DocumentFilterParam.builder().setFieldName(paramDescriptor.getFieldName()).setOperator(paramDescriptor.getOperator()).setValue(value).setValueTo(valueTo).build());
    }
    for (final DocumentFilterParam internalParam : filterDescriptor.getInternalParameters()) {
        filter.addInternalParameter(internalParam);
    }
    return filter.build();
}


public JSONDocumentFilter of(DocumentFilter filter,JSONOptions jsonOpts){
    final String filterId = filter.getFilterId();
    final List<JSONDocumentFilterParam> jsonParameters = filter.getParameters().stream().filter(filterParam -> !filter.isInternalParameter(filterParam.getFieldName())).map(filterParam -> JSONDocumentFilterParam.of(filterParam, jsonOpts)).filter(Optional::isPresent).map(Optional::get).collect(GuavaCollectors.toImmutableList());
    return new JSONDocumentFilter(filterId, filter.getCaption(jsonOpts.getAdLanguage()), jsonParameters);
}


public DocumentFilterList unwrapList(List<JSONDocumentFilter> jsonFilters,DocumentFilterDescriptorsProvider filterDescriptorProvider){
    if (jsonFilters == null || jsonFilters.isEmpty()) {
        return DocumentFilterList.EMPTY;
    }
    return jsonFilters.stream().map(jsonFilter -> unwrap(jsonFilter, filterDescriptorProvider)).filter(Objects::nonNull).collect(DocumentFilterList.toDocumentFilterList());
}


public List<JSONDocumentFilter> ofList(DocumentFilterList filters,JSONOptions jsonOpts){
    if (filters == null || filters.isEmpty()) {
        return ImmutableList.of();
    }
    return filters.stream().map(filter -> of(filter, jsonOpts)).collect(GuavaCollectors.toImmutableList());
}


public DocumentFilter unwrap(JSONDocumentFilter jsonFilter,DocumentFilterDescriptorsProvider filterDescriptorProvider){
    final String filterId = jsonFilter.getFilterId();
    final DocumentFilterDescriptor filterDescriptor = filterDescriptorProvider.getByFilterIdOrNull(filterId);
    // Ad-hoc filters (e.g. zoom references)
    if (filterDescriptor == null) {
        return unwrapAsGenericFilter(jsonFilter);
    } else // Filter with descriptor
    {
        return unwrapUsingDescriptor(jsonFilter, filterDescriptor);
    }
}


}