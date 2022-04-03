package de.metas.ui.web.order.products_proposal.campaign_price;
 import java.time.LocalDate;
import java.util.Optional;
import de.metas.bpartner.BPGroupId;
import de.metas.bpartner.BPartnerId;
import de.metas.location.CountryId;
import de.metas.money.CurrencyId;
import de.metas.pricing.PricingSystemId;
import de.metas.pricing.rules.campaign_price.CampaignPrice;
import de.metas.pricing.rules.campaign_price.CampaignPriceQuery;
import de.metas.pricing.rules.campaign_price.CampaignPriceService;
import de.metas.product.ProductId;
import de.metas.ui.web.order.products_proposal.model.ProductProposalCampaignPrice;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
@ToString(exclude = { "campaignPriceService" })
public class StandardCampaignPriceProvider implements CampaignPriceProvider{

 private  CampaignPriceService campaignPriceService;

 private  BPartnerId bpartnerId;

 private  BPGroupId bpGroupId;

 private  PricingSystemId pricingSystemId;

 private  CountryId countryId;

 private  CurrencyId currencyId;

 private  LocalDate date;


public ProductProposalCampaignPrice toProductProposalCampaignPrice(CampaignPrice campaignPrice){
    return ProductProposalCampaignPrice.builder().amount(campaignPriceService.getPriceStdAsAmount(campaignPrice)).applyOnlyIfLessThanStandardPrice(true).build();
}


public CampaignPriceQuery createQuery(ProductId productId){
    return CampaignPriceQuery.builder().bpartnerId(bpartnerId).bpGroupId(bpGroupId).pricingSystemId(pricingSystemId).productId(productId).countryId(countryId).currencyId(currencyId).date(date).build();
}


@Override
public Optional<ProductProposalCampaignPrice> getCampaignPrice(ProductId productId){
    final CampaignPriceQuery query = createQuery(productId);
    return campaignPriceService.getCampaignPrice(query).map(this::toProductProposalCampaignPrice);
}


}