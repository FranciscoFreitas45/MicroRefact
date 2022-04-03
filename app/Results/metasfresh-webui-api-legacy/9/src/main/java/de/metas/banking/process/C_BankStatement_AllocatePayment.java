package de.metas.banking.process;
 import org.adempiere.exceptions.AdempiereException;
import org.compiere.SpringContextHolder;
import org.compiere.model.I_C_BankStatement;
import com.google.common.collect.ImmutableSet;
import de.metas.banking.BankStatementId;
import de.metas.document.engine.DocStatus;
import de.metas.i18n.AdMessageKey;
import de.metas.payment.PaymentId;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.ProcessExecutionResult;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.payment_allocation.PaymentsViewFactory;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import lombok.NonNull;
public class C_BankStatement_AllocatePayment extends BankStatementBasedProcess{

 private  AdMessageKey BANK_STATEMENT_MUST_BE_COMPLETED_MSG;

 private  IViewsRepository viewsFactory;


@Override
public String doIt(){
    final ImmutableSet<PaymentId> paymentIds = retrievePaymentIds();
    if (paymentIds.isEmpty()) {
        throw new AdempiereException("@NoOpenPayments@").markAsUserValidationError();
    }
    final ViewId viewId = viewsFactory.createView(CreateViewRequest.builder(PaymentsViewFactory.WINDOW_ID).setFilterOnlyIds(PaymentId.toIntSet(paymentIds)).build()).getViewId();
    getResult().setWebuiViewToOpen(ProcessExecutionResult.WebuiViewToOpen.builder().viewId(viewId.getViewId()).target(ProcessExecutionResult.ViewOpenTarget.ModalOverlay).build());
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(IProcessPreconditionsContext context){
    if (context.isNoSelection()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    if (!context.isSingleSelection()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final BankStatementId bankStatementId = BankStatementId.ofRepoId(context.getSingleSelectedRecordId());
    final I_C_BankStatement bankStatement = bankStatementBL.getById(bankStatementId);
    final DocStatus docStatus = DocStatus.ofCode(bankStatement.getDocStatus());
    if (!docStatus.isCompleted()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(BANK_STATEMENT_MUST_BE_COMPLETED_MSG));
    }
    return ProcessPreconditionsResolution.accept();
}


public ImmutableSet<PaymentId> retrievePaymentIds(){
    final BankStatementId bankStatementId = BankStatementId.ofRepoId(getRecord_ID());
    return bankStatementBL.getLinesPaymentIds(bankStatementId);
}


}