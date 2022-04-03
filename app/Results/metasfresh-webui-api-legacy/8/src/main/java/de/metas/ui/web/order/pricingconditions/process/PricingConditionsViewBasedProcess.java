package de.metas.ui.web.order.pricingconditions.process;
 import de.metas.process.IProcessPrecondition;
import de.metas.ui.web.order.pricingconditions.view.PricingConditionsRow;
import de.metas.ui.web.order.pricingconditions.view.PricingConditionsRowChangeRequest;
import de.metas.ui.web.order.pricingconditions.view.PricingConditionsView;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
public class PricingConditionsViewBasedProcess extends ViewBasedProcessTemplateimplements IProcessPrecondition{


public PricingConditionsRow getEditableRow(){
    return getView().getEditableRow();
}


@Override
public PricingConditionsRow getSingleSelectedRow(){
    return PricingConditionsRow.cast(super.getSingleSelectedRow());
}


@Override
public PricingConditionsView getView(){
    return PricingConditionsView.cast(super.getView());
}


public boolean isSingleSelectedRow(){
    return getSelectedRowIds().isSingleDocumentId();
}


public void patchEditableRow(PricingConditionsRowChangeRequest request){
    getView().patchEditableRow(request);
}


}