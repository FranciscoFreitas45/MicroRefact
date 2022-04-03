package de.metas.ui.web.order.products_proposal.model;
 import javax.annotation.Nullable;
import de.metas.currency.Amount;
import de.metas.handlingunits.HUPIItemProductId;
import de.metas.i18n.ITranslatableString;
import de.metas.pricing.ProductPriceId;
import de.metas.product.ProductId;
import de.metas.ui.web.window.datatypes.LookupValue;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class ProductsProposalRowAddRequest {

@NonNull
 private LookupValue product;

@NonNull
@Default
 private ProductASIDescription asiDescription;

@NonNull
 private Amount priceListPrice;

@Nullable
 private Integer lastShipmentDays;

@Nullable
 private ProductPriceId copiedFromProductPriceId;

@Nullable
 private HUPIItemProductId packingMaterialId;

@Nullable
 private ITranslatableString packingDescription;


public ProductId getProductId(){
    return getProduct().getIdAs(ProductId::ofRepoId);
}


}