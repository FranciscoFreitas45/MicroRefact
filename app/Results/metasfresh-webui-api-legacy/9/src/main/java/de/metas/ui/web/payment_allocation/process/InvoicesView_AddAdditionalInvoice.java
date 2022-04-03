package de.metas.ui.web.payment_allocation.process;
 import de.metas.invoice.InvoiceId;
import de.metas.process.Param;
import de.metas.ui.web.payment_allocation.InvoicesView;
public class InvoicesView_AddAdditionalInvoice extends InvoicesViewBasedProcess{

@Param(parameterName = "C_Invoice_ID", mandatory = true)
 private  InvoiceId invoiceId;


@Override
public String doIt(){
    final InvoicesView view = getView();
    view.addInvoice(invoiceId);
    invalidateView(view);
    return MSG_OK;
}


}