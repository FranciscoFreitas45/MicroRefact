package de.metas.ui.web.payment_allocation.process;
 import org.compiere.SpringContextHolder;
import de.metas.money.MoneyService;
import de.metas.ui.web.payment_allocation.process.PaymentsViewAllocateCommand.PaymentsViewAllocateCommandBuilder;
import lombok.NonNull;
public class PaymentsView_Allocate_Template extends PaymentsViewBasedProcess{

 private  MoneyService moneyService;


public PaymentsViewAllocateCommand newPaymentsViewAllocateCommand(){
    final PaymentsViewAllocateCommandBuilder builder = PaymentsViewAllocateCommand.builder().moneyService(moneyService).paymentRow(getSingleSelectedPaymentRowOrNull()).invoiceRows(getSelectedInvoiceRows());
    customizePaymentsViewAllocateCommandBuilder(builder);
    return builder.build();
}


public void customizePaymentsViewAllocateCommandBuilder(PaymentsViewAllocateCommandBuilder builder){
// nothing on this level
}


@Override
public String doIt(){
    newPaymentsViewAllocateCommand().run();
    // NOTE: the payment and invoice rows will be automatically invalidated (via a cache reset),
    // when the payment allocation is processed
    return MSG_OK;
}


@Override
public void postProcess(boolean success){
    // FIXME: until https://github.com/metasfresh/me03/issues/3388 is fixed,
    // as a workaround we have to invalidate the whole views
    invalidatePaymentsAndInvoicesViews();
}


}