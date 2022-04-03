package de.metas.banking.process;
 import java.util.Set;
import de.metas.payment.PaymentId;
import de.metas.payment.esr.ESRImportId;
import de.metas.payment.esr.api.IESRImportBL;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.util.Services;
import lombok.NonNull;
public class C_BankStatement_ReconcileESRImport extends BankStatementBasedProcess{

 private  IESRImportBL esrImportBL;

@Param(parameterName = "ESR_Import_ID", mandatory = true)
 private  ESRImportId esrImportId;


@Override
public String doIt(){
    final Set<PaymentId> paymentIds = esrImportBL.getPaymentIds(esrImportId);
    openBankStatementReconciliationView(paymentIds);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(IProcessPreconditionsContext context){
    return checkBankStatementIsDraftOrInProcessOrCompleted(context).and(() -> checkSingleLineSelectedWhichIsNotReconciled(context));
}


}