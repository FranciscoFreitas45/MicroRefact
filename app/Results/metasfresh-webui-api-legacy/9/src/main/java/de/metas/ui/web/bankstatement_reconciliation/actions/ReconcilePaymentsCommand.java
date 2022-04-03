package de.metas.ui.web.bankstatement_reconciliation.actions;
 import java.util.ArrayList;
import java.util.List;
import com.google.common.annotations.VisibleForTesting;
import de.metas.banking.payment.BankStatementLineMultiPaymentLinkRequest;
import de.metas.banking.payment.BankStatementLineMultiPaymentLinkRequest.PaymentToLink;
import de.metas.banking.payment.IBankStatementPaymentBL;
import de.metas.currency.Amount;
import de.metas.currency.CurrencyCode;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.ExplainedOptional;
import de.metas.i18n.IMsgBL;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.bankstatement_reconciliation.BankStatementLineRow;
import de.metas.ui.web.bankstatement_reconciliation.PaymentToReconcileRow;
import lombok.Builder;
import lombok.NonNull;
public class ReconcilePaymentsCommand {

@VisibleForTesting
 static  AdMessageKey MSG_StatementLineAmtToReconcileIs;

 private  IMsgBL msgBL;

 private  IBankStatementPaymentBL bankStatmentPaymentBL;

 private  ReconcilePaymentsRequest request;


public ProcessPreconditionsResolution checkCanExecute(){
    // 
    final BankStatementLineRow bankStatementLine = request.getSelectedBankStatementLine();
    if (bankStatementLine == null) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("no bank statement line selected");
    }
    if (bankStatementLine.isReconciled()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("bank statement line was already reconciled");
    }
    // 
    final List<PaymentToReconcileRow> payments = request.getSelectedPaymentsToReconcile();
    if (payments.isEmpty()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("no payment rows selected");
    }
    // 
    final ExplainedOptional<BankStatementLineMultiPaymentLinkRequest> optionalRequest = computeBankStatementLineReconcileRequest();
    if (!optionalRequest.isPresent()) {
        return ProcessPreconditionsResolution.reject(optionalRequest.getExplanation());
    }
    return ProcessPreconditionsResolution.accept();
}


public ExplainedOptional<BankStatementLineMultiPaymentLinkRequest> computeBankStatementLineReconcileRequest(){
    final BankStatementLineRow bankStatementLineRow = request.getSelectedBankStatementLine();
    if (bankStatementLineRow == null) {
        return ExplainedOptional.emptyBecause("no bank statement line selected");
    }
    if (bankStatementLineRow.isReconciled()) {
        return ExplainedOptional.emptyBecause("bank statement line was already reconciled");
    }
    final List<PaymentToReconcileRow> paymentRows = request.getSelectedPaymentsToReconcile();
    if (paymentRows.isEmpty()) {
        return ExplainedOptional.emptyBecause("no payment rows selected");
    }
    final Amount statementLineAmt = bankStatementLineRow.getStatementLineAmt();
    final CurrencyCode currencyCode = statementLineAmt.getCurrencyCode();
    Amount statementLineAmtReconciled = Amount.zero(currencyCode);
    final ArrayList<PaymentToLink> paymentsToReconcile = new ArrayList<>();
    for (final PaymentToReconcileRow paymentRow : paymentRows) {
        if (paymentRow.isReconciled()) {
            return ExplainedOptional.emptyBecause("Payment `" + paymentRow.getDocumentNo() + "` was already reconciled");
        }
        final Amount payAmt = paymentRow.getPayAmtNegateIfOutbound();
        if (!payAmt.getCurrencyCode().equals(currencyCode)) {
            return ExplainedOptional.emptyBecause("Payment `" + paymentRow.getDocumentNo() + "` shall be in `" + currencyCode + "` instead of `" + payAmt.getCurrencyCode() + "`");
        }
        statementLineAmtReconciled = statementLineAmtReconciled.add(payAmt);
        paymentsToReconcile.add(PaymentToLink.builder().paymentId(paymentRow.getPaymentId()).statementLineAmt(payAmt).build());
    }
    final Amount statementLineAmtToReconcile = statementLineAmt.subtract(statementLineAmtReconciled);
    if (!statementLineAmtToReconcile.isZero()) {
        return ExplainedOptional.emptyBecause(msgBL.getTranslatableMsgText(MSG_StatementLineAmtToReconcileIs, statementLineAmtToReconcile));
    }
    return ExplainedOptional.of(BankStatementLineMultiPaymentLinkRequest.builder().bankStatementLineId(bankStatementLineRow.getBankStatementLineId()).paymentsToLink(paymentsToReconcile).build());
}


public void execute(){
    final BankStatementLineMultiPaymentLinkRequest request = computeBankStatementLineReconcileRequest().get();
    bankStatmentPaymentBL.linkMultiPayments(request);
}


}