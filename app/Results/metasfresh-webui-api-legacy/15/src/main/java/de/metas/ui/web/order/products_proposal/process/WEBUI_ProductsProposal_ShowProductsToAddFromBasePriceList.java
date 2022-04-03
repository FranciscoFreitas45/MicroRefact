package de.metas.ui.web.order.products_proposal.process;
 import org.springframework.beans.factory.annotation.Autowired;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.order.products_proposal.view.BasePLVProductsProposalViewFactory;
import de.metas.ui.web.order.products_proposal.view.ProductsProposalView;
public class WEBUI_ProductsProposal_ShowProductsToAddFromBasePriceList extends ProductsProposalViewBasedProcess{

@Autowired
 private  BasePLVProductsProposalViewFactory basePLVProductsProposalViewFactory;


@Override
public String doIt(){
    final ProductsProposalView basePriceListView = basePLVProductsProposalViewFactory.createView(getView());
    afterCloseOpenView(basePriceListView.getViewId());
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!getView().getBasePriceListVersionId().isPresent()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("no base price list set");
    }
    return ProcessPreconditionsResolution.accept();
}


}