package DTO;
 import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.metas.util.GuavaCollectors;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
public class DocumentFilterList {

 public  DocumentFilterList EMPTY;

 private  ImmutableMap<String,DocumentFilter> filtersById;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://16";


public int getParamValueAsInt(String filterId,String parameterName,int defaultValue){
    final DocumentFilter filter = getFilterByIdOrNull(filterId);
    if (filter == null) {
        return defaultValue;
    }
    return filter.getParameterValueAsInt(parameterName, defaultValue);
}


public Optional<DocumentFilter> getFilterById(String filterId){
    final DocumentFilter filter = getFilterByIdOrNull(filterId);
    return Optional.ofNullable(filter);
}


public DocumentFilter getFilterByIdOrNull(String filterId){
    return filtersById.get(filterId);
}


public boolean getParamValueAsBoolean(String filterId,String parameterName,boolean defaultValue){
    final DocumentFilter filter = getFilterByIdOrNull(filterId);
    if (filter == null) {
        return defaultValue;
    }
    return filter.getParameterValueAsBoolean(parameterName, defaultValue);
}


@Nullable
public String getParamValueAsString(String filterId,String parameterName){
    final DocumentFilter filter = getFilterByIdOrNull(filterId);
    if (filter == null) {
        return null;
    }
    return filter.getParameterValueAsString(parameterName);
}


public DocumentFilterList of(DocumentFilter filters){
    return ofList(Arrays.asList(filters));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("filters",filters);
DocumentFilterList aux = restTemplate.getForObject(builder.toUriString(),DocumentFilterList.class);
return aux;
}


public DocumentFilterList mergeWith(DocumentFilter filter){
    if (isEmpty()) {
        return of(filter);
    } else {
        final LinkedHashMap<String, DocumentFilter> filtersByIdNew = new LinkedHashMap<>(this.filtersById);
        filtersByIdNew.put(filter.getFilterId(), filter);
        return ofMap(filtersByIdNew);
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/mergeWith"))

.queryParam("filter",filter);
DocumentFilterList aux = restTemplate.getForObject(builder.toUriString(),DocumentFilterList.class);
return aux;
}


}