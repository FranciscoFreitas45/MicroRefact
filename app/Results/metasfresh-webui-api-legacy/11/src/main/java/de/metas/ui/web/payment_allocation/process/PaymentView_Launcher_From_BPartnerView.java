package de.metas.ui.web.payment_allocation.process;
 import com.google.common.collect.ImmutableSet;
import de.metas.banking.payment.paymentallocation.PaymentAllocationRepository;
import de.metas.banking.payment.paymentallocation.PaymentToAllocateQuery;
import de.metas.bpartner.BPartnerId;
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
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.time.SystemTime;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.SpringContextHolder;
import java.util.Optional;
public class PaymentView_Launcher_From_BPartnerView extends ViewBasedProcessTemplateimplements IProcessPrecondition{

 private  PaymentAllocationRepository paymentAllocationRepo;

 private  IViewsRepository viewsFactory;


public Optional<BPartnerId> getSingleSelectedBPartnerId(){
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    return selectedRowIds.isSingleDocumentId() ? Optional.of(selectedRowIds.getSingleDocumentId().toId(BPartnerId::ofRepoId)) : Optional.empty();
}


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


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!getSingleSelectedBPartnerId().isPresent()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not a single selected BPartner");
    }
    return ProcessPreconditionsResolution.accept();
}


public ImmutableSet<PaymentId> retrievePaymentIds(){
    return paymentAllocationRepo.retrievePaymentIdsToAllocate(PaymentToAllocateQuery.builder().evaluationDate(SystemTime.asZonedDateTime()).bpartnerId(getSingleSelectedBPartnerId().get()).build());
}


}