package de.metas.ui.web.order.products_proposal.process;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.order.products_proposal.model.ProductsProposalRow;
import de.metas.ui.web.order.products_proposal.view.OtherSalePricesProductsProposalViewFactory;
import de.metas.ui.web.order.products_proposal.view.ProductsProposalView;
public class WEBUI_ProductsProposal_ShowProductsSoldToOtherCustomers extends ProductsProposalViewBasedProcess{

@Autowired
 private  OtherSalePricesProductsProposalViewFactory otherSalePricesProductsProposalViewFactory;


@Override
public String doIt(){
    final ProductsProposalView view = getView();
    final List<ProductsProposalRow> selectedRows = getSelectedRows();
    final ProductsProposalView otherSalesPricesView = otherSalePricesProductsProposalViewFactory.createView(view, selectedRows);
    afterCloseOpenView(otherSalesPricesView.getViewId());
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (getSelectedRowIds().isEmpty()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("nothing selected");
    }
    return ProcessPreconditionsResolution.accept();
}


}