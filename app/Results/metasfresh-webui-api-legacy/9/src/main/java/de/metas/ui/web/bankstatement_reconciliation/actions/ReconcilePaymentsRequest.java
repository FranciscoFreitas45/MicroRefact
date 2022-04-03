package de.metas.ui.web.bankstatement_reconciliation.actions;
 import javax.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.bankstatement_reconciliation.BankStatementLineRow;
import de.metas.ui.web.bankstatement_reconciliation.PaymentToReconcileRow;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
@Value
@Builder
public class ReconcilePaymentsRequest {

@Nullable
 private BankStatementLineRow selectedBankStatementLine;

@NonNull
@Singular("selectedPaymentToReconcile")
 private ImmutableList<PaymentToReconcileRow> selectedPaymentsToReconcile;


}