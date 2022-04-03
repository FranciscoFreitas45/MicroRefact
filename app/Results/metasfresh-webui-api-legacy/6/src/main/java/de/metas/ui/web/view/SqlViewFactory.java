package de.metas.ui.web.view;
 import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import com.google.common.collect.ImmutableList;
import de.metas.logging.LogManager;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilter.Builder;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.DocumentFilterParam;
import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterDecorator;
import de.metas.ui.web.document.geo_location.GeoLocationDocumentService;
import de.metas.ui.web.view.descriptor.SqlViewBinding;
import de.metas.ui.web.view.descriptor.SqlViewBindingFactory;
import de.metas.ui.web.view.descriptor.SqlViewCustomizerMap;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.descriptor.ViewLayoutFactory;
import de.metas.ui.web.view.json.JSONFilterViewRequest;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import de.metas.ui.web.window.model.DocumentReference;
import de.metas.ui.web.window.model.DocumentReferencesService;
import de.metas.util.time.SystemTime;
import lombok.NonNull;
@Service
public class SqlViewFactory implements IViewFactory{

 private  Logger logger;

 private  DocumentReferencesService documentReferencesService;

 private  ViewLayoutFactory viewLayouts;

 private  CompositeDefaultViewProfileIdProvider defaultProfileIdProvider;


public DocumentFilter extractReferencedDocumentFilter(WindowId targetWindowId,DocumentPath referencedDocumentPath){
    if (referencedDocumentPath == null) {
        return null;
    } else if (referencedDocumentPath.isComposedKey()) {
        // document with composed keys does not support references
        return null;
    } else {
        final DocumentReference reference = documentReferencesService.getDocumentReference(referencedDocumentPath, targetWindowId);
        return reference.getFilter();
    }
}


public DocumentFilter createAutoFilter(DocumentFilterDescriptor filterDescriptor){
    if (!filterDescriptor.isAutoFilter()) {
        throw new AdempiereException("Not an auto filter: " + filterDescriptor);
    }
    final Builder filterBuilder = DocumentFilter.builder().setFilterId(filterDescriptor.getFilterId());
    filterDescriptor.getParameters().stream().filter(DocumentFilterParamDescriptor::isAutoFilter).map(SqlViewFactory::createAutoFilterParam).forEach(filterBuilder::addParameter);
    return filterBuilder.build();
}


public DefaultView filterView(DefaultView view,JSONFilterViewRequest filterViewRequest){
    final DocumentFilterDescriptorsProvider filterDescriptors = view.getViewDataRepository().getViewFilterDescriptors();
    final DocumentFilterList newFilters = filterViewRequest.getFiltersUnwrapped(filterDescriptors);
    // final DocumentFilterList newFiltersExcludingFacets = newFilters.retainOnlyNonFacetFilters();
    // 
    // final DocumentFilterList currentFiltersExcludingFacets = view.getFilters().retainOnlyNonFacetFilters();
    // 
    // if (DocumentFilterList.equals(currentFiltersExcludingFacets, newFiltersExcludingFacets))
    // {
    // // TODO
    // throw new AdempiereException("TODO");
    // }
    // else
    {
        return createView(CreateViewRequest.filterViewBuilder(view).setFilters(newFilters).build());
    }
}


public CompositeDefaultViewProfileIdProvider makeDefaultProfileIdProvider(List<DefaultViewProfileIdProvider> providers,SqlViewCustomizerMap viewCustomizersToExtractFallbacks){
    final CompositeDefaultViewProfileIdProvider result = CompositeDefaultViewProfileIdProvider.of(providers);
    viewCustomizersToExtractFallbacks.forEachWindowIdAndProfileId(result::setDefaultProfileIdFallbackIfAbsent);
    return result;
}


public List<DocumentFilter> createAutoFilters(Collection<DocumentFilterDescriptor> filters){
    return filters.stream().filter(DocumentFilterDescriptor::isAutoFilter).map(SqlViewFactory::createAutoFilter).collect(ImmutableList.toImmutableList());
}


@Override
public List<ViewProfile> getAvailableProfiles(WindowId windowId){
    return viewLayouts.getAvailableProfiles(windowId);
}


public void setDefaultProfileId(WindowId windowId,ViewProfileId profileId){
    defaultProfileIdProvider.setDefaultProfileIdOverride(windowId, profileId);
}


@Override
public DefaultView createView(CreateViewRequest request){
    final WindowId windowId = request.getViewId().getWindowId();
    final JSONViewDataType viewType = request.getViewType();
    final ViewProfileId profileId = !ViewProfileId.isNull(request.getProfileId()) ? request.getProfileId() : defaultProfileIdProvider.getDefaultProfileIdByWindowId(windowId);
    final SqlViewBinding sqlViewBinding = viewLayouts.getViewBinding(windowId, viewType.getRequiredFieldCharacteristic(), profileId);
    final IViewDataRepository viewDataRepository = new SqlViewDataRepository(sqlViewBinding);
    final DefaultView.Builder viewBuilder = DefaultView.builder(viewDataRepository).setViewId(request.getViewId()).setViewType(viewType).setProfileId(profileId).setReferencingDocumentPaths(request.getReferencingDocumentPaths()).setParentViewId(request.getParentViewId()).setParentRowId(request.getParentRowId()).addStickyFilters(request.getStickyFilters()).addStickyFilter(extractReferencedDocumentFilter(windowId, request.getSingleReferencingDocumentPathOrNull())).applySecurityRestrictions(request.isApplySecurityRestrictions()).viewInvalidationAdvisor(sqlViewBinding.getViewInvalidationAdvisor()).refreshViewOnChangeEvents(sqlViewBinding.isRefreshViewOnChangeEvents());
    final DocumentFilterList filters = request.getFiltersUnwrapped(viewDataRepository.getViewFilterDescriptors());
    viewBuilder.setFilters(filters);
    if (request.isUseAutoFilters()) {
        final List<DocumentFilter> autoFilters = createAutoFilters(sqlViewBinding.getViewFilterDescriptors().getAll());
        viewBuilder.addFiltersIfAbsent(autoFilters);
    }
    if (!request.getFilterOnlyIds().isEmpty()) {
        final String keyColumnName = sqlViewBinding.getSqlViewKeyColumnNamesMap().getSingleKeyColumnName();
        viewBuilder.addStickyFilter(DocumentFilter.inArrayFilter(keyColumnName, keyColumnName, request.getFilterOnlyIds()));
    }
    return viewBuilder.build();
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    final ViewProfileId profileIdEffective = !ViewProfileId.isNull(profileId) ? profileId : defaultProfileIdProvider.getDefaultProfileIdByWindowId(windowId);
    return viewLayouts.getViewLayout(windowId, viewDataType, profileIdEffective);
}


public DocumentFilterParam createAutoFilterParam(DocumentFilterParamDescriptor filterParamDescriptor){
    final Object value;
    if (filterParamDescriptor.isAutoFilterInitialValueIsDateNow()) {
        final DocumentFieldWidgetType widgetType = filterParamDescriptor.getWidgetType();
        if (widgetType == DocumentFieldWidgetType.LocalDate) {
            value = SystemTime.asLocalDate();
        } else {
            value = SystemTime.asZonedDateTime();
        }
    } else {
        value = filterParamDescriptor.getAutoFilterInitialValue();
    }
    return DocumentFilterParam.builder().setFieldName(filterParamDescriptor.getFieldName()).setOperator(Operator.EQUAL).setValue(value).build();
}


}