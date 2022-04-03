package de.metas.ui.web.payment_allocation;
 import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_BPartner;
import org.springframework.stereotype.Repository;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.banking.payment.paymentallocation.InvoiceToAllocate;
import de.metas.banking.payment.paymentallocation.InvoiceToAllocateQuery;
import de.metas.banking.payment.paymentallocation.InvoiceToAllocateQuery.InvoiceToAllocateQueryBuilder;
import de.metas.banking.payment.paymentallocation.PaymentAllocationRepository;
import de.metas.banking.payment.paymentallocation.PaymentToAllocate;
import de.metas.banking.payment.paymentallocation.PaymentToAllocateQuery;
import de.metas.bpartner.BPartnerId;
import de.metas.currency.CurrencyCode;
import de.metas.currency.CurrencyRepository;
import de.metas.document.IDocTypeBL;
import de.metas.invoice.InvoiceId;
import de.metas.money.CurrencyId;
import de.metas.payment.PaymentId;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.util.Check;
import de.metas.util.Services;
import de.metas.util.time.SystemTime;
import lombok.NonNull;
@Repository
public class PaymentAndInvoiceRowsRepo {

 private  IDocTypeBL docTypeBL;

 private  CurrencyRepository currenciesRepo;

 private  PaymentAllocationRepository paymentAllocationRepo;

 private  LookupDataSource bpartnersLookup;


public Optional<PaymentRow> getPaymentRowByPaymentId(PaymentId paymentId,ZonedDateTime evaluationDate){
    final List<PaymentRow> paymentRows = getPaymentRowsListByPaymentId(ImmutableList.of(paymentId), evaluationDate);
    if (paymentRows.isEmpty()) {
        return Optional.empty();
    } else if (paymentRows.size() == 1) {
        return Optional.of(paymentRows.get(0));
    } else {
        throw new AdempiereException("Expected only one row for " + paymentId + " but got " + paymentRows);
    }
}


public PaymentRow toPaymentRow(PaymentToAllocate paymentToAllocate){
    final BPartnerId bpartnerId = paymentToAllocate.getBpartnerId();
    return PaymentRow.builder().paymentId(paymentToAllocate.getPaymentId()).clientAndOrgId(paymentToAllocate.getClientAndOrgId()).documentNo(paymentToAllocate.getDocumentNo()).bpartner(bpartnersLookup.findById(bpartnerId)).dateTrx(paymentToAllocate.getDateTrx()).payAmt(paymentToAllocate.getPayAmt()).openAmt(paymentToAllocate.getOpenAmt()).paymentDirection(paymentToAllocate.getPaymentDirection()).build();
}


public InvoiceRow toInvoiceRow(InvoiceToAllocate invoiceToAllocate){
    return InvoiceRow.builder().invoiceId(invoiceToAllocate.getInvoiceId()).clientAndOrgId(invoiceToAllocate.getClientAndOrgId()).docTypeName(docTypeBL.getNameById(invoiceToAllocate.getDocTypeId())).documentNo(invoiceToAllocate.getDocumentNo()).dateInvoiced(invoiceToAllocate.getDateInvoiced()).bpartner(bpartnersLookup.findById(invoiceToAllocate.getBpartnerId())).soTrx(invoiceToAllocate.getSoTrx()).creditMemo(invoiceToAllocate.isCreditMemo()).grandTotal(invoiceToAllocate.getGrandTotal()).openAmt(invoiceToAllocate.getOpenAmountConverted()).discountAmt(invoiceToAllocate.getDiscountAmountConverted()).build();
}


public InvoiceRows retrieveInvoiceRowsByPayments(List<PaymentToAllocate> paymentsToAllocate,ZonedDateTime evaluationDate){
    final ImmutableSet<InvoiceToAllocateQuery> queries = paymentsToAllocate.stream().map(paymentToAllocate -> prepareInvoiceToAllocateQuery(paymentToAllocate).evaluationDate(evaluationDate).build()).collect(ImmutableSet.toImmutableSet());
    final ImmutableList<InvoiceRow> rows = paymentAllocationRepo.retrieveInvoicesToAllocate(queries).stream().map(this::toInvoiceRow).collect(ImmutableList.toImmutableList());
    return InvoiceRows.builder().repository(this).evaluationDate(evaluationDate).initialRows(rows).build();
}


public PaymentRows toPaymentRows(List<PaymentToAllocate> paymentsToAllocate,ZonedDateTime evaluationDate){
    final ImmutableList<PaymentRow> rows = paymentsToAllocate.stream().map(this::toPaymentRow).collect(ImmutableList.toImmutableList());
    return PaymentRows.builder().repository(this).evaluationDate(evaluationDate).initialRows(rows).build();
}


public List<PaymentRow> getPaymentRowsListByPaymentId(Collection<PaymentId> paymentIds,ZonedDateTime evaluationDate){
    if (paymentIds.isEmpty()) {
        return ImmutableList.of();
    }
    final PaymentToAllocateQuery query = PaymentToAllocateQuery.builder().evaluationDate(evaluationDate).additionalPaymentIdsToInclude(paymentIds).build();
    return paymentAllocationRepo.retrievePaymentsToAllocate(query).stream().map(this::toPaymentRow).collect(ImmutableList.toImmutableList());
}


public InvoiceToAllocateQueryBuilder prepareInvoiceToAllocateQuery(PaymentToAllocate paymentToAllocate){
    final CurrencyCode currencyCode = paymentToAllocate.getOpenAmt().getCurrencyCode();
    final CurrencyId currencyId = currenciesRepo.getCurrencyIdByCurrencyCode(currencyCode);
    return InvoiceToAllocateQuery.builder().bpartnerId(paymentToAllocate.getBpartnerId()).currencyId(currencyId).clientAndOrgId(paymentToAllocate.getClientAndOrgId());
}


public List<InvoiceRow> getInvoiceRowsListByInvoiceId(Collection<InvoiceId> invoiceIds,ZonedDateTime evaluationDate){
    if (invoiceIds.isEmpty()) {
        return ImmutableList.of();
    }
    final InvoiceToAllocateQuery query = InvoiceToAllocateQuery.builder().evaluationDate(evaluationDate).additionalInvoiceIdsToInclude(invoiceIds).build();
    return paymentAllocationRepo.retrieveInvoicesToAllocate(query).stream().map(this::toInvoiceRow).collect(ImmutableList.toImmutableList());
}


public PaymentAndInvoiceRows getByPaymentIds(Set<PaymentId> paymentIds){
    Check.assumeNotEmpty(paymentIds, "paymentIds is not empty");
    final ZonedDateTime evaluationDate = SystemTime.asZonedDateTime();
    final List<PaymentToAllocate> paymentsToAllocate = paymentAllocationRepo.retrievePaymentsToAllocate(PaymentToAllocateQuery.builder().evaluationDate(evaluationDate).additionalPaymentIdsToInclude(paymentIds).build());
    if (paymentsToAllocate.isEmpty()) {
        throw new AdempiereException("@NoOpenPayments@");
    }
    final PaymentRows paymentRows = toPaymentRows(paymentsToAllocate, evaluationDate);
    final InvoiceRows invoiceRows = retrieveInvoiceRowsByPayments(paymentsToAllocate, evaluationDate);
    return PaymentAndInvoiceRows.builder().paymentRows(paymentRows).invoiceRows(invoiceRows).build();
}


public Optional<InvoiceRow> getInvoiceRowByInvoiceId(InvoiceId invoiceId,ZonedDateTime evaluationDate){
    final List<InvoiceRow> invoiceRows = getInvoiceRowsListByInvoiceId(ImmutableList.of(invoiceId), evaluationDate);
    if (invoiceRows.isEmpty()) {
        return Optional.empty();
    } else if (invoiceRows.size() == 1) {
        return Optional.of(invoiceRows.get(0));
    } else {
        throw new AdempiereException("Expected only one row for " + invoiceId + " but got " + invoiceRows);
    }
}


}