package de.metas.ui.web.order.products_proposal.service;
 import java.time.ZonedDateTime;
import java.util.Optional;
import javax.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import de.metas.bpartner.BPartnerId;
import de.metas.handlingunits.HUPIItemProductId;
import de.metas.lang.SOTrx;
import de.metas.location.CountryId;
import de.metas.money.CurrencyId;
import de.metas.order.OrderId;
import de.metas.pricing.PriceListId;
import de.metas.pricing.PriceListVersionId;
import de.metas.pricing.PricingSystemId;
import de.metas.product.ProductId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class Order {

@NonNull
 private OrderId orderId;

@NonNull
 private SOTrx soTrx;

@NonNull
 private ZonedDateTime datePromised;

@NonNull
 private BPartnerId bpartnerId;

 private String bpartnerName;

@NonNull
 private PricingSystemId pricingSystemId;

@NonNull
 private PriceListId priceListId;

@NonNull
 private PriceListVersionId priceListVersionId;

@Nullable
 private CountryId countryId;

@NonNull
 private CurrencyId currencyId;

@NonNull
 private ImmutableList<OrderLine> lines;


public Optional<OrderLine> getFirstMatchingOrderLine(ProductId productId,HUPIItemProductId packingMaterialId){
    return getLines().stream().filter(line -> line.isMatching(productId, packingMaterialId)).findFirst();
}


}