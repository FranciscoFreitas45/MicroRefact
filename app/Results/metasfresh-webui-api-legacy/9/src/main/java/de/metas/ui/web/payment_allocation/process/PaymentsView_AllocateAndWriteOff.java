package de.metas.ui.web.payment_allocation.process;
 import org.compiere.util.DisplayType;
import de.metas.banking.payment.paymentallocation.service.PaymentAllocationBuilder.PayableRemainingOpenAmtPolicy;
import de.metas.banking.payment.paymentallocation.service.PaymentAllocationResult;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.payment_allocation.process.PaymentsViewAllocateCommand.PaymentsViewAllocateCommandBuilder;
import lombok.NonNull;
public class PaymentsView_AllocateAndWriteOff extends PaymentsView_Allocate_Templateimplements IProcessPrecondition{


@Override
public void customizePaymentsViewAllocateCommandBuilder(PaymentsViewAllocateCommandBuilder builder){
    builder.payableRemainingOpenAmtPolicy(PayableRemainingOpenAmtPolicy.WRITE_OFF);
}


public ITranslatableString computeCaption(PaymentAllocationResult result){
    return TranslatableStrings.builder().appendADElement("WriteOffAmt").append(": ").append(result.getTotalWriteOffAmt(), DisplayType.Amount).build();
}


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
    if (result.getTotalWriteOffAmt().signum() == 0) {
        // NOTE: there is other process is would allocate without writing off
        return ProcessPreconditionsResolution.rejectWithInternalReason("nothing to write-off");
    }
    return ProcessPreconditionsResolution.accept().deriveWithCaptionOverride(computeCaption(result));
}


}