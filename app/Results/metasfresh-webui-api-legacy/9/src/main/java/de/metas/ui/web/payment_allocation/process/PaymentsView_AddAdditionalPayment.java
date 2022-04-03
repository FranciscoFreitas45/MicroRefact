package de.metas.ui.web.payment_allocation.process;
 import de.metas.payment.PaymentId;
import de.metas.process.Param;
import de.metas.ui.web.payment_allocation.PaymentsView;
public class PaymentsView_AddAdditionalPayment extends PaymentsViewBasedProcess{

@Param(parameterName = "C_Payment_ID", mandatory = true)
 private  PaymentId paymentId;


@Override
public String doIt(){
    final PaymentsView view = getView();
    view.addPayment(paymentId);
    return MSG_OK;
}


@Override
public void postProcess(boolean success){
    if (success) {
        invalidateView();
    }
}


}