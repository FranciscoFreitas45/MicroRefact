package DTO;
 import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.process.view.ViewActionDescriptorsList;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.NonNull;
public class HUEditorViewBuilder {

 private  ViewId parentViewId;

 private  DocumentId parentRowId;

 private  ViewId viewId;

 private  JSONViewDataType viewType;

 private  String referencingTableName;

 private  Set<DocumentPath> referencingDocumentPaths;

 private  ViewActionDescriptorsList actions;

 private  List<RelatedProcessDescriptor> additionalRelatedProcessDescriptors;

 private  DocumentFilterList stickyFilters;

 private  DocumentFilterList filters;

 private  DocumentFilterDescriptorsProvider filterDescriptors;

 private  ArrayList<DocumentQueryOrderBy> orderBys;

 private  LinkedHashMap<String,Object> parameters;

 private  HUEditorViewRepository huEditorViewRepository;

 private  boolean useAutoFilters;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public String getReferencingTableName(){
    return referencingTableName;
}


public DocumentId getParentRowId(){
    return parentRowId;
}


public ViewActionDescriptorsList getActions(){
    return actions;
}


public JSONViewDataType getViewType(){
    return viewType;
}


public Set<DocumentPath> getReferencingDocumentPaths(){
    return referencingDocumentPaths == null ? ImmutableSet.of() : ImmutableSet.copyOf(referencingDocumentPaths);
}


public DocumentFilterList getFilters(){
    return filters != null ? filters : DocumentFilterList.EMPTY;
}


public DocumentFilterList getStickyFilters(){
    return stickyFilters != null ? stickyFilters : DocumentFilterList.EMPTY;
}


public T getParameter(String name){
    @SuppressWarnings("unchecked")
    final T value = (T) parameters.get(name);
    return value;
}


public DocumentFilterDescriptorsProvider getFilterDescriptors(){
    return filterDescriptors;
}


public ImmutableList<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors(){
    return additionalRelatedProcessDescriptors != null && !additionalRelatedProcessDescriptors.isEmpty() ? ImmutableList.copyOf(additionalRelatedProcessDescriptors) : ImmutableList.of();
}


public DocumentQueryOrderByList getOrderBys(){
    return DocumentQueryOrderByList.ofList(orderBys);
}


public ViewId getParentViewId(){
    return parentViewId;
}


public ImmutableMap<String,Object> getParameters(){
    return parameters != null ? ImmutableMap.copyOf(parameters) : ImmutableMap.of();
}


@NonNull
public ViewId getViewId(){
    return viewId;
}


public HUEditorView build(){
    return new HUEditorView(this);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/build"))

HUEditorView aux = restTemplate.getForObject(builder.toUriString(),HUEditorView.class);
return aux;
}


}