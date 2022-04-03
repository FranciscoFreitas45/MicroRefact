package de.metas.ui.web.order.pricingconditions.process;
 import java.util.Optional;
import org.adempiere.exceptions.AdempiereException;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.ITranslatableString;
import de.metas.pricing.conditions.PricingConditionsBreak;
import de.metas.pricing.conditions.PricingConditionsBreakId;
import de.metas.pricing.conditions.PricingConditionsId;
import de.metas.pricing.conditions.service.IPricingConditionsRepository;
import de.metas.pricing.conditions.service.PricingConditionsBreakChangeRequest;
import de.metas.pricing.conditions.service.PricingConditionsBreakChangeRequest.PricingConditionsBreakChangeRequestBuilder;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.order.pricingconditions.view.PricingConditionsRow;
import de.metas.ui.web.order.pricingconditions.view.PricingConditionsRowActions;
import de.metas.util.Services;
import lombok.NonNull;
public class PricingConditionsView_SaveEditableRow extends PricingConditionsViewBasedProcess{

 private  AdMessageKey MSG_BPARTNER_HAS_NO_PRICING_CONDITIONS;

 private  IPricingConditionsRepository pricingConditionsRepo;


public PricingConditionsBreakChangeRequestBuilder preparePricingConditionsBreakChangeRequest(PricingConditionsBreak pricingConditionsBreak){
    return PricingConditionsBreakChangeRequest.builder().pricingConditionsBreakId(pricingConditionsBreak.getId()).matchCriteria(pricingConditionsBreak.getMatchCriteria()).price(pricingConditionsBreak.getPriceSpecification()).discount(pricingConditionsBreak.getDiscount()).paymentTermId(Optional.ofNullable(pricingConditionsBreak.getPaymentTermIdOrNull())).paymentDiscount(Optional.ofNullable(pricingConditionsBreak.getPaymentDiscountOverrideOrNull()));
}


@Override
public String doIt(){
    final PricingConditionsBreak pricingConditionsBreak = pricingConditionsRepo.changePricingConditionsBreak(createPricingConditionsBreakChangeRequest(getEditableRow()));
    patchEditableRow(PricingConditionsRowActions.saved(pricingConditionsBreak));
    return MSG_OK;
}


public PricingConditionsBreakChangeRequest createPricingConditionsBreakChangeRequest(PricingConditionsRow row){
    if (!row.isEditable()) {
        throw new AdempiereException("Saving not editable rows is not allowed").setParameter("row", row);
    }
    final PricingConditionsId pricingConditionsId = row.getPricingConditionsId();
    final PricingConditionsBreak pricingConditionsBreak = row.getPricingConditionsBreak();
    final PricingConditionsBreakId updateFromPricingConditionsBreakId = row.getCopiedFromPricingConditionsBreakId();
    return preparePricingConditionsBreakChangeRequest(pricingConditionsBreak).pricingConditionsId(pricingConditionsId).updateFromPricingConditionsBreakId(updateFromPricingConditionsBreakId).build();
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
    if (!row.isEditable()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not the editable row");
    }
    if (row.getPricingConditionsId() == null) {
        final ITranslatableString msg = msgBL.getTranslatableMsgText(MSG_BPARTNER_HAS_NO_PRICING_CONDITIONS);
        return ProcessPreconditionsResolution.reject(msg);
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    invalidateView();
}


}