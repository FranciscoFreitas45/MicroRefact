package de.metas.ui.web.order.products_proposal.process;
 import java.math.BigDecimal;
import java.util.stream.Stream;
import org.adempiere.exceptions.AdempiereException;
import de.metas.pricing.PriceListVersionId;
import de.metas.pricing.ProductPriceId;
import de.metas.pricing.service.CopyProductPriceRequest;
import de.metas.pricing.service.IPriceListDAO;
import de.metas.pricing.service.UpdateProductPriceRequest;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRow;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRowChangeRequest.RowSaved;
import de.metas.ui.web.order.products_proposal.view.ProductsProposalView;
import de.metas.util.Services;
public class WEBUI_ProductsProposal_SaveProductPriceToCurrentPriceListVersion extends ProductsProposalViewBasedProcess{

 private  IPriceListDAO pricesListsRepo;


public boolean hasChangedRows(){
    return streamChangedRows().findAny().isPresent();
}


public Stream<ProductsProposalRow> streamChangedRows(){
    return getSelectedRows().stream().filter(ProductsProposalRow::isChanged);
}


public void save(ProductsProposalRow row){
    if (!row.isChanged()) {
        return;
    }
    final BigDecimal userEnteredPriceValue = row.getPrice().getUserEnteredPriceValue();
    final ProductPriceId productPriceId;
    // 
    // Update existing product price
    if (row.getProductPriceId() != null) {
        productPriceId = row.getProductPriceId();
        pricesListsRepo.updateProductPrice(UpdateProductPriceRequest.builder().productPriceId(productPriceId).priceStd(userEnteredPriceValue).build());
    } else // 
    // Save a new product price which was copied from some other price list version
    if (row.getCopiedFromProductPriceId() != null) {
        productPriceId = pricesListsRepo.copyProductPrice(CopyProductPriceRequest.builder().copyFromProductPriceId(row.getCopiedFromProductPriceId()).copyToPriceListVersionId(getPriceListVersionId()).priceStd(userEnteredPriceValue).build());
    } else {
        throw new AdempiereException("Cannot save row: " + row);
    }
    // 
    // Refresh row
    getView().patchViewRow(row.getId(), RowSaved.builder().productPriceId(productPriceId).price(row.getPrice().withPriceListPriceValue(userEnteredPriceValue)).build());
}


@Override
public String doIt(){
    streamChangedRows().forEach(this::save);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!hasChangedRows()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("nothing to save");
    }
    final ProductsProposalView view = getView();
    if (!view.getSinglePriceListVersionId().isPresent()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("no base price list version");
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    invalidateView();
}


public PriceListVersionId getPriceListVersionId(){
    return getView().getSinglePriceListVersionId().orElseThrow(() -> new AdempiereException("@NotFound@ @M_PriceList_Version_ID@"));
}


}