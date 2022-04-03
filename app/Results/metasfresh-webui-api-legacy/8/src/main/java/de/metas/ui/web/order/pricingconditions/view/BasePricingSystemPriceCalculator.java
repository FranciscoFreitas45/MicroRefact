package de.metas.ui.web.order.pricingconditions.view;
 import de.metas.money.Money;
@FunctionalInterface
public interface BasePricingSystemPriceCalculator {


public Money calculate(BasePricingSystemPriceCalculatorRequest request)
;

}