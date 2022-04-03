package de.metas.ui.web.order.products_proposal.model;
 import java.math.BigDecimal;
import java.util.Optional;
import de.metas.pricing.ProductPriceId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
public interface ProductsProposalRowChangeRequest {

 private Optional<BigDecimal> qty;

 private Optional<BigDecimal> price;

 private Optional<String> description;

 private ProductProposalPrice price;

 private Integer lastShipmentDays;

 private ProductPriceId copiedFromProductPriceId;

@NonNull
 private ProductPriceId productPriceId;

@NonNull
 private ProductProposalPrice price;


}