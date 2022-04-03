package de.metas.ui.web.payment_allocation;
 import java.time.ZonedDateTime;
import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.model.I_C_Payment;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.payment.PaymentId;
import de.metas.ui.web.view.template.IRowsData;
import de.metas.ui.web.view.template.SynchronizedRowsIndexHolder;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.Builder;
import lombok.NonNull;
public class PaymentRows implements IRowsData<PaymentRow>{

 private  PaymentAndInvoiceRowsRepo repository;

 private  ZonedDateTime evaluationDate;

 private  SynchronizedRowsIndexHolder<PaymentRow> rowsHolder;


public PaymentRows cast(IRowsData<PaymentRow> rows){
    return (PaymentRows) rows;
}


@Override
public DocumentIdsSelection getDocumentIdsToInvalidate(TableRecordReferenceSet recordRefs){
    return recordRefs.streamIds(I_C_Payment.Table_Name, PaymentId::ofRepoId).map(PaymentRow::convertPaymentIdToDocumentId).filter(rowsHolder.isRelevantForRefreshingByDocumentId()).collect(DocumentIdsSelection.toDocumentIdsSelection());
}


@Override
public void invalidateAll(){
    invalidate(DocumentIdsSelection.ALL);
// nothing
}


@Override
public void invalidate(DocumentIdsSelection rowIds){
    final ImmutableSet<PaymentId> paymentIds = rowsHolder.getRecordIdsToRefresh(rowIds, PaymentRow::convertDocumentIdToPaymentId);
    final List<PaymentRow> newRows = repository.getPaymentRowsListByPaymentId(paymentIds, evaluationDate);
    rowsHolder.compute(rows -> rows.replacingRows(rowIds, newRows));
}


public void addPayment(PaymentId paymentId){
    final PaymentRow row = repository.getPaymentRowByPaymentId(paymentId, evaluationDate).orElse(null);
    if (row == null) {
        throw new AdempiereException("@PaymentNotOpen@");
    }
    rowsHolder.compute(rows -> rows.addingRow(row));
}


@Override
public ImmutableMap<DocumentId,PaymentRow> getDocumentId2TopLevelRows(){
    return rowsHolder.getDocumentId2TopLevelRows();
}


}