package de.metas.ui.web.view.DefaultView;
 import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.cache.CCache;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.standard.FacetFilterViewCacheMap;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.view.event.ViewChangesCollector;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.DocumentSaveStatus;
import de.metas.ui.web.window.model.DocumentValidStatus;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.ui.web.window.model.NullDocumentChangesCollector;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.NumberUtils;
import de.metas.util.Services;
import de.metas.util.collections.IteratorUtils;
import de.metas.util.collections.PagedIterator.Page;
import lombok.Getter;
import lombok.NonNull;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.util.Evaluatee;
import org.slf4j.Logger;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.stream.Stream;
public class Builder {

 private  ViewId viewId;

 private  JSONViewDataType viewType;

 private  ViewProfileId profileId;

 private  Set<DocumentPath> referencingDocumentPaths;

 private  ViewId parentViewId;

 private  DocumentId parentRowId;

 private  IViewDataRepository viewDataRepository;

 private  LinkedHashMap<String,DocumentFilter> _stickyFiltersById;

 private  LinkedHashMap<String,DocumentFilter> _filtersById;

 private  boolean refreshViewOnChangeEvents;

 private  IViewInvalidationAdvisor viewInvalidationAdvisor;

 private  boolean applySecurityRestrictions;


public Builder refreshViewOnChangeEvents(boolean refreshViewOnChangeEvents){
    this.refreshViewOnChangeEvents = refreshViewOnChangeEvents;
    return this;
}


public Builder viewInvalidationAdvisor(IViewInvalidationAdvisor viewInvalidationAdvisor){
    this.viewInvalidationAdvisor = viewInvalidationAdvisor;
    return this;
}


public DocumentId getParentRowId(){
    return parentRowId;
}


public Builder applySecurityRestrictions(boolean applySecurityRestrictions){
    this.applySecurityRestrictions = applySecurityRestrictions;
    return this;
}


public Builder setParentRowId(DocumentId parentRowId){
    this.parentRowId = parentRowId;
    return this;
}


public Builder addFiltersIfAbsent(Collection<DocumentFilter> filters){
    filters.forEach(filter -> _filtersById.putIfAbsent(filter.getFilterId(), filter));
    return this;
}


public IViewDataRepository getViewDataRepository(){
    return viewDataRepository;
}


public JSONViewDataType getViewType(){
    return viewType;
}


public ImmutableSet<DocumentPath> getReferencingDocumentPaths(){
    return referencingDocumentPaths == null ? ImmutableSet.of() : ImmutableSet.copyOf(referencingDocumentPaths);
}


public DocumentFilterList getFilters(){
    return _filtersById.isEmpty() ? DocumentFilterList.EMPTY : DocumentFilterList.ofList(_filtersById.values());
}


public Builder setViewType(JSONViewDataType viewType){
    this.viewType = viewType;
    return this;
}


public DocumentFilterList getStickyFilters(){
    return _stickyFiltersById == null ? DocumentFilterList.EMPTY : DocumentFilterList.ofList(_stickyFiltersById.values());
}


public boolean isRefreshViewOnChangeEvents(){
    return refreshViewOnChangeEvents;
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
}


public DocumentFilterDescriptorsProvider getViewFilterDescriptors(){
    return viewDataRepository.getViewFilterDescriptors();
}


public Builder setReferencingDocumentPaths(Set<DocumentPath> referencingDocumentPaths){
    this.referencingDocumentPaths = referencingDocumentPaths;
    return this;
}


public Builder addStickyFilters(DocumentFilterList stickyFilters){
    if (stickyFilters == null || stickyFilters.isEmpty()) {
        return this;
    }
    stickyFilters.forEach(this::addStickyFilter);
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


public DefaultView build(){
    return new DefaultView(this);
}


public Builder setFilters(DocumentFilterList filters){
    _filtersById.clear();
    filters.forEach(filter -> _filtersById.put(filter.getFilterId(), filter));
    return this;
}


public IViewInvalidationAdvisor getViewInvalidationAdvisor(){
    return viewInvalidationAdvisor;
}


@NonNull
public ViewId getViewId(){
    return viewId;
}


public Builder setViewId(ViewId viewId){
    this.viewId = viewId;
    return this;
}


public boolean isApplySecurityRestrictions(){
    return applySecurityRestrictions;
}


}