package de.metas.ui.web.bankstatement_reconciliation;
 import java.util.Collection;
import com.google.common.collect.ImmutableSet;
import de.metas.banking.BankStatementLineId;
import de.metas.payment.PaymentId;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
@Value
public class BanksStatementReconciliationViewCreateRequest {

 private ImmutableSet<BankStatementLineId> bankStatementLineIds;

 private ImmutableSet<PaymentId> paymentIds;


}