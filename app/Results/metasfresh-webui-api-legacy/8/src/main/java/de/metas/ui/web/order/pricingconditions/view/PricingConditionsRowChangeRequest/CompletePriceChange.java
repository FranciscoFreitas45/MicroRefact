package de.metas.ui.web.order.pricingconditions.view.PricingConditionsRowChangeRequest;
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
@lombok.Value(staticConstructor = "of")
public class CompletePriceChange implements PriceChange{

@NonNull
 private PriceSpecification price;


}