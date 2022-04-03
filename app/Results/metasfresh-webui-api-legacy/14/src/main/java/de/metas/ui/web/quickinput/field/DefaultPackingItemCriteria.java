package de.metas.ui.web.quickinput.field;
 import de.metas.adempiere.model.I_C_Invoice;
import de.metas.adempiere.model.I_C_Order;
import de.metas.bpartner.BPartnerLocationId;
import de.metas.lang.SOTrx;
import de.metas.pricing.PriceListId;
import de.metas.pricing.PricingSystemId;
import de.metas.product.ProductId;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.compiere.util.TimeUtil;
import javax.annotation.Nullable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
@Data
@Builder
public class DefaultPackingItemCriteria {

@NonNull
 private  ProductId productId;

@NonNull
 private  BPartnerLocationId bPartnerLocationId;

@Nullable
 private  PricingSystemId pricingSystemId;

@Nullable
 private  PriceListId priceListId;

@NonNull
 private  ZonedDateTime date;

@Nullable
 private  SOTrx soTrx;


public Optional<DefaultPackingItemCriteria> of(I_C_Invoice invoice,ProductId productId){
    final BPartnerLocationId bpartnerLocationId = BPartnerLocationId.ofRepoIdOrNull(invoice.getC_BPartner_ID(), invoice.getC_BPartner_Location_ID());
    final PriceListId priceListId = PriceListId.ofRepoIdOrNull(invoice.getM_PriceList_ID());
    final ZonedDateTime date = TimeUtil.asZonedDateTime(invoice.getDateInvoiced());
    final boolean anyNull = Stream.of(bpartnerLocationId, priceListId, date, productId).anyMatch(Objects::isNull);
    if (anyNull) {
        return Optional.empty();
    }
    return Optional.of(builder().productId(productId).priceListId(priceListId).date(date).bPartnerLocationId(bpartnerLocationId).build());
}


}