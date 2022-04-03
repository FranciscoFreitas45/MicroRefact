package DTO;
 import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntFunction;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import de.metas.util.lang.RepoIdAware;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
public class Builder {

 private  String filterId;

 private  ITranslatableString caption;

 private  boolean facetFilter;

 private  ArrayList<DocumentFilterParam> parameters;

 private  Set<String> internalParameterNames;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://16";


public Builder setFilterId(String filterId){
    this.filterId = filterId;
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFilterId"))

.queryParam("filterId",filterId);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public DocumentFilter build(){
    return new DocumentFilter(this);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/build"))

DocumentFilter aux = restTemplate.getForObject(builder.toUriString(),DocumentFilter.class);
return aux;
}


public Builder setFilters(DocumentFilterList filters){
    _filtersById.clear();
    filters.forEach(filter -> _filtersById.put(filter.getFilterId(), filter));
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFilters"))

.queryParam("filters",filters);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public Builder addFiltersIfAbsent(Collection<DocumentFilter> filters){
    filters.forEach(filter -> _filtersById.putIfAbsent(filter.getFilterId(), filter));
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addFiltersIfAbsent"))

.queryParam("filters",filters);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public Builder addStickyFilter(DocumentFilter stickyFilter){
    if (stickyFilter == null) {
        return this;
    }
    if (_stickyFiltersById == null) {
        _stickyFiltersById = new LinkedHashMap<>();
    }
    _stickyFiltersById.put(stickyFilter.getFilterId(), stickyFilter);
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addStickyFilter"))

.queryParam("stickyFilter",stickyFilter);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public Builder setType(IViewRowType type){
    this.type = type;
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setType"))

.queryParam("type",type);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public Builder setParentRowId(DocumentId parentRowId){
    this.parentRowId = parentRowId;
    _rowIdEffective = null;
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setParentRowId"))

.queryParam("parentRowId",parentRowId);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public Builder setRowId(DocumentId rowId){
    this.rowId = rowId;
    _rowIdEffective = null;
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRowId"))

.queryParam("rowId",rowId);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public Builder putFieldValue(String fieldName,Object jsonValue){
    if (JSONNullValue.isNull(jsonValue)) {
        values.remove(fieldName);
    } else {
        values.put(fieldName, jsonValue);
    }
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/putFieldValue"))

.queryParam("fieldName",fieldName);
.queryParam("jsonValue",jsonValue);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public DocumentId getRowId(){
    if (_rowIdEffective == null) {
        if (rowId == null) {
            throw new IllegalStateException("No rowId was provided for " + this);
        }
        if (isRootRow()) {
            _rowIdEffective = rowId;
        } else {
            // NOTE: we have to do this because usually, the root row can have the same ID as one of the included rows,
            // because the root/aggregated rows are build on demand and they don't really exist in database.
            // Also see https://github.com/metasfresh/metasfresh-webui-frontend/issues/835#issuecomment-307783959
            _rowIdEffective = rowId.toIncludedRowId();
        }
    }
    return _rowIdEffective;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRowId"))

DocumentId aux = restTemplate.getForObject(builder.toUriString(),DocumentId.class);
return aux;
}


public boolean isRootRow(){
    return getParentRowId() == null;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isRootRow"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public Builder addIncludedRow(IViewRow includedRow){
    if (includedRows == null) {
        includedRows = new ArrayList<>();
    }
    includedRows.add(includedRow);
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addIncludedRow"))

.queryParam("includedRow",includedRow);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public LookupValue getFieldValueAsLookupValue(String fieldName){
    return LookupValue.cast(values.get(fieldName));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFieldValueAsLookupValue"))

.queryParam("fieldName",fieldName);
LookupValue aux = restTemplate.getForObject(builder.toUriString(),LookupValue.class);
return aux;
}


}