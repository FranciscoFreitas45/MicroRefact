package de.metas.banking.process;
 import java.util.Collection;
import java.util.Set;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.SpringContextHolder;
import org.compiere.model.I_C_BankStatement;
import org.compiere.model.I_C_BankStatementLine;
import de.metas.banking.BankStatementId;
import de.metas.banking.BankStatementLineId;
import de.metas.banking.payment.IBankStatementPaymentBL;
import de.metas.banking.service.IBankStatementBL;
import de.metas.document.engine.DocStatus;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.IMsgBL;
import de.metas.payment.PaymentId;
import de.metas.process.IProcessPrecondition;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.JavaProcess;
import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.bankstatement_reconciliation.BankStatementReconciliationView;
import de.metas.ui.web.bankstatement_reconciliation.BankStatementReconciliationViewFactory;
import de.metas.ui.web.bankstatement_reconciliation.BanksStatementReconciliationViewCreateRequest;
import de.metas.ui.web.view.ViewId;
import de.metas.util.Services;
import lombok.NonNull;
public class BankStatementBasedProcess extends JavaProcessimplements IProcessPrecondition{

 protected  AdMessageKey MSG_BankStatement_MustBe_Draft_InProgress_Or_Completed;

 private  AdMessageKey MSG_LineIsAlreadyReconciled;

 protected  IMsgBL msgBL;

 protected  IBankStatementBL bankStatementBL;

 protected  IBankStatementPaymentBL bankStatementPaymentBL;

 private  BankStatementReconciliationViewFactory bankStatementReconciliationViewFactory;


public ProcessPreconditionsResolution checkSingleLineSelectedWhichIsNotReconciled(IProcessPreconditionsContext context){
    // there should be a single line selected
    final Set<TableRecordReference> bankStatemementLineRefs = context.getSelectedIncludedRecords();
    if (bankStatemementLineRefs.size() != 1) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final TableRecordReference bankStatemementLineRef = bankStatemementLineRefs.iterator().next();
    final BankStatementLineId bankStatementLineId = BankStatementLineId.ofRepoId(bankStatemementLineRef.getRecord_ID());
    final I_C_BankStatementLine line = bankStatementBL.getLineById(bankStatementLineId);
    if (line.isReconciled()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_LineIsAlreadyReconciled));
    }
    return ProcessPreconditionsResolution.accept();
}


public BankStatementLineId getSingleSelectedBankStatementLineId(){
    final Set<Integer> bankStatementLineRepoIds = getSelectedIncludedRecordIds(I_C_BankStatementLine.class);
    if (bankStatementLineRepoIds.isEmpty()) {
        throw new AdempiereException("@NoSelection@");
    } else if (bankStatementLineRepoIds.size() == 1) {
        return BankStatementLineId.ofRepoId(bankStatementLineRepoIds.iterator().next());
    } else {
        throw new AdempiereException("More than one bank statement line selected: " + bankStatementLineRepoIds);
    }
}


public ProcessPreconditionsResolution checkBankStatementIsDraftOrInProcessOrCompleted(IProcessPreconditionsContext context){
    if (context.isNoSelection()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    if (!context.isSingleSelection()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final BankStatementId bankStatementId = BankStatementId.ofRepoId(context.getSingleSelectedRecordId());
    final I_C_BankStatement bankStatement = bankStatementBL.getById(bankStatementId);
    final DocStatus docStatus = DocStatus.ofCode(bankStatement.getDocStatus());
    if (!docStatus.isDraftedInProgressOrCompleted()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_BankStatement_MustBe_Draft_InProgress_Or_Completed));
    }
    return ProcessPreconditionsResolution.accept();
}


public I_C_BankStatementLine getSingleSelectedBankStatementLine(){
    final BankStatementLineId lineId = getSingleSelectedBankStatementLineId();
    return bankStatementBL.getLineById(lineId);
}


public void openBankStatementReconciliationView(Collection<PaymentId> paymentIds){
    if (paymentIds.isEmpty()) {
        throw new AdempiereException("@NoPayments@");
    }
    final BankStatementReconciliationView view = bankStatementReconciliationViewFactory.createView(BanksStatementReconciliationViewCreateRequest.builder().bankStatementLineId(getSingleSelectedBankStatementLineId()).paymentIds(paymentIds).build());
    final ViewId viewId = view.getViewId();
    getResult().setWebuiViewToOpen(WebuiViewToOpen.builder().viewId(viewId.toJson()).target(ViewOpenTarget.ModalOverlay).build());
}


public I_C_BankStatement getSelectedBankStatement(){
    final BankStatementId bankStatementId = BankStatementId.ofRepoId(getRecord_ID());
    return bankStatementBL.getById(bankStatementId);
}


}