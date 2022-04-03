package de.metas.ui.web.bankstatement_reconciliation;
 import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.adempiere.invoice.service.IInvoiceDAO;
import org.compiere.Adempiere;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_BankStatementLine;
import org.compiere.model.I_C_Payment;
import org.compiere.util.TimeUtil;
import org.springframework.stereotype.Repository;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.SetMultimap;
import de.metas.allocation.api.IAllocationDAO;
import de.metas.banking.BankStatementLineId;
import de.metas.banking.service.IBankStatementBL;
import de.metas.currency.Amount;
import de.metas.currency.CurrencyCode;
import de.metas.currency.CurrencyRepository;
import de.metas.invoice.InvoiceId;
import de.metas.money.CurrencyId;
import de.metas.payment.PaymentId;
import de.metas.payment.api.IPaymentDAO;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.util.GuavaCollectors;
import de.metas.util.ImmutableMapEntry;
import de.metas.util.Services;
import de.metas.util.StringUtils;
import lombok.NonNull;
@Repository
public class BankStatementLineAndPaymentsToReconcileRepository {

 private  IBankStatementBL bankStatementBL;

 private  IPaymentDAO paymentDAO;

 private  IInvoiceDAO invoiceDAO;

 private  IAllocationDAO allocationDAO;

 private  CurrencyRepository currencyRepository;

 private  LookupDataSource bpartnerLookup;


public Amount extractStatementLineAmt(I_C_BankStatementLine record){
    final CurrencyId currencyId = CurrencyId.ofRepoId(record.getC_Currency_ID());
    final CurrencyCode currencyCode = currencyRepository.getCurrencyCodeById(currencyId);
    final Amount statementLineAmt = Amount.of(record.getStmtAmt(), currencyCode);
    return statementLineAmt;
}


public ImmutableSetMultimap<PaymentId,String> getInvoiceDocumentNosByPaymentId(Set<PaymentId> paymentIds){
    final SetMultimap<PaymentId, InvoiceId> invoiceIdsByPaymentId = allocationDAO.retrieveInvoiceIdsByPaymentIds(paymentIds);
    final ImmutableMap<InvoiceId, String> invoiceDocumentNos = invoiceDAO.getDocumentNosByInvoiceIds(invoiceIdsByPaymentId.values());
    return invoiceIdsByPaymentId.entries().stream().map(GuavaCollectors.mapValue(invoiceDocumentNos::get)).filter(ImmutableMapEntry::isValueNotNull).collect(GuavaCollectors.toImmutableSetMultimap());
}


public List<BankStatementLineRow> getBankStatementLineRowsByIds(Set<BankStatementLineId> ids){
    if (ids.isEmpty()) {
        return ImmutableList.of();
    }
    return bankStatementBL.getLinesByIds(ids).stream().map(this::toBankStatementLineRow).collect(ImmutableList.toImmutableList());
}


public String joinInvoiceDocumentNos(Collection<String> documentNos){
    if (documentNos == null || documentNos.isEmpty()) {
        return "";
    }
    return documentNos.stream().map(StringUtils::trimBlankToNull).filter(Objects::nonNull).collect(Collectors.joining(", "));
}


public List<PaymentToReconcileRow> getPaymentToReconcileRowsByIds(Set<PaymentId> paymentIds){
    final ImmutableSetMultimap<PaymentId, String> invoiceDocumentNosByPaymentId = getInvoiceDocumentNosByPaymentId(paymentIds);
    return paymentDAO.getByIds(paymentIds).stream().map(record -> toPaymentToReconcileRow(record, invoiceDocumentNosByPaymentId)).collect(ImmutableList.toImmutableList());
}


public PaymentToReconcileRow toPaymentToReconcileRow(I_C_Payment record,ImmutableSetMultimap<PaymentId,String> invoiceDocumentNosByPaymentId){
    final CurrencyId currencyId = CurrencyId.ofRepoId(record.getC_Currency_ID());
    final CurrencyCode currencyCode = currencyRepository.getCurrencyCodeById(currencyId);
    final Amount payAmt = Amount.of(record.getPayAmt(), currencyCode);
    final PaymentId paymentId = PaymentId.ofRepoId(record.getC_Payment_ID());
    String invoiceDocumentNos = joinInvoiceDocumentNos(invoiceDocumentNosByPaymentId.get(paymentId));
    return PaymentToReconcileRow.builder().paymentId(paymentId).inboundPayment(record.isReceipt()).documentNo(record.getDocumentNo()).dateTrx(TimeUtil.asLocalDate(record.getDateTrx())).bpartner(bpartnerLookup.findById(record.getC_BPartner_ID())).invoiceDocumentNos(invoiceDocumentNos).payAmt(payAmt).reconciled(record.isReconciled()).build();
}


public BankStatementLineRow toBankStatementLineRow(I_C_BankStatementLine record){
    final Amount statementLineAmt = extractStatementLineAmt(record);
    return BankStatementLineRow.builder().bankStatementLineId(BankStatementLineId.ofRepoId(record.getC_BankStatementLine_ID())).lineNo(record.getLine()).dateAcct(TimeUtil.asLocalDate(record.getDateAcct())).statementLineAmt(statementLineAmt).description(record.getDescription()).reconciled(record.isReconciled()).build();
}


@VisibleForTesting
public void setBpartnerLookup(LookupDataSource bpartnerLookup){
    Adempiere.assertUnitTestMode();
    this.bpartnerLookup = bpartnerLookup;
}


}