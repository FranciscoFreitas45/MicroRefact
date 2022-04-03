package de.metas.ui.web.payment_allocation.process;
 import java.util.List;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.payment_allocation.InvoiceRow;
import de.metas.ui.web.payment_allocation.InvoicesView;
import de.metas.ui.web.payment_allocation.PaymentRow;
import de.metas.ui.web.payment_allocation.PaymentsView;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
public class PaymentsViewBasedProcess extends ViewBasedProcessTemplate{


public List<InvoiceRow> getSelectedInvoiceRows(){
    final DocumentIdsSelection invoiceRowIds = getSelectedInvoiceRowIds();
    return getInvoicesView().streamByIds(invoiceRowIds).collect(ImmutableList.toImmutableList());
}


public DocumentIdsSelection getSelectedInvoiceRowIds(){
    return getChildViewSelectedRowIds();
}


public InvoicesView getInvoicesView(){
    return getPaymentsView().getInvoicesView();
}


public PaymentsView getPaymentsView(){
    return getView();
}


public PaymentRow getSingleSelectedPaymentRow(){
    final DocumentId rowId = getSelectedPaymentRowIds().getSingleDocumentId();
    return getPaymentsView().getById(rowId);
}


public PaymentRow getSingleSelectedPaymentRowOrNull(){
    final DocumentIdsSelection selectedPaymentRowIds = getSelectedPaymentRowIds();
    if (selectedPaymentRowIds.isSingleDocumentId()) {
        final DocumentId rowId = selectedPaymentRowIds.getSingleDocumentId();
        return getPaymentsView().getById(rowId);
    } else {
        return null;
    }
}


public void invalidatePaymentsAndInvoicesViews(){
    invalidateView(getInvoicesView());
    invalidateView(getPaymentsView());
}


@Override
public PaymentsView getView(){
    return PaymentsView.cast(super.getView());
}


public DocumentIdsSelection getSelectedPaymentRowIds(){
    return getSelectedRowIds();
}


}