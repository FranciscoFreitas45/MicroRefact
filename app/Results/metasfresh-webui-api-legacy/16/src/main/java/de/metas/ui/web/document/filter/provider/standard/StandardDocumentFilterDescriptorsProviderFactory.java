package de.metas.ui.web.document.filter.provider.standard;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import org.adempiere.ad.element.api.AdTabId;
import org.adempiere.service.ISysConfigBL;
import org.springframework.stereotype.Component;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsConstants;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProviderFactory;
import de.metas.ui.web.document.filter.provider.ImmutableDocumentFilterDescriptorsProvider;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.ui.web.window.descriptor.DocumentFieldDefaultFilterDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.util.Services;
import lombok.NonNull;
@Component
public class StandardDocumentFilterDescriptorsProviderFactory implements DocumentFilterDescriptorsProviderFactory{

 private  ISysConfigBL sysConfigs;

 private  IMsgBL msgBL;

 private  IViewsRepository viewsRepository;

 private  String FILTER_ID_DefaultDate;

 private  String FILTER_ID_Default;

 private  AdMessageKey MSG_DefaultFilterName;

 private  String FACET_FILTER_ID_PREFIX;

 private  String SYSCONFIG_MAX_FACETS_TO_FETCH;

 private  int SYSCONFIG_FACETS_TO_FETCH_DEFAULT;


public int getMaxFacetsToFetch(){
    return sysConfigs.getIntValue(SYSCONFIG_MAX_FACETS_TO_FETCH, SYSCONFIG_FACETS_TO_FETCH_DEFAULT);
}


public DocumentFieldWidgetType extractFilterWidgetType(DocumentFieldDescriptor field){
    final DocumentFieldWidgetType widgetType = field.getWidgetType();
    if (widgetType == DocumentFieldWidgetType.ZonedDateTime) {
        // frontend cannot handle ZonedDateTime filter params
        return DocumentFieldWidgetType.LocalDate;
    }
    if (widgetType == DocumentFieldWidgetType.Timestamp) {
        // frontend cannot handle Timestamp filter params
        return DocumentFieldWidgetType.LocalDate;
    } else {
        return widgetType;
    }
}


public DocumentFilterDescriptor createFacetFilter(DocumentFieldDescriptor field,int sortNo){
    final FacetsFilterLookupDescriptor facetsLookupDescriptor = createFacetsFilterLookupDescriptor(field);
    return DocumentFilterDescriptor.builder().setFilterId(facetsLookupDescriptor.getFilterId()).setSortNo(DocumentFilterDescriptorsConstants.SORT_NO_FACETS_START + sortNo).setFrequentUsed(true).setParametersLayoutType(PanelLayoutType.Panel).setDisplayName(field.getCaption()).setFacetFilter(true).addParameter(DocumentFilterParamDescriptor.builder().setFieldName(facetsLookupDescriptor.getFieldName()).setOperator(Operator.IN_ARRAY).setDisplayName(field.getCaption()).setMandatory(true).setWidgetType(DocumentFieldWidgetType.MultiValuesList).setLookupDescriptor(facetsLookupDescriptor)).build();
}


public DocumentFilterDescriptorsProvider createFiltersProvider(Collection<DocumentFieldDescriptor> fields){
    final List<DocumentFieldDescriptor> fieldsForDefaultFiltering = fields.stream().filter(DocumentFieldDescriptor::hasFileringInfo).filter(field -> field.getDefaultFilterInfo().isDefaultFilter()).sorted(Comparator.comparing(field -> field.getDefaultFilterInfo().getDefaultFilterSeqNo())).collect(ImmutableList.toImmutableList());
    final List<DocumentFieldDescriptor> fieldsForFacetFiltering = fields.stream().filter(DocumentFieldDescriptor::hasFileringInfo).filter(field -> field.getDefaultFilterInfo().isFacetFilter()).sorted(Comparator.comparing(field -> field.getDefaultFilterInfo().getFacetFilterSeqNo())).collect(ImmutableList.toImmutableList());
    // 
    // Default filters
    DocumentFilterDescriptor defaultDateFilter = null;
    DocumentFilterDescriptor.Builder defaultFilterBuilder = null;
    for (final DocumentFieldDescriptor field : fieldsForDefaultFiltering) {
        final DocumentFilterParamDescriptor.Builder filterParam = createFilterParam(field);
        if (defaultDateFilter == null && filterParam.getWidgetType().isDateOrTime()) {
            defaultDateFilter = DocumentFilterDescriptor.builder().setFilterId(FILTER_ID_DefaultDate).setSortNo(DocumentFilterDescriptorsConstants.SORT_NO_DEFAULT_DATE).setFrequentUsed(true).setDisplayName(filterParam.getDisplayName()).addParameter(filterParam).build();
        } else {
            if (defaultFilterBuilder == null) {
                defaultFilterBuilder = DocumentFilterDescriptor.builder().setFilterId(FILTER_ID_Default).setSortNo(DocumentFilterDescriptorsConstants.SORT_NO_DEFAULT_FILTERS_GROUP).setDisplayName(msgBL.getTranslatableMsgText(MSG_DefaultFilterName)).setFrequentUsed(false);
            }
            defaultFilterBuilder.addParameter(filterParam);
        }
    }
    // 
    // Facet filters
    final ArrayList<DocumentFilterDescriptor> facetFilters = new ArrayList<>();
    for (DocumentFieldDescriptor field : fieldsForFacetFiltering) {
        final int sortNo = facetFilters.size() + 1;
        final DocumentFilterDescriptor facetFilter = createFacetFilter(field, sortNo);
        facetFilters.add(facetFilter);
    }
    final ArrayList<DocumentFilterDescriptor> descriptors = new ArrayList<>();
    if (defaultDateFilter != null) {
        descriptors.add(defaultDateFilter);
    }
    if (defaultFilterBuilder != null) {
        descriptors.add(defaultFilterBuilder.build());
    }
    descriptors.addAll(facetFilters);
    return ImmutableDocumentFilterDescriptorsProvider.of(descriptors);
}


public FacetsFilterLookupDescriptor createFacetsFilterLookupDescriptor(DocumentFieldDescriptor field){
    final String columnName = field.getDataBinding().get().getColumnName();
    final String filterId = FACET_FILTER_ID_PREFIX + columnName;
    final DocumentFieldDefaultFilterDescriptor fieldFilteringInfo = field.getDefaultFilterInfo();
    final DocumentFieldWidgetType fieldWidgetType = extractFilterWidgetType(field);
    final LookupDescriptor fieldLookupDescriptor = field.getLookupDescriptorForFiltering().orElse(null);
    final boolean numericKey;
    if (fieldWidgetType.isLookup()) {
        numericKey = fieldLookupDescriptor.isNumericKey();
    } else {
        numericKey = fieldWidgetType.isNumeric();
    }
    return FacetsFilterLookupDescriptor.builder().viewsRepository(viewsRepository).filterId(filterId).fieldName(columnName).fieldWidgetType(fieldWidgetType).numericKey(numericKey).maxFacetsToFetch(fieldFilteringInfo.getMaxFacetsToFetch().orElse(getMaxFacetsToFetch())).fieldLookupDescriptor(fieldLookupDescriptor).build();
}


public DocumentFilterParamDescriptor.Builder createFilterParam(DocumentFieldDescriptor field){
    final ITranslatableString displayName = field.getCaption();
    final String fieldName = field.getFieldName();
    final DocumentFieldWidgetType widgetType = extractFilterWidgetType(field);
    final DocumentFieldDefaultFilterDescriptor filteringInfo = field.getDefaultFilterInfo();
    final Optional<LookupDescriptor> lookupDescriptor = field.getLookupDescriptorForFiltering();
    final Operator operator;
    if (widgetType.isText()) {
        operator = Operator.LIKE_I;
    } else if (filteringInfo.isRangeFilter()) {
        operator = Operator.BETWEEN;
    } else {
        operator = Operator.EQUAL;
    }
    return DocumentFilterParamDescriptor.builder().setDisplayName(displayName).setFieldName(fieldName).setWidgetType(widgetType).setOperator(operator).setLookupDescriptor(lookupDescriptor).setMandatory(false).setShowIncrementDecrementButtons(filteringInfo.isShowFilterIncrementButtons()).setAutoFilterInitialValue(filteringInfo.getAutoFilterInitialValue());
}


}