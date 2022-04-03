package de.metas.banking.process;
 import java.util.Set;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.model.I_C_BankStatement;
import org.compiere.model.I_C_BankStatementLine;
import com.google.common.collect.ImmutableList;
import de.metas.banking.BankStatementLineId;
import de.metas.document.engine.DocStatus;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.ProcessPreconditionsResolution;
import lombok.NonNull;
public class C_BankStatement_UnReconcileLine extends BankStatementBasedProcess{


public ProcessPreconditionsResolution checkSingleLineSelectedWhichIsReconciled(IProcessPreconditionsContext context){
    // there should be a single line selected
    final Set<TableRecordReference> bankStatemementLineRefs = context.getSelectedIncludedRecords();
    if (bankStatemementLineRefs.size() != 1) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("a single line shall be selected");
    }
    final TableRecordReference bankStatemementLineRef = bankStatemementLineRefs.iterator().next();
    final BankStatementLineId bankStatementLineId = BankStatementLineId.ofRepoId(bankStatemementLineRef.getRecord_ID());
    final I_C_BankStatementLine line = bankStatementBL.getLineById(bankStatementLineId);
    if (!line.isReconciled()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("line shall be reconciled");
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public String doIt(){
    final I_C_BankStatement bankStatement = getSelectedBankStatement();
    final DocStatus docStatus = DocStatus.ofCode(bankStatement.getDocStatus());
    if (!docStatus.isDraftedInProgressOrCompleted()) {
        throw new AdempiereException(msgBL.getTranslatableMsgText(MSG_BankStatement_MustBe_Draft_InProgress_Or_Completed));
    }
    final I_C_BankStatementLine bankStatementLine = getSingleSelectedBankStatementLine();
    bankStatementBL.unlinkPaymentsAndDeleteReferences(ImmutableList.of(bankStatementLine));
    bankStatementBL.unpost(bankStatement);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(IProcessPreconditionsContext context){
    return checkBankStatementIsDraftOrInProcessOrCompleted(context).and(() -> checkSingleLineSelectedWhichIsReconciled(context));
}


}