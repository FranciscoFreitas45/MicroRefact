package de.metas.ui.web.bankstatement_reconciliation;
 import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class BankStatementLineAndPaymentsRows {

@NonNull
 private BankStatementLineRows bankStatementLineRows;

@NonNull
 private PaymentToReconcileRows paymentToReconcileRows;


}