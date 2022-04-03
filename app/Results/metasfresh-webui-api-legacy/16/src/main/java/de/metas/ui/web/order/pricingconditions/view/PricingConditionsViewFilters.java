package de.metas.ui.web.order.pricingconditions.view;
 import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.IMsgBL;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.ImmutableDocumentFilterDescriptorsProvider;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.json.JSONFilterViewRequest;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.util.Services;
import lombok.NonNull;
public class PricingConditionsViewFilters {

 private  String FILTERID_IsCustomer;

 private  String PARAM_IsCustomer;

 private  String FILTERID_IsVendor;

 private  String PARAM_IsVendor;

 private  ImmutableDocumentFilterDescriptorsProvider filterDescriptorsProvider;

 private  DocumentFilterList defaultFilters;


public DocumentFilterDescriptor createIsCustomerFilterDescriptor(){
    return DocumentFilterDescriptor.builder().setFilterId(FILTERID_IsCustomer).setFrequentUsed(true).addParameter(DocumentFilterParamDescriptor.builder().setFieldName(PARAM_IsCustomer).setDisplayName(Services.get(IMsgBL.class).translatable(PARAM_IsCustomer)).setWidgetType(DocumentFieldWidgetType.YesNo)).build();
}


public Predicate<PricingConditionsRow> isEditableRowOrMatching(DocumentFilterList filters){
    if (filters.isEmpty()) {
        return Predicates.alwaysTrue();
    }
    final boolean showCustomers = filters.getParamValueAsBoolean(FILTERID_IsCustomer, PARAM_IsCustomer, false);
    final boolean showVendors = filters.getParamValueAsBoolean(FILTERID_IsVendor, PARAM_IsVendor, false);
    final boolean showAll = !showCustomers && !showVendors;
    if (showAll) {
        return Predicates.alwaysTrue();
    }
    return row -> row.isEditable() || ((showCustomers && row.isCustomer()) || (showVendors && row.isVendor()));
}


public DocumentFilterDescriptor createIsVendorFilterDescriptor(){
    return DocumentFilterDescriptor.builder().setFilterId(FILTERID_IsVendor).setFrequentUsed(true).addParameter(DocumentFilterParamDescriptor.builder().setFieldName(PARAM_IsVendor).setDisplayName(Services.get(IMsgBL.class).translatable(PARAM_IsVendor)).setWidgetType(DocumentFieldWidgetType.YesNo)).build();
}


public DocumentFilterList extractFilters(CreateViewRequest request){
    return request.isUseAutoFilters() ? getDefaultFilters() : request.getFiltersUnwrapped(getFilterDescriptorsProvider());
}


public Collection<DocumentFilterDescriptor> getFilterDescriptors(){
    return getFilterDescriptorsProvider().getAll();
}


public List<DocumentFilterDescriptor> createFilterDescriptors(){
    return ImmutableList.of(createIsCustomerFilterDescriptor(), createIsVendorFilterDescriptor());
}


public DocumentFilterList getDefaultFilters(){
    if (defaultFilters == null) {
        final DocumentFilter isCustomer = DocumentFilter.singleParameterFilter(FILTERID_IsCustomer, PARAM_IsCustomer, Operator.EQUAL, true);
        defaultFilters = DocumentFilterList.of(isCustomer);
    }
    return defaultFilters;
}


public DocumentFilterDescriptorsProvider getFilterDescriptorsProvider(){
    if (filterDescriptorsProvider == null) {
        filterDescriptorsProvider = ImmutableDocumentFilterDescriptorsProvider.of(createFilterDescriptors());
    }
    return filterDescriptorsProvider;
}


}