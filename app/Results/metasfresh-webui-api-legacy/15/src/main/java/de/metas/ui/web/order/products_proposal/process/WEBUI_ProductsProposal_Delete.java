package de.metas.ui.web.order.products_proposal.process;
 import java.util.List;
import java.util.Set;
import java.util.Objects;
import com.google.common.collect.ImmutableSet;
import de.metas.pricing.ProductPriceId;
import de.metas.pricing.service.IPriceListDAO;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRow;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.util.Services;
public class WEBUI_ProductsProposal_Delete extends ProductsProposalViewBasedProcess{

 private  IPriceListDAO priceListsRepo;


@Override
public String doIt(){
    final List<ProductsProposalRow> selectedRows = getSelectedRows();
    if (// shall not happen
    selectedRows.isEmpty()) {
        return MSG_OK;
    }
    // 
    // Remove product prices from database
    final Set<ProductPriceId> productPriceIds = selectedRows.stream().map(ProductsProposalRow::getProductPriceId).filter(Objects::nonNull).collect(ImmutableSet.toImmutableSet());
    priceListsRepo.deleteProductPricesByIds(productPriceIds);
    // 
    // Remove rows from view
    final Set<DocumentId> rowIds = selectedRows.stream().map(ProductsProposalRow::getId).collect(ImmutableSet.toImmutableSet());
    getView().removeRowsByIds(rowIds);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (getSelectedRowIds().isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection().toInternal();
    }
    return ProcessPreconditionsResolution.accept();
}


}