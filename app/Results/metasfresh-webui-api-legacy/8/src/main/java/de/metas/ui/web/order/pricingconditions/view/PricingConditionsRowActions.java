package de.metas.ui.web.order.pricingconditions.view;
 import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import de.metas.money.CurrencyId;
import de.metas.payment.paymentterm.PaymentTermId;
import de.metas.pricing.PricingSystemId;
import de.metas.pricing.conditions.PriceSpecificationType;
import de.metas.pricing.conditions.PricingConditionsBreak;
import de.metas.ui.web.order.pricingconditions.view.PricingConditionsRowChangeRequest.PartialPriceChange;
import de.metas.ui.web.order.pricingconditions.view.PricingConditionsRowChangeRequest.PartialPriceChange.PartialPriceChangeBuilder;
import de.metas.ui.web.order.pricingconditions.view.PricingConditionsRowChangeRequest.PricingConditionsRowChangeRequestBuilder;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.util.lang.Percent;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
@UtilityClass
public class PricingConditionsRowActions {


public PricingConditionsRowChangeRequest saved(PricingConditionsBreak pricingConditionsBreak){
    return PricingConditionsRowChangeRequest.builder().pricingConditionsBreak(pricingConditionsBreak).build();
}


public PricingConditionsRowChangeRequest toChangeRequest(List<JSONDocumentChangedEvent> fieldChangeRequests,CurrencyId defaultCurrencyId){
    final PricingConditionsRowChangeRequestBuilder builder = PricingConditionsRowChangeRequest.builder().priceChange(toPartialPriceChange(fieldChangeRequests, defaultCurrencyId));
    for (final JSONDocumentChangedEvent fieldChangeRequest : fieldChangeRequests) {
        final String fieldName = fieldChangeRequest.getPath();
        if (PricingConditionsRow.FIELDNAME_Discount.equals(fieldName)) {
            builder.discount(Percent.of(fieldChangeRequest.getValueAsBigDecimal(BigDecimal.ZERO)));
        } else if (PricingConditionsRow.FIELDNAME_PaymentTerm.equals(fieldName)) {
            final LookupValue paymentTerm = fieldChangeRequest.getValueAsIntegerLookupValue();
            final PaymentTermId paymentTermId = paymentTerm != null ? PaymentTermId.ofRepoIdOrNull(paymentTerm.getIdAsInt()) : null;
            builder.paymentTermId(Optional.ofNullable(paymentTermId));
        } else if (PricingConditionsRow.FIELDNAME_PaymentDiscount.equals(fieldName)) {
            final BigDecimal valueAsBigDecimal = fieldChangeRequest.getValueAsBigDecimal(null);
            if (valueAsBigDecimal != null) {
                builder.paymentDiscount(Optional.of(Percent.of(valueAsBigDecimal)));
            } else {
                builder.paymentDiscount(Optional.empty());
            }
        }
    }
    return builder.build();
}


public PartialPriceChange toPartialPriceChange(List<JSONDocumentChangedEvent> fieldChangeRequests,CurrencyId defaultCurrencyId){
    final PartialPriceChangeBuilder builder = PartialPriceChange.builder().defaultCurrencyId(defaultCurrencyId);
    for (final JSONDocumentChangedEvent fieldChangeRequest : fieldChangeRequests) {
        final String fieldName = fieldChangeRequest.getPath();
        builder.changedFieldName(fieldName);
        if (PricingConditionsRow.FIELDNAME_BasePriceType.equals(fieldName)) {
            final LookupValue priceTypeLookupValue = fieldChangeRequest.getValueAsStringLookupValue();
            final PriceSpecificationType priceType = priceTypeLookupValue != null ? PriceSpecificationType.ofCode(priceTypeLookupValue.getIdAsString()) : null;
            builder.priceType(priceType);
        } else if (PricingConditionsRow.FIELDNAME_BasePricingSystem.equals(fieldName)) {
            final LookupValue pricingSystem = fieldChangeRequest.getValueAsIntegerLookupValue();
            final PricingSystemId pricingSystemId = pricingSystem != null ? PricingSystemId.ofRepoIdOrNull(pricingSystem.getIdAsInt()) : null;
            builder.basePricingSystemId(Optional.ofNullable(pricingSystemId));
        } else if (PricingConditionsRow.FIELDNAME_PricingSystemSurcharge.equals(fieldName)) {
            builder.pricingSystemSurchargeAmt(fieldChangeRequest.getValueAsBigDecimal(BigDecimal.ZERO));
        } else if (PricingConditionsRow.FIELDNAME_BasePrice.equals(fieldName)) {
            builder.fixedPriceAmt(fieldChangeRequest.getValueAsBigDecimal(BigDecimal.ZERO));
        } else if (PricingConditionsRow.FIELDNAME_C_Currency_ID.equals(fieldName)) {
            final LookupValue currency = fieldChangeRequest.getValueAsIntegerLookupValue();
            final CurrencyId currencyId = currency != null ? CurrencyId.ofRepoIdOrNull(currency.getIdAsInt()) : null;
            builder.currencyId(currencyId);
            if (currencyId != null) {
                builder.defaultCurrencyId(currencyId);
            }
        }
    }
    return builder.build();
}


}