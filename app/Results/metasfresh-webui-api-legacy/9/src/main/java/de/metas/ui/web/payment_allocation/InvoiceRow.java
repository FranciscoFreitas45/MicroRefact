package de.metas.ui.web.payment_allocation;
 import java.time.LocalDate;
import java.util.Set;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.model.I_C_Invoice;
import com.google.common.base.MoreObjects;
import de.metas.bpartner.BPartnerId;
import de.metas.currency.Amount;
import de.metas.i18n.ITranslatableString;
import de.metas.invoice.InvoiceId;
import de.metas.lang.SOTrx;
import de.metas.organization.ClientAndOrgId;
import de.metas.organization.OrgId;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValues;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValuesHolder;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.WidgetSize;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
public class InvoiceRow implements IViewRow{

@ViewColumn(seqNo = 10, widgetType = DocumentFieldWidgetType.Text, widgetSize = WidgetSize.Small, captionKey = "C_DocType_ID")
 private  ITranslatableString docTypeName;

@ViewColumn(seqNo = 20, widgetType = DocumentFieldWidgetType.Text, widgetSize = WidgetSize.Small, captionKey = "DocumentNo")
@Getter
 private  String documentNo;

@ViewColumn(seqNo = 30, widgetType = DocumentFieldWidgetType.LocalDate, widgetSize = WidgetSize.Small, captionKey = "DateInvoiced")
 private  LocalDate dateInvoiced;

@ViewColumn(seqNo = 40, widgetType = DocumentFieldWidgetType.Lookup, widgetSize = WidgetSize.Small, captionKey = "C_BPartner_ID")
 private  LookupValue bpartner;

@ViewColumn(seqNo = 50, widgetType = DocumentFieldWidgetType.Amount, widgetSize = WidgetSize.Small, captionKey = "GrandTotal")
 private  Amount grandTotal;

@ViewColumn(seqNo = 60, widgetType = DocumentFieldWidgetType.Amount, widgetSize = WidgetSize.Small, captionKey = "OpenAmt")
@Getter
 private  Amount openAmt;

@ViewColumn(seqNo = 70, widgetType = DocumentFieldWidgetType.Amount, widgetSize = WidgetSize.Small, captionKey = "Discount")
@Getter
 private  Amount discountAmt;

@ViewColumn(seqNo = 80, widgetType = DocumentFieldWidgetType.Text, widgetSize = WidgetSize.Small, captionKey = "C_Currency_ID")
 private  String currencyCode;

 private  DocumentId rowId;

@Getter
 private  InvoiceId invoiceId;

 private  ClientAndOrgId clientAndOrgId;

@Getter
 private  SOTrx soTrx;

@Getter
 private  boolean creditMemo;

 private  ViewRowFieldNameAndJsonValuesHolder<InvoiceRow> values;


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return values.get(this);
}


public DocumentId convertInvoiceIdToDocumentId(InvoiceId invoiceId){
    return DocumentId.of(invoiceId);
}


public OrgId getOrgId(){
    return clientAndOrgId.getOrgId();
}


@Override
public DocumentPath getDocumentPath(){
    return null;
}


@Override
public boolean isProcessed(){
    return false;
}


public DocumentId convertRecordRefToDocumentId(TableRecordReference recordRef){
    final InvoiceId invoiceId = recordRef.getIdAssumingTableName(I_C_Invoice.Table_Name, InvoiceId::ofRepoId);
    return convertInvoiceIdToDocumentId(invoiceId);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(values.get(this)).toString();
}


@Override
public DocumentId getId(){
    return rowId;
}


public InvoiceId convertDocumentIdToInvoiceId(DocumentId rowId){
    return rowId.toId(InvoiceId::ofRepoId);
}


@Override
public Set<String> getFieldNames(){
    return values.getFieldNames();
}


public BPartnerId getBPartnerId(){
    return bpartner.getIdAs(BPartnerId::ofRepoId);
}


}