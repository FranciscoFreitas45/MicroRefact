package de.metas.ui.web.payment_allocation;
 import java.time.LocalDate;
import java.util.Set;
import de.metas.bpartner.BPartnerId;
import de.metas.currency.Amount;
import de.metas.organization.ClientAndOrgId;
import de.metas.organization.OrgId;
import de.metas.payment.PaymentDirection;
import de.metas.payment.PaymentId;
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
public class PaymentRow implements IViewRow{

@ViewColumn(seqNo = 10, widgetType = DocumentFieldWidgetType.YesNo, widgetSize = WidgetSize.Small, captionKey = "IsReceipt")
 private  boolean inboundPayment;

@ViewColumn(seqNo = 20, widgetType = DocumentFieldWidgetType.Text, widgetSize = WidgetSize.Small, captionKey = "DocumentNo")
@Getter
 private  String documentNo;

@ViewColumn(seqNo = 30, widgetType = DocumentFieldWidgetType.LocalDate, widgetSize = WidgetSize.Small, captionKey = "DateTrx")
 private  LocalDate dateTrx;

@ViewColumn(seqNo = 40, widgetType = DocumentFieldWidgetType.Lookup, widgetSize = WidgetSize.Small, captionKey = "C_BPartner_ID")
 private  LookupValue bpartner;

@ViewColumn(seqNo = 50, widgetType = DocumentFieldWidgetType.Amount, widgetSize = WidgetSize.Small, captionKey = "PayAmt")
@Getter
 private  Amount payAmt;

@ViewColumn(seqNo = 60, widgetType = DocumentFieldWidgetType.Amount, widgetSize = WidgetSize.Small, captionKey = "OpenAmt")
@Getter
 private  Amount openAmt;

@ViewColumn(seqNo = 70, widgetType = DocumentFieldWidgetType.Text, widgetSize = WidgetSize.Small, captionKey = "C_Currency_ID")
 private  String currencyCode;

 private  DocumentId rowId;

@Getter
 private  PaymentId paymentId;

 private  ClientAndOrgId clientAndOrgId;

@Getter
 private  PaymentDirection paymentDirection;

 private  ViewRowFieldNameAndJsonValuesHolder<PaymentRow> values;


public DocumentId convertPaymentIdToDocumentId(PaymentId paymentId){
    return DocumentId.of(paymentId);
}


public PaymentId convertDocumentIdToPaymentId(DocumentId rowId){
    return rowId.toId(PaymentId::ofRepoId);
}


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return values.get(this);
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


@Override
public DocumentId getId(){
    return rowId;
}


@Override
public Set<String> getFieldNames(){
    return values.getFieldNames();
}


public BPartnerId getBPartnerId(){
    return bpartner.getIdAs(BPartnerId::ofRepoId);
}


}