package de.metas.ui.web.payment_allocation;
 import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.model.I_C_Invoice;
import com.google.common.collect.ImmutableSet;
import de.metas.invoice.InvoiceId;
import de.metas.ui.web.view.template.IRowsData;
import de.metas.ui.web.view.template.SynchronizedRowsIndexHolder;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.Builder;
import lombok.NonNull;
public class InvoiceRows implements IRowsData<InvoiceRow>{

 private  PaymentAndInvoiceRowsRepo repository;

 private  ZonedDateTime evaluationDate;

 private  SynchronizedRowsIndexHolder<InvoiceRow> rowsHolder;


public InvoiceRows cast(IRowsData<InvoiceRow> rows){
    return (InvoiceRows) rows;
}


@Override
public DocumentIdsSelection getDocumentIdsToInvalidate(TableRecordReferenceSet recordRefs){
    return recordRefs.streamIds(I_C_Invoice.Table_Name, InvoiceId::ofRepoId).map(InvoiceRow::convertInvoiceIdToDocumentId).filter(rowsHolder.isRelevantForRefreshingByDocumentId()).collect(DocumentIdsSelection.toDocumentIdsSelection());
}


@Override
public void invalidateAll(){
    invalidate(DocumentIdsSelection.ALL);
}


@Override
public void invalidate(DocumentIdsSelection rowIds){
    final ImmutableSet<InvoiceId> invoiceIds = rowsHolder.getRecordIdsToRefresh(rowIds, InvoiceRow::convertDocumentIdToInvoiceId);
    final List<InvoiceRow> newRows = repository.getInvoiceRowsListByInvoiceId(invoiceIds, evaluationDate);
    rowsHolder.compute(rows -> rows.replacingRows(rowIds, newRows));
}


@Override
public Map<DocumentId,InvoiceRow> getDocumentId2TopLevelRows(){
    return rowsHolder.getDocumentId2TopLevelRows();
}


public void addInvoice(InvoiceId invoiceId){
    final InvoiceRow row = repository.getInvoiceRowByInvoiceId(invoiceId, evaluationDate).orElse(null);
    if (row == null) {
        throw new AdempiereException("@InvoiceNotOpen@");
    }
    rowsHolder.compute(rows -> rows.addingRow(row));
}


}