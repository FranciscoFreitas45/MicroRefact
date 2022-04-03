package de.metas.ui.web.handlingunits;
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


public HUEditorViewBuilder addAdditionalRelatedProcessDescriptors(List<RelatedProcessDescriptor> descriptors){
    if (additionalRelatedProcessDescriptors == null) {
        additionalRelatedProcessDescriptors = new ArrayList<>();
    }
    additionalRelatedProcessDescriptors.addAll(descriptors);
    return this;
}


public HUEditorViewBuilder setFilterDescriptors(DocumentFilterDescriptorsProvider filterDescriptors){
    this.filterDescriptors = filterDescriptors;
    return this;
}


public HUEditorViewBuilder setActions(ViewActionDescriptorsList actions){
    this.actions = actions;
    return this;
}


public boolean isUseAutoFilters(){
    return useAutoFilters;
}


public HUEditorViewBuilder setStickyFilters(DocumentFilterList stickyFilters){
    this.stickyFilters = stickyFilters;
    return this;
}


public String getReferencingTableName(){
    return referencingTableName;
}


public HUEditorViewBuilder orderBy(DocumentQueryOrderBy orderBy){
    if (orderBys == null) {
        orderBys = new ArrayList<>();
    }
    orderBys.add(orderBy);
    return this;
}


public DocumentId getParentRowId(){
    return parentRowId;
}


public ViewActionDescriptorsList getActions(){
    return actions;
}


public HUEditorViewBuilder setParentRowId(DocumentId parentRowId){
    this.parentRowId = parentRowId;
    return this;
}


public HUEditorViewBuilder setParameters(Map<String,Object> parameters){
    parameters.forEach(this::setParameter);
    return this;
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


public HUEditorViewBuilder setParameter(String name,Object value){
    if (value == null) {
        if (parameters != null) {
            parameters.remove(name);
        }
    } else {
        if (parameters == null) {
            parameters = new LinkedHashMap<>();
        }
        parameters.put(name, value);
    }
    return this;
}


public HUEditorViewBuilder setViewType(JSONViewDataType viewType){
    this.viewType = viewType;
    return this;
}


public DocumentFilterList getStickyFilters(){
    return stickyFilters != null ? stickyFilters : DocumentFilterList.EMPTY;
}


public HUEditorViewBuffer createRowsBuffer(SqlDocumentFilterConverterContext context){
    final ViewId viewId = getViewId();
    final DocumentFilterList stickyFilters = getStickyFilters();
    final DocumentFilterList filters = getFilters();
    if (HUEditorViewBuffer_HighVolume.isHighVolume(stickyFilters)) {
        return new HUEditorViewBuffer_HighVolume(viewId, huEditorViewRepository, stickyFilters, filters, getOrderBys(), context);
    } else {
        return new HUEditorViewBuffer_FullyCached(viewId, huEditorViewRepository, stickyFilters, filters, getOrderBys(), context);
    }
}


public HUEditorViewBuilder orderBys(DocumentQueryOrderByList orderBys){
    this.orderBys = new ArrayList<>(orderBys.toList());
    return this;
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


public HUEditorViewBuilder setReferencingDocumentPaths(String referencingTableName,Set<DocumentPath> referencingDocumentPaths){
    this.referencingTableName = referencingTableName;
    this.referencingDocumentPaths = referencingDocumentPaths;
    return this;
}


public HUEditorViewBuilder clearOrderBys(){
    this.orderBys = null;
    return this;
}


public HUEditorViewBuilder setUseAutoFilters(boolean useAutoFilters){
    this.useAutoFilters = useAutoFilters;
    return this;
}


public DocumentQueryOrderByList getOrderBys(){
    return DocumentQueryOrderByList.ofList(orderBys);
}


public HUEditorViewBuilder setParentViewId(ViewId parentViewId){
    this.parentViewId = parentViewId;
    return this;
}


public HUEditorViewBuilder setAdditionalRelatedProcessDescriptors(List<RelatedProcessDescriptor> additionalRelatedProcessDescriptors){
    if (additionalRelatedProcessDescriptors == null || additionalRelatedProcessDescriptors.isEmpty()) {
        this.additionalRelatedProcessDescriptors = null;
    } else {
        this.additionalRelatedProcessDescriptors = new ArrayList<>(additionalRelatedProcessDescriptors);
    }
    return this;
}


public ViewId getParentViewId(){
    return parentViewId;
}


public HUEditorViewBuilder addAdditionalRelatedProcessDescriptor(RelatedProcessDescriptor descriptor){
    if (additionalRelatedProcessDescriptors == null) {
        additionalRelatedProcessDescriptors = new ArrayList<>();
    }
    additionalRelatedProcessDescriptors.add(descriptor);
    return this;
}


public HUEditorView build(){
    return new HUEditorView(this);
}


public HUEditorViewBuilder setFilters(DocumentFilterList filters){
    this.filters = filters;
    return this;
}


public HUEditorViewBuilder setHUEditorViewRepository(HUEditorViewRepository huEditorViewRepository){
    this.huEditorViewRepository = huEditorViewRepository;
    return this;
}


public ImmutableMap<String,Object> getParameters(){
    return parameters != null ? ImmutableMap.copyOf(parameters) : ImmutableMap.of();
}


@NonNull
public ViewId getViewId(){
    return viewId;
}


public HUEditorViewBuilder setViewId(ViewId viewId){
    this.viewId = viewId;
    return this;
}


}