package de.metas.ui.web.bankstatement_reconciliation;
 import java.util.List;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.model.I_C_BankStatementLine;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.banking.BankStatementLineId;
import de.metas.ui.web.view.template.IRowsData;
import de.metas.ui.web.view.template.SynchronizedRowsIndexHolder;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.Builder;
import lombok.NonNull;
public class BankStatementLineRows implements IRowsData<BankStatementLineRow>{

 private  BankStatementLineAndPaymentsToReconcileRepository repository;

 private  SynchronizedRowsIndexHolder<BankStatementLineRow> rowsHolder;


public BankStatementLineRows cast(IRowsData<BankStatementLineRow> rowsData){
    return (BankStatementLineRows) rowsData;
}


@Override
public DocumentIdsSelection getDocumentIdsToInvalidate(TableRecordReferenceSet recordRefs){
    return recordRefs.streamIds(I_C_BankStatementLine.Table_Name, BankStatementLineId::ofRepoId).map(BankStatementLineRow::convertBankStatementLineIdToDocumentId).filter(rowsHolder.isRelevantForRefreshingByDocumentId()).collect(DocumentIdsSelection.toDocumentIdsSelection());
}


@Override
public void invalidateAll(){
    invalidate(DocumentIdsSelection.ALL);
}


@Override
public void invalidate(DocumentIdsSelection rowIds){
    final ImmutableSet<BankStatementLineId> bankStatementLineIds = rowsHolder.getRecordIdsToRefresh(rowIds, BankStatementLineRow::convertDocumentIdToBankStatementLineId);
    final List<BankStatementLineRow> newRows = repository.getBankStatementLineRowsByIds(bankStatementLineIds);
    rowsHolder.compute(rows -> rows.replacingRows(rowIds, newRows));
}


@Override
public ImmutableMap<DocumentId,BankStatementLineRow> getDocumentId2TopLevelRows(){
    return rowsHolder.getDocumentId2TopLevelRows();
}


}