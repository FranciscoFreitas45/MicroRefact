package de.metas.ui.web.order.products_proposal.campaign_price;
 import de.metas.ui.web.order.products_proposal.campaign_price.StandardCampaignPriceProvider.StandardCampaignPriceProviderBuilder;
import lombok.experimental.UtilityClass;
@UtilityClass
public class CampaignPriceProviders {


public StandardCampaignPriceProviderBuilder standard(){
    return StandardCampaignPriceProvider.builder();
}


public CampaignPriceProvider none(){
    return NullCampaignPriceProvider.instance;
}


}