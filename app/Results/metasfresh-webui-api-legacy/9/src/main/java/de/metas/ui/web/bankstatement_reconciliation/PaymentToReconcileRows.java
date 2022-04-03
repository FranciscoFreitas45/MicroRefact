package de.metas.ui.web.bankstatement_reconciliation;
 import java.util.List;
import java.util.Map;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.model.I_C_Payment;
import com.google.common.collect.ImmutableSet;
import de.metas.payment.PaymentId;
import de.metas.ui.web.view.template.IRowsData;
import de.metas.ui.web.view.template.SynchronizedRowsIndexHolder;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.Builder;
import lombok.NonNull;
public class PaymentToReconcileRows implements IRowsData<PaymentToReconcileRow>{

 private  BankStatementLineAndPaymentsToReconcileRepository repository;

 private  SynchronizedRowsIndexHolder<PaymentToReconcileRow> rowsHolder;


public PaymentToReconcileRows cast(IRowsData<PaymentToReconcileRow> rowsData){
    return (PaymentToReconcileRows) rowsData;
}


@Override
public DocumentIdsSelection getDocumentIdsToInvalidate(TableRecordReferenceSet recordRefs){
    return recordRefs.streamIds(I_C_Payment.Table_Name, PaymentId::ofRepoId).map(PaymentToReconcileRow::convertPaymentIdToDocumentId).filter(rowsHolder.isRelevantForRefreshingByDocumentId()).collect(DocumentIdsSelection.toDocumentIdsSelection());
}


@Override
public void invalidateAll(){
    invalidate(DocumentIdsSelection.ALL);
}


@Override
public void invalidate(DocumentIdsSelection rowIds){
    final ImmutableSet<PaymentId> paymentIds = rowsHolder.getRecordIdsToRefresh(rowIds, PaymentToReconcileRow::convertDocumentIdToPaymentId);
    final List<PaymentToReconcileRow> newRows = repository.getPaymentToReconcileRowsByIds(paymentIds);
    rowsHolder.compute(rows -> rows.replacingRows(rowIds, newRows));
}


@Override
public Map<DocumentId,PaymentToReconcileRow> getDocumentId2TopLevelRows(){
    return rowsHolder.getDocumentId2TopLevelRows();
}


}