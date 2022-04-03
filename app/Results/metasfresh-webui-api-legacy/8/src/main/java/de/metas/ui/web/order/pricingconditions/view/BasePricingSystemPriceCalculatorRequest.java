package de.metas.ui.web.order.pricingconditions.view;
 import de.metas.bpartner.BPartnerId;
import de.metas.pricing.conditions.PricingConditionsBreak;
@lombok.Value
@lombok.Builder
public class BasePricingSystemPriceCalculatorRequest {

@lombok.NonNull
 private PricingConditionsBreak pricingConditionsBreak;

@lombok.NonNull
 private BPartnerId bpartnerId;

 private boolean isSOTrx;


}