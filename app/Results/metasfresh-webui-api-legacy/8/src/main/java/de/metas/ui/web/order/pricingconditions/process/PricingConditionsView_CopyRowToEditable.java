package de.metas.ui.web.order.pricingconditions.process;
 import java.util.Optional;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.service.IBPartnerDAO;
import de.metas.lang.SOTrx;
import de.metas.payment.paymentterm.PaymentTermId;
import de.metas.pricing.PricingSystemId;
import de.metas.pricing.conditions.PriceSpecification;
import de.metas.pricing.conditions.PricingConditionsBreak;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.order.pricingconditions.view.PricingConditionsRow;
import de.metas.ui.web.order.pricingconditions.view.PricingConditionsRowChangeRequest;
import de.metas.ui.web.order.pricingconditions.view.PricingConditionsRowChangeRequest.CompletePriceChange;
import de.metas.util.Services;
import de.metas.util.lang.Percent;
import lombok.NonNull;
public class PricingConditionsView_CopyRowToEditable extends PricingConditionsViewBasedProcess{

 private  IBPartnerDAO bpartnersRepo;


public PricingConditionsRowChangeRequest createChangeRequest(PricingConditionsRow templateRow){
    final PricingConditionsBreak templatePricingConditionsBreak = templateRow.getPricingConditionsBreak();
    PriceSpecification price = templatePricingConditionsBreak.getPriceSpecification();
    if (price.isNoPrice()) {
        // In case row does not have a price then use BPartner's pricing system
        final BPartnerId bpartnerId = templateRow.getBpartnerId();
        final SOTrx soTrx = SOTrx.ofBoolean(templateRow.isCustomer());
        price = createBasePricingSystemPrice(bpartnerId, soTrx);
    }
    final Percent discount = templatePricingConditionsBreak.getDiscount();
    final PaymentTermId paymentTermIdOrNull = templatePricingConditionsBreak.getPaymentTermIdOrNull();
    final Percent paymentDiscountOverrideOrNull = templatePricingConditionsBreak.getPaymentDiscountOverrideOrNull();
    return PricingConditionsRowChangeRequest.builder().priceChange(CompletePriceChange.of(price)).discount(discount).paymentTermId(Optional.ofNullable(paymentTermIdOrNull)).paymentDiscount(Optional.ofNullable(paymentDiscountOverrideOrNull)).sourcePricingConditionsBreakId(templatePricingConditionsBreak.getId()).build();
}


@Override
public String doIt(){
    patchEditableRow(createChangeRequest(getSingleSelectedRow()));
    return MSG_OK;
}


public PriceSpecification createBasePricingSystemPrice(BPartnerId bpartnerId,SOTrx soTrx){
    final PricingSystemId pricingSystemId = bpartnersRepo.retrievePricingSystemIdOrNull(bpartnerId, soTrx);
    if (pricingSystemId == null) {
        return PriceSpecification.none();
    }
    return PriceSpecification.basePricingSystem(pricingSystemId);
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!getView().hasEditableRow()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("view does not have an editable row");
    }
    if (!isSingleSelectedRow()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not a single selected row");
    }
    final PricingConditionsRow row = getSingleSelectedRow();
    if (row.isEditable()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select other row than editable row");
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    invalidateView();
}


}