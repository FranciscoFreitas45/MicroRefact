package de.metas.ui.web.view.CreateViewRequest;
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

@Deprecated
 private  LinkedHashSet<Integer> filterOnlyIds;

 private  ArrayList<DocumentFilter> stickyFilters;

 private  WrappedDocumentFilterList filters;

 private  boolean useAutoFilters;

 private  ViewActionDescriptorsList actions;

 private  List<RelatedProcessDescriptor> additionalRelatedProcessDescriptors;

 private  LinkedHashMap<String,Object> parameters;

 private  boolean applySecurityRestrictions;


public Builder addFilterOnlyId(int filterOnlyId){
    if (filterOnlyIds == null) {
        filterOnlyIds = new LinkedHashSet<>();
    }
    filterOnlyIds.add(filterOnlyId);
    return this;
}


public Builder addAdditionalRelatedProcessDescriptors(List<RelatedProcessDescriptor> relatedProcessDescriptors){
    additionalRelatedProcessDescriptors.addAll(relatedProcessDescriptors);
    return this;
}


public boolean isUseAutoFilters(){
    return useAutoFilters;
}


public Builder setStickyFilters(DocumentFilterList stickyFilters){
    this.stickyFilters = stickyFilters != null ? new ArrayList<>(stickyFilters.toList()) : null;
    return this;
}


public DocumentId getParentRowId(){
    return parentRowId;
}


public ViewActionDescriptorsList getActions(){
    return actions;
}


public Builder applySecurityRestrictions(boolean applySecurityRestrictions){
    this.applySecurityRestrictions = applySecurityRestrictions;
    return this;
}


public Builder setParentRowId(DocumentId parentRowId){
    this.parentRowId = parentRowId;
    return this;
}


public Builder addActionsFromUtilityClass(Class<?> utilityClass){
    final ViewActionDescriptorsList actionsToAdd = ViewActionDescriptorsFactory.instance.getFromClass(utilityClass);
    addActions(actionsToAdd);
    return this;
}


public Builder setParameters(Map<String,Object> parameters){
    parameters.forEach(this::setParameter);
    return this;
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


public Builder setParameter(String name,Object value){
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


public DocumentFilterList getStickyFilters(){
    return DocumentFilterList.ofList(stickyFilters);
}


public Builder setFiltersFromJSON(List<JSONDocumentFilter> jsonFilters){
    filters = WrappedDocumentFilterList.ofJSONFilters(jsonFilters);
    return this;
}


public Builder addActions(ViewActionDescriptorsList actionsToAdd){
    actions = actions.mergeWith(actionsToAdd);
    return this;
}


public Builder setReferencingDocumentPath(DocumentPath referencingDocumentPath){
    setReferencingDocumentPaths(ImmutableSet.of(referencingDocumentPath));
    return this;
}


public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors(){
    return additionalRelatedProcessDescriptors;
}


public Builder setReferencingDocumentPaths(Set<DocumentPath> referencingDocumentPaths){
    this.referencingDocumentPaths = referencingDocumentPaths;
    return this;
}


public Builder setFilterOnlyIds(Collection<Integer> filterOnlyIds){
    if (this.filterOnlyIds == null) {
        this.filterOnlyIds = new LinkedHashSet<>();
    }
    this.filterOnlyIds.addAll(filterOnlyIds);
    return this;
}


public Builder setUseAutoFilters(boolean useAutoFilters){
    this.useAutoFilters = useAutoFilters;
    return this;
}


public Builder addStickyFilters(DocumentFilter stickyFilter){
    if (stickyFilters == null) {
        stickyFilters = new ArrayList<>();
    }
    stickyFilters.add(stickyFilter);
    return this;
}


public ViewProfileId getProfileId(){
    return profileId;
}


public Builder setParentViewId(ViewId parentViewId){
    this.parentViewId = parentViewId;
    return this;
}


public Builder setProfileId(ViewProfileId profileId){
    this.profileId = profileId;
    return this;
}


public ViewId getParentViewId(){
    return parentViewId;
}


public Builder addAdditionalRelatedProcessDescriptor(RelatedProcessDescriptor relatedProcessDescriptor){
    additionalRelatedProcessDescriptors.add(relatedProcessDescriptor);
    return this;
}


public CreateViewRequest build(){
    return new CreateViewRequest(this);
}


public Builder setFilters(DocumentFilterList filters){
    this.filters = WrappedDocumentFilterList.ofFilters(filters);
    return this;
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


public boolean isApplySecurityRestrictions(){
    return applySecurityRestrictions;
}


}