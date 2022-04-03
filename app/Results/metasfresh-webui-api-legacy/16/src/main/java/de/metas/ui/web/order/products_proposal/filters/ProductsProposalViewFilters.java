package de.metas.ui.web.order.products_proposal.filters;
 import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterInlineRenderMode;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.DocumentFilterParam;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.ImmutableDocumentFilterDescriptorsProvider;
import de.metas.ui.web.view.json.JSONFilterViewRequest;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
@UtilityClass
public class ProductsProposalViewFilters {


public ProductsProposalViewFilter extractPackageableViewFilterVO(DocumentFilterList filters){
    return filters.getFilterById(ProductsProposalViewFilter.FILTER_ID).map(filter -> toProductsProposalViewFilterValue(filter)).orElse(ProductsProposalViewFilter.ANY);
}


public DocumentFilterDescriptorsProvider getDescriptors(){
    return ImmutableDocumentFilterDescriptorsProvider.builder().addDescriptor(createDefaultFilterDescriptor()).build();
}


public DocumentFilterDescriptor createDefaultFilterDescriptor(){
    return DocumentFilterDescriptor.builder().setFilterId(ProductsProposalViewFilter.FILTER_ID).setFrequentUsed(true).setInlineRenderMode(DocumentFilterInlineRenderMode.INLINE_PARAMETERS).setDisplayName(getDefaultFilterCaption()).addParameter(newParamDescriptor(ProductsProposalViewFilter.PARAM_ProductName).setWidgetType(DocumentFieldWidgetType.Text)).build();
}


public ITranslatableString getDefaultFilterCaption(){
    return Services.get(IMsgBL.class).getTranslatableMsgText("Default");
}


public DocumentFilterParamDescriptor.Builder newParamDescriptor(String fieldName){
    return DocumentFilterParamDescriptor.builder().setFieldName(fieldName).setDisplayName(Services.get(IMsgBL.class).translatable(fieldName));
}


public ProductsProposalViewFilter toProductsProposalViewFilterValue(DocumentFilter filter){
    return ProductsProposalViewFilter.builder().productName(filter.getParameterValueAsString(ProductsProposalViewFilter.PARAM_ProductName, null)).build();
}


public DocumentFilterList toDocumentFilters(ProductsProposalViewFilter filter){
    final DocumentFilter.Builder builder = DocumentFilter.builder().setFilterId(ProductsProposalViewFilter.FILTER_ID).setCaption(getDefaultFilterCaption());
    if (!Check.isEmpty(filter.getProductName())) {
        builder.addParameter(DocumentFilterParam.ofNameEqualsValue(ProductsProposalViewFilter.PARAM_ProductName, filter.getProductName()));
    }
    if (!builder.hasParameters()) {
        return DocumentFilterList.EMPTY;
    }
    return DocumentFilterList.of(builder.build());
}


}