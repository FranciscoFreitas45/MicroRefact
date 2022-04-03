package de.metas.banking.process;
 import java.util.Set;
import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.model.I_C_BankStatement;
import org.compiere.model.I_C_BankStatementLine;
import org.compiere.model.I_C_Payment;
import de.metas.bpartner.BPartnerId;
import de.metas.payment.PaymentId;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.process.descriptor.ProcessParamLookupValuesProvider;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import lombok.NonNull;
public class C_BankStatement_ReconcileWithSinglePayment extends BankStatementBasedProcessimplements IProcessDefaultParametersProvider{

 private  String PARAM_C_BPartner_ID;

@Param(parameterName = PARAM_C_BPartner_ID, mandatory = true)
 private  BPartnerId bpartnerId;

 private  String PARAM_C_Payment_ID;

@Param(parameterName = PARAM_C_Payment_ID)
 private  PaymentId paymentId;


@Override
public Object getParameterDefaultValue(IProcessDefaultParameter parameter){
    if (PARAM_C_BPartner_ID.contentEquals(parameter.getColumnName())) {
        final I_C_BankStatementLine bankStatementLine = getSingleSelectedBankStatementLine();
        final BPartnerId bpartnerId = BPartnerId.ofRepoIdOrNull(bankStatementLine.getC_BPartner_ID());
        if (bpartnerId != null) {
            return bpartnerId;
        }
    }
    return DEFAULT_VALUE_NOTAVAILABLE;
}


@ProcessParamLookupValuesProvider(parameterName = PARAM_C_Payment_ID, numericKey = true, lookupSource = DocumentLayoutElementFieldDescriptor.LookupSource.lookup, lookupTableName = I_C_Payment.Table_Name)
public LookupValuesList paymentLookupProvider(){
    if (bpartnerId == null) {
        return LookupValuesList.EMPTY;
    }
    final I_C_BankStatementLine bankStatementLine = getSingleSelectedBankStatementLine();
    final int limit = 20;
    final Set<PaymentId> paymentIds = bankStatementPaymentBL.findEligiblePaymentIds(bankStatementLine, bpartnerId, limit);
    return LookupDataSourceFactory.instance.searchInTableLookup(I_C_Payment.Table_Name).findByIdsOrdered(paymentIds);
}


@Override
public String doIt(){
    final I_C_BankStatement bankStatement = getSelectedBankStatement();
    final I_C_BankStatementLine bankStatementLine = getSingleSelectedBankStatementLine();
    bankStatementLine.setC_BPartner_ID(bpartnerId.getRepoId());
    if (paymentId != null) {
        bankStatementPaymentBL.linkSinglePayment(bankStatement, bankStatementLine, paymentId);
    } else {
        final Set<PaymentId> eligiblePaymentIds = bankStatementPaymentBL.findEligiblePaymentIds(bankStatementLine, bpartnerId, 2);
        if (eligiblePaymentIds.isEmpty()) {
            bankStatementPaymentBL.createSinglePaymentAndLink(bankStatement, bankStatementLine);
        } else if (eligiblePaymentIds.size() == 1) {
            PaymentId eligiblePaymentId = eligiblePaymentIds.iterator().next();
            bankStatementPaymentBL.linkSinglePayment(bankStatement, bankStatementLine, eligiblePaymentId);
        } else {
            throw new FillMandatoryException(PARAM_C_Payment_ID);
        }
    }
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(IProcessPreconditionsContext context){
    return checkBankStatementIsDraftOrInProcessOrCompleted(context).and(() -> checkSingleLineSelectedWhichIsNotReconciled(context));
}


}