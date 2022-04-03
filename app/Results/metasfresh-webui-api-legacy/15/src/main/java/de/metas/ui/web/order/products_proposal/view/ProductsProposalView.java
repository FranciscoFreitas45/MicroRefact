package de.metas.ui.web.order.products_proposal.view;
 import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableList;
import de.metas.bpartner.BPartnerId;
import de.metas.i18n.TranslatableStrings;
import de.metas.lang.SOTrx;
import de.metas.order.OrderId;
import de.metas.pricing.PriceListVersionId;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.product.ProductId;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.order.products_proposal.filters.ProductsProposalViewFilter;
import de.metas.ui.web.order.products_proposal.filters.ProductsProposalViewFilters;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRow;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowAddRequest;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowChangeRequest;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowsData;
import de.metas.ui.web.order.products_proposal.service.Order;
import de.metas.ui.web.view.IEditableView;
import de.metas.ui.web.view.ViewHeaderProperties;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.Builder;
import lombok.NonNull;
public class ProductsProposalView extends AbstractCustomView<ProductsProposalRow>implements IEditableView{

 private  ProductsProposalRowsData rowsData;

 private  ImmutableList<RelatedProcessDescriptor> processes;

 private  ViewId initialViewId;


public Optional<BPartnerId> getBpartnerId(){
    return rowsData.getBpartnerId();
}


public Set<ProductId> getProductIds(){
    return rowsData.getProductIds();
}


@Override
public ViewHeaderProperties getHeaderProperties(){
    return rowsData.getHeaderProperties();
}


@Override
public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors(){
    return processes;
}


@Override
public String getTableNameOrNull(DocumentId documentId){
    return null;
}


@Override
public LookupValuesList getFieldDropdown(RowEditingContext ctx,String fieldName){
    throw new UnsupportedOperationException();
}


public Optional<OrderId> getOrderId(){
    return rowsData.getOrder().map(Order::getOrderId);
}


public List<ProductsProposalRow> getAllRows(){
    return ImmutableList.copyOf(getRows());
}


public ViewId getInitialViewId(){
    return initialViewId != null ? initialViewId : getViewId();
}


public SOTrx getSoTrx(){
    return rowsData.getSoTrx();
}


public void addOrUpdateRows(List<ProductsProposalRowAddRequest> requests){
    rowsData.addOrUpdateRows(requests);
    invalidateAll();
}


public void patchViewRow(DocumentId rowId,ProductsProposalRowChangeRequest request){
    rowsData.patchRow(rowId, request);
}


@Override
public LookupValuesList getFieldTypeahead(RowEditingContext ctx,String fieldName,String query){
    throw new UnsupportedOperationException();
}


public ProductsProposalView filter(ProductsProposalViewFilter filter){
    rowsData.filter(filter);
    return this;
}


public ProductsProposalView cast(Object viewObj){
    return (ProductsProposalView) viewObj;
}


public Optional<PriceListVersionId> getSinglePriceListVersionId(){
    return rowsData.getSinglePriceListVersionId();
}


public void removeRowsByIds(Set<DocumentId> rowIds){
    rowsData.removeRowsByIds(rowIds);
    invalidateAll();
}


@Override
public ViewId getParentViewId(){
    return initialViewId;
}


public Optional<PriceListVersionId> getBasePriceListVersionId(){
    return rowsData.getBasePriceListVersionId();
}


@Override
public DocumentFilterList getFilters(){
    return ProductsProposalViewFilters.toDocumentFilters(rowsData.getFilter());
}


public PriceListVersionId getBasePriceListVersionIdOrFail(){
    return rowsData.getBasePriceListVersionId().orElseThrow(() -> new AdempiereException("@NotFound@ @M_Pricelist_Version_Base_ID@"));
}


}