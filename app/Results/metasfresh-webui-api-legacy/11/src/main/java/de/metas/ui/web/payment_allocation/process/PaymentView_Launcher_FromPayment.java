package de.metas.ui.web.payment_allocation.process;
 import org.adempiere.exceptions.AdempiereException;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableSet;
import de.metas.payment.PaymentId;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.payment_allocation.PaymentsViewFactory;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
public class PaymentView_Launcher_FromPayment extends ViewBasedProcessTemplateimplements IProcessPrecondition{

@Autowired
 private  IViewsRepository viewsFactory;


@Override
public String doIt(){
    final ImmutableSet<PaymentId> paymentIds = getSelectedPaymentIds();
    if (paymentIds.isEmpty()) {
        throw new AdempiereException("@NoSelection@");
    }
    final ViewId viewId = viewsFactory.createView(CreateViewRequest.builder(PaymentsViewFactory.WINDOW_ID).setFilterOnlyIds(PaymentId.toIntSet(paymentIds)).build()).getViewId();
    getResult().setWebuiViewToOpen(WebuiViewToOpen.builder().viewId(viewId.getViewId()).target(ViewOpenTarget.ModalOverlay).build());
    return MSG_OK;
}


public ImmutableSet<PaymentId> getSelectedPaymentIds(){
    return getSelectedRowIds().stream().map(rowId -> PaymentId.ofRepoId(rowId.toInt())).collect(ImmutableSet.toImmutableSet());
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final ImmutableSet<PaymentId> paymentIds = getSelectedPaymentIds();
    if (paymentIds.isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection().toInternal();
    }
    return ProcessPreconditionsResolution.accept();
}


}