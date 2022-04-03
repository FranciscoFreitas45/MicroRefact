package de.metas.ui.web.bankstatement_reconciliation.process;
 import java.util.List;
import java.util.stream.Stream;
import com.google.common.collect.ImmutableList;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.bankstatement_reconciliation.BankStatementLineRow;
import de.metas.ui.web.bankstatement_reconciliation.BankStatementReconciliationView;
import de.metas.ui.web.bankstatement_reconciliation.PaymentToReconcileRow;
import de.metas.ui.web.bankstatement_reconciliation.PaymentsToReconcileView;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewRowIdsSelection;
public class PaymentsToReconcileViewBasedProcess extends ViewBasedProcessTemplateimplements IProcessPrecondition{


public BankStatementLineRow getSingleSelectedBankStatementRowOrNull(){
    final ViewRowIdsSelection selection = getParentViewRowIdsSelection();
    if (selection == null || selection.isEmpty()) {
        return null;
    }
    final ImmutableList<BankStatementLineRow> rows = getBanksStatementReconciliationView().streamByIds(selection).collect(ImmutableList.toImmutableList());
    return rows.size() == 1 ? rows.get(0) : null;
}


@Override
public Stream<PaymentToReconcileRow> streamSelectedRows(){
    return super.streamSelectedRows().map(PaymentToReconcileRow::cast);
}


public List<PaymentToReconcileRow> getSelectedPaymentToReconcileRows(){
    return streamSelectedRows().collect(ImmutableList.toImmutableList());
}


@Override
public PaymentToReconcileRow getSingleSelectedRow(){
    return PaymentToReconcileRow.cast(super.getSingleSelectedRow());
}


public void invalidateBankStatementReconciliationView(){
    invalidateView(getBanksStatementReconciliationViewId());
}


public BankStatementReconciliationView getBanksStatementReconciliationView(){
    final ViewId bankStatementViewId = getBanksStatementReconciliationViewId();
    final IViewsRepository viewsRepo = getViewsRepo();
    return BankStatementReconciliationView.cast(viewsRepo.getView(bankStatementViewId));
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable()


@Override
public PaymentsToReconcileView getView(){
    return PaymentsToReconcileView.cast(super.getView());
}


public ViewId getBanksStatementReconciliationViewId(){
    return getView().getBankStatementViewId();
}


}