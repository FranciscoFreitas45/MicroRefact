package de.metas.ui.web.order.products_proposal.campaign_price;
 import java.util.Optional;
import de.metas.product.ProductId;
import de.metas.ui.web.order.products_proposal.model.ProductProposalCampaignPrice;
public interface CampaignPriceProvider {


public Optional<ProductProposalCampaignPrice> getCampaignPrice(ProductId productId)
;

}