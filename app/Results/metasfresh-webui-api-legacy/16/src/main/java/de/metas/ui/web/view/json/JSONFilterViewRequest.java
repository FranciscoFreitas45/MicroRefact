package de.metas.ui.web.view.json;
 import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.json.JSONDocumentFilter;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@EqualsAndHashCode
@ToString
public class JSONFilterViewRequest {

@JsonProperty("filters")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
 private  List<JSONDocumentFilter> filters;


public DocumentFilterList getFiltersUnwrapped(DocumentFilterDescriptorsProvider descriptors){
    return JSONDocumentFilter.unwrapList(filters, descriptors);
}


}