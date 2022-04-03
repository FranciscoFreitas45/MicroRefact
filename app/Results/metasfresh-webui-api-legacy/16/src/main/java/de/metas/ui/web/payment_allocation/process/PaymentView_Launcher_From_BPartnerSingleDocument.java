package de.metas.ui.web.payment_allocation.process;
 import org.adempiere.exceptions.AdempiereException;
import org.compiere.SpringContextHolder;
import com.google.common.collect.ImmutableSet;
import de.metas.banking.payment.paymentallocation.PaymentAllocationRepository;
import de.metas.banking.payment.paymentallocation.PaymentToAllocateQuery;
import de.metas.bpartner.BPartnerId;
import de.metas.payment.PaymentId;
import de.metas.process.JavaProcess;
import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.ui.web.payment_allocation.PaymentsViewFactory;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.util.time.SystemTime;
public class PaymentView_Launcher_From_BPartnerSingleDocument extends JavaProcess{

 private  PaymentAllocationRepository paymentAllocationRepo;

 private  IViewsRepository viewsFactory;


@Override
public String doIt(){
    final ImmutableSet<PaymentId> paymentIds = retrievePaymentIds();
    if (paymentIds.isEmpty()) {
        throw new AdempiereException("@NoOpenPayments@").markAsUserValidationError();
    }
    final ViewId viewId = viewsFactory.createView(CreateViewRequest.builder(PaymentsViewFactory.WINDOW_ID).setFilterOnlyIds(PaymentId.toIntSet(paymentIds)).build()).getViewId();
    getResult().setWebuiViewToOpen(WebuiViewToOpen.builder().viewId(viewId.getViewId()).target(ViewOpenTarget.ModalOverlay).build());
    return MSG_OK;
}


public ImmutableSet<PaymentId> retrievePaymentIds(){
    return paymentAllocationRepo.retrievePaymentIdsToAllocate(PaymentToAllocateQuery.builder().evaluationDate(SystemTime.asZonedDateTime()).bpartnerId(BPartnerId.ofRepoId(getRecord_ID())).build());
}


}