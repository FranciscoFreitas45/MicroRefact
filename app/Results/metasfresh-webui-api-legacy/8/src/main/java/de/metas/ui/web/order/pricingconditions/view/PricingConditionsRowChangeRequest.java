package de.metas.ui.web.order.pricingconditions.view;
 import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;
import de.metas.money.CurrencyId;
import de.metas.payment.paymentterm.PaymentTermId;
import de.metas.pricing.PricingSystemId;
import de.metas.pricing.conditions.PriceSpecification;
import de.metas.pricing.conditions.PriceSpecificationType;
import de.metas.pricing.conditions.PricingConditionsBreak;
import de.metas.pricing.conditions.PricingConditionsBreakId;
import de.metas.util.lang.Percent;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
@Value
@Builder
public class PricingConditionsRowChangeRequest {

 private PricingConditionsBreak pricingConditionsBreak;

 private PricingConditionsBreakId sourcePricingConditionsBreakId;

 private Percent discount;

@Nullable
 private Optional<PaymentTermId> paymentTermId;

@Nullable
 private Optional<Percent> paymentDiscount;

 private PriceChange priceChange;

@Singular
 private Set<String> changedFieldNames;

 private PriceSpecificationType priceType;

 private Optional<PricingSystemId> basePricingSystemId;

 private BigDecimal pricingSystemSurchargeAmt;

 private BigDecimal fixedPriceAmt;

 private CurrencyId currencyId;

 private CurrencyId defaultCurrencyId;

@NonNull
 private PriceSpecification price;


}