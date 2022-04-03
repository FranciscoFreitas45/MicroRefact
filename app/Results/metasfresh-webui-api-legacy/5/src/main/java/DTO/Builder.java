package DTO;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.json.JSONDocumentFilter;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.process.view.ViewActionDescriptorsFactory;
import de.metas.ui.web.process.view.ViewActionDescriptorsList;
import de.metas.ui.web.view.json.JSONFilterViewRequest;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.util.Check;
import de.metas.util.collections.CollectionUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;
public class Builder {

 private  ViewId viewId;

 private  JSONViewDataType viewType;

 private  ViewProfileId profileId;

 private  ViewId parentViewId;

 private  DocumentId parentRowId;

 private  Set<DocumentPath> referencingDocumentPaths;

 private  LinkedHashSet<Integer> filterOnlyIds;

 private  ArrayList<DocumentFilter> stickyFilters;

 private  WrappedDocumentFilterList filters;

 private  boolean useAutoFilters;

 private  ViewActionDescriptorsList actions;

 private  List<RelatedProcessDescriptor> additionalRelatedProcessDescriptors;

 private  LinkedHashMap<String,Object> parameters;

 private  boolean applySecurityRestrictions;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://16";


public DocumentId getParentRowId(){
    return parentRowId;
}


public ViewActionDescriptorsList getActions(){
    return actions;
}


public JSONViewDataType getViewType(){
    return viewType;
}


public ImmutableSet<DocumentPath> getReferencingDocumentPaths(){
    return referencingDocumentPaths == null ? ImmutableSet.of() : ImmutableSet.copyOf(referencingDocumentPaths);
}


public WrappedDocumentFilterList getFilters(){
    return filters != null ? filters : WrappedDocumentFilterList.EMPTY;
}


public DocumentFilterList getStickyFilters(){
    return DocumentFilterList.ofList(stickyFilters);
}


public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors(){
    return additionalRelatedProcessDescriptors;
}


public ViewProfileId getProfileId(){
    return profileId;
}


public ViewId getParentViewId(){
    return parentViewId;
}


public ImmutableMap<String,Object> getParameters(){
    return parameters != null ? ImmutableMap.copyOf(parameters) : ImmutableMap.of();
}


public ImmutableSet<Integer> getFilterOnlyIds(){
    return filterOnlyIds == null ? ImmutableSet.of() : ImmutableSet.copyOf(filterOnlyIds);
}


public ViewId getViewId(){
    return viewId;
}


public Builder addFilterOnlyId(int filterOnlyId){
    if (filterOnlyIds == null) {
        filterOnlyIds = new LinkedHashSet<>();
    }
    filterOnlyIds.add(filterOnlyId);
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addFilterOnlyId"))

.queryParam("filterOnlyId",filterOnlyId);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


}