package de.metas.ui.web.order.products_proposal.process;
 import java.util.List;
import com.google.common.collect.ImmutableList;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRow;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowAddRequest;
import de.metas.ui.web.order.products_proposal.view.ProductsProposalView;
public class WEBUI_ProductsProposal_AddProductFromBasePriceList extends ProductsProposalViewBasedProcess{


@Override
public String doIt(){
    addSelectedRowsToInitialView();
    closeAllViewsAndShowInitialView();
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (getSelectedRowIds().isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    return ProcessPreconditionsResolution.accept();
}


public void addSelectedRowsToInitialView(){
    final ProductsProposalView initialView = getInitialView();
    final List<ProductsProposalRowAddRequest> addRequests = getSelectedRows().stream().map(this::toProductsProposalRowAddRequest).collect(ImmutableList.toImmutableList());
    initialView.addOrUpdateRows(addRequests);
}


public ProductsProposalRowAddRequest toProductsProposalRowAddRequest(ProductsProposalRow row){
    return ProductsProposalRowAddRequest.builder().product(row.getProduct()).asiDescription(row.getAsiDescription()).priceListPrice(row.getPrice().getUserEnteredPrice()).lastShipmentDays(row.getLastShipmentDays()).copiedFromProductPriceId(row.getProductPriceId()).packingMaterialId(row.getPackingMaterialId()).packingDescription(row.getPackingDescription()).build();
}


}