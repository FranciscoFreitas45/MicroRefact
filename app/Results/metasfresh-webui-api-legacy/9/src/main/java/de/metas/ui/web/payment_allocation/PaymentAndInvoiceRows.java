package de.metas.ui.web.payment_allocation;
 import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class PaymentAndInvoiceRows {

@NonNull
 private PaymentRows paymentRows;

@NonNull
 private InvoiceRows invoiceRows;


}