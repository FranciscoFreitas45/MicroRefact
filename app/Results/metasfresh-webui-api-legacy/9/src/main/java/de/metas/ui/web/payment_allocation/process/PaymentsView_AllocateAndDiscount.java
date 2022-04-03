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
public class PaymentsView_AllocateAndDiscount extends PaymentsView_Allocate_Templateimplements IProcessPrecondition{


@Override
public void customizePaymentsViewAllocateCommandBuilder(PaymentsViewAllocateCommandBuilder builder){
    builder.payableRemainingOpenAmtPolicy(PayableRemainingOpenAmtPolicy.DISCOUNT);
}


public ITranslatableString computeCaption(PaymentAllocationResult result){
    return TranslatableStrings.builder().appendADElement("DiscountAmt").append(": ").append(result.getTotalDiscountAmt(), DisplayType.Amount).build();
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
    if (result.getTotalDiscountAmt().signum() == 0) {
        // NOTE: there is other process is would allocate without writing off
        return ProcessPreconditionsResolution.rejectWithInternalReason("nothing to discount");
    }
    return ProcessPreconditionsResolution.accept().deriveWithCaptionOverride(computeCaption(result));
}


}