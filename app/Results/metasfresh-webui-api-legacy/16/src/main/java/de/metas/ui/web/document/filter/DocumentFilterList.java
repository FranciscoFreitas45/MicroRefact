package de.metas.ui.web.document.filter;
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
@EqualsAndHashCode
@ToString
public class DocumentFilterList {

 public  DocumentFilterList EMPTY;

 private  ImmutableMap<String,DocumentFilter> filtersById;


public int getParamValueAsInt(String filterId,String parameterName,int defaultValue){
    final DocumentFilter filter = getFilterByIdOrNull(filterId);
    if (filter == null) {
        return defaultValue;
    }
    return filter.getParameterValueAsInt(parameterName, defaultValue);
}


public DocumentFilterList mergeWith(DocumentFilter filter){
    if (isEmpty()) {
        return of(filter);
    } else {
        final LinkedHashMap<String, DocumentFilter> filtersByIdNew = new LinkedHashMap<>(this.filtersById);
        filtersByIdNew.put(filter.getFilterId(), filter);
        return ofMap(filtersByIdNew);
    }
}


public Optional<DocumentFilter> getFilterById(String filterId){
    final DocumentFilter filter = getFilterByIdOrNull(filterId);
    return Optional.ofNullable(filter);
}


public void forEach(Consumer<DocumentFilter> consumer){
    filtersById.values().forEach(consumer);
}


public boolean isEmpty(){
    return filtersById.isEmpty();
}


public ImmutableList<DocumentFilter> toList(){
    return ImmutableList.copyOf(filtersById.values());
}


public DocumentFilterList ofMap(Map<String,DocumentFilter> filtersById){
    return !filtersById.isEmpty() ? new DocumentFilterList(ImmutableMap.copyOf(filtersById)) : EMPTY;
}


public DocumentFilterList retainOnlyNonFacetFilters(){
    return filtering(filter -> !filter.isFacetFilter());
}


public DocumentFilterList retainOnlyFacetFilters(){
    return filtering(DocumentFilter::isFacetFilter);
}


public DocumentFilter getFilterByIdOrNull(String filterId){
    return filtersById.get(filterId);
}


public boolean containsFilterById(String filterId){
    return getFilterByIdOrNull(filterId) != null;
}


public Collector<DocumentFilter,?,DocumentFilterList> toDocumentFilterList(){
    return GuavaCollectors.collectUsingListAccumulator(DocumentFilterList::ofList);
}


public Stream<DocumentFilter> stream(){
    return filtersById.values().stream();
}


public DocumentFilterList of(DocumentFilter filters){
    return ofList(Arrays.asList(filters));
}


public boolean equals(DocumentFilterList list1,DocumentFilterList list2){
    return Objects.equals(list1, list2);
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


public DocumentFilterList filtering(Predicate<DocumentFilter> predicate){
    if (isEmpty()) {
        return this;
    }
    final ImmutableMap<String, DocumentFilter> newFiltersById = filtersById.entrySet().stream().filter(entry -> predicate.test(entry.getValue())).collect(GuavaCollectors.toImmutableMap());
    if (newFiltersById.isEmpty()) {
        return EMPTY;
    } else if (newFiltersById.size() == filtersById.size()) {
        return this;
    } else {
        return new DocumentFilterList(newFiltersById);
    }
}


public DocumentFilterList ofList(Collection<DocumentFilter> list){
    return list != null && !list.isEmpty() ? new DocumentFilterList(Maps.uniqueIndex(list, DocumentFilter::getFilterId)) : EMPTY;
}


}