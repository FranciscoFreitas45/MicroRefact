package de.metas.ui.web.view;
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
@Value
public class CreateViewRequest {

 private ViewId viewId;

 private JSONViewDataType viewType;

 private ViewProfileId profileId;

 private ViewId parentViewId;

 private DocumentId parentRowId;

 private ImmutableSet<DocumentPath> referencingDocumentPaths;

 private DocumentFilterList stickyFilters;

@Getter(AccessLevel.PRIVATE)
 private WrappedDocumentFilterList filters;

@Deprecated
 private ImmutableSet<Integer> filterOnlyIds;

 private boolean useAutoFilters;

 private ViewActionDescriptorsList actions;

 private ImmutableList<RelatedProcessDescriptor> additionalRelatedProcessDescriptors;

 private ImmutableMap<String,Object> parameters;

 private boolean applySecurityRestrictions;

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

 public  WrappedDocumentFilterList EMPTY;

 private  ImmutableList<JSONDocumentFilter> jsonFilters;

 private  DocumentFilterList filters;


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


public Characteristic getViewTypeRequiredFieldCharacteristic(){
    Check.assumeNotNull(viewType, "Parameter viewType is not null for {}", this);
    return viewType.getRequiredFieldCharacteristic();
}


public boolean isUseAutoFilters(){
    return useAutoFilters;
}


public Builder setStickyFilters(DocumentFilterList stickyFilters){
    this.stickyFilters = stickyFilters != null ? new ArrayList<>(stickyFilters.toList()) : null;
    return this;
}


public int getSingleFilterOnlyId(){
    return CollectionUtils.singleElement(getFilterOnlyIds());
}


public WrappedDocumentFilterList ofFilters(DocumentFilterList filters){
    if (filters == null || filters.isEmpty()) {
        return EMPTY;
    }
    final ImmutableList<JSONDocumentFilter> jsonFiltersEffective = null;
    final DocumentFilterList filtersEffective = filters;
    return new WrappedDocumentFilterList(jsonFiltersEffective, filtersEffective);
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


public DocumentFilterList unwrap(DocumentFilterDescriptorsProvider descriptors){
    if (filters != null) {
        return filters;
    }
    if (jsonFilters == null || jsonFilters.isEmpty()) {
        return DocumentFilterList.EMPTY;
    }
    return JSONDocumentFilter.unwrapList(jsonFilters, descriptors);
}


public Builder deleteStickyFilterBuilder(IView view,String stickyFilterIdToDelete){
    final DocumentFilterList stickyFilters = view.getStickyFilters().stream().filter(stickyFilter -> !Objects.equals(stickyFilter.getFilterId(), stickyFilterIdToDelete)).collect(DocumentFilterList.toDocumentFilterList());
    // FIXME: instead of removing all referencing document paths (to prevent creating sticky filters from them),
    // we shall remove only those is are related to "stickyFilterIdToDelete".
    // view.getReferencingDocumentPaths();
    final Set<DocumentPath> referencingDocumentPaths = ImmutableSet.of();
    return builder(view.getViewId().getWindowId(), view.getViewType()).setProfileId(view.getProfileId()).setParentViewId(view.getParentViewId()).setParentRowId(view.getParentRowId()).setReferencingDocumentPaths(referencingDocumentPaths).setStickyFilters(stickyFilters).setFilters(view.getFilters()).setUseAutoFilters(false).addActions(view.getActions()).addAdditionalRelatedProcessDescriptors(view.getAdditionalRelatedProcessDescriptors());
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


public DocumentPath getSingleReferencingDocumentPathOrNull(){
    final Set<DocumentPath> referencingDocumentPaths = getReferencingDocumentPaths();
    if (referencingDocumentPaths.isEmpty()) {
        return null;
    } else {
        // NOTE: preserving the old logic and returning the first documentPath
        return referencingDocumentPaths.iterator().next();
    }
}


public JSONViewDataType getViewType(){
    return viewType;
}


public ImmutableSet<DocumentPath> getReferencingDocumentPaths(){
    return referencingDocumentPaths == null ? ImmutableSet.of() : ImmutableSet.copyOf(referencingDocumentPaths);
}


public Builder builder(ViewId viewId,JSONViewDataType viewType){
    return new Builder(viewId, viewType);
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


public DocumentFilterList getFiltersUnwrapped(DocumentFilterDescriptorsProvider descriptors){
    return getFilters().unwrap(descriptors);
}


public Builder setReferencingDocumentPath(DocumentPath referencingDocumentPath){
    setReferencingDocumentPaths(ImmutableSet.of(referencingDocumentPath));
    return this;
}


public Builder filterViewBuilder(IView view){
    return builder(view.getViewId().getWindowId(), view.getViewType()).setProfileId(view.getProfileId()).setParentViewId(view.getParentViewId()).setParentRowId(view.getParentRowId()).setReferencingDocumentPaths(view.getReferencingDocumentPaths()).setStickyFilters(view.getStickyFilters()).setUseAutoFilters(false).addActions(view.getActions()).addAdditionalRelatedProcessDescriptors(view.getAdditionalRelatedProcessDescriptors());
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


public void assertNoParentViewOrRow(){
    if (parentViewId != null) {
        throw new AdempiereException("parentViewId '" + parentViewId + "' is not supported in " + this);
    }
    if (parentRowId != null) {
        throw new AdempiereException("parentViewId '" + parentRowId + "' is not supported in " + this);
    }
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


public T getParameterAs(String parameterName,Class<T> type){
    @SuppressWarnings("unchecked")
    final T value = (T) getParameters().get(parameterName);
    return value;
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


public WrappedDocumentFilterList ofJSONFilters(List<JSONDocumentFilter> jsonFilters){
    if (jsonFilters == null || jsonFilters.isEmpty()) {
        return EMPTY;
    }
    final ImmutableList<JSONDocumentFilter> jsonFiltersEffective = ImmutableList.copyOf(jsonFilters);
    final DocumentFilterList filtersEffective = null;
    return new WrappedDocumentFilterList(jsonFiltersEffective, filtersEffective);
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