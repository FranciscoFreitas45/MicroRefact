package de.metas.ui.web.payment_allocation.process;
 import de.metas.ui.web.payment_allocation.InvoicesView;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
public class InvoicesViewBasedProcess extends ViewBasedProcessTemplate{


@Override
public InvoicesView getView(){
    return InvoicesView.cast(super.getView());
}


}