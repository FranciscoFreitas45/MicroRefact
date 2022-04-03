package de.metas.ui.web.payment_allocation.process;
 import de.metas.banking.payment.paymentallocation.service.PaymentAllocationResult;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
public class PaymentsView_Allocate extends PaymentsView_Allocate_Templateimplements IProcessPrecondition{


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final PaymentAllocationResult result = newPaymentsViewAllocateCommand().dryRun().orElse(null);
    if (result == null) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("invalid");
    }
    if (result.getCandidates().isEmpty()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("nothing to allocate");
    }
    if (!result.isOK()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not a valid selection");
    }
    return ProcessPreconditionsResolution.accept();
}


}