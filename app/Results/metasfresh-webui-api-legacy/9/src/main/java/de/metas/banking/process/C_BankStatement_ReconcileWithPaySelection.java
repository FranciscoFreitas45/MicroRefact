package de.metas.banking.process;
 import java.util.Set;
import de.metas.banking.PaySelectionId;
import de.metas.banking.payment.IPaySelectionBL;
import de.metas.payment.PaymentId;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.util.Services;
import lombok.NonNull;
public class C_BankStatement_ReconcileWithPaySelection extends BankStatementBasedProcess{

 private  IPaySelectionBL paySelectionBL;

@Param(parameterName = "C_PaySelection_ID", mandatory = true)
 private  PaySelectionId paySelectionId;


@Override
public String doIt(){
    final Set<PaymentId> paymentIds = paySelectionBL.getPaymentIds(paySelectionId);
    openBankStatementReconciliationView(paymentIds);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(IProcessPreconditionsContext context){
    return checkBankStatementIsDraftOrInProcessOrCompleted(context).and(() -> checkSingleLineSelectedWhichIsNotReconciled(context));
}


}