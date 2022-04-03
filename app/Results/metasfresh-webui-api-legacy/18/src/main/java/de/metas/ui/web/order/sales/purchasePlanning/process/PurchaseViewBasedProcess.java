package de.metas.ui.web.order.sales.purchasePlanning.process;
 import de.metas.process.IProcessPrecondition;
import de.metas.ui.web.order.sales.purchasePlanning.view.PurchaseRowChangeRequest;
import de.metas.ui.web.order.sales.purchasePlanning.view.PurchaseRowId;
import de.metas.ui.web.order.sales.purchasePlanning.view.PurchaseView;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
public class PurchaseViewBasedProcess extends ViewBasedProcessTemplateimplements IProcessPrecondition{


@Override
public PurchaseView getView(){
    return PurchaseView.cast(super.getView());
}


public void patchViewRow(PurchaseRowId rowId,PurchaseRowChangeRequest rowChangeRequest){
    getView().patchViewRow(rowId, rowChangeRequest);
}


}