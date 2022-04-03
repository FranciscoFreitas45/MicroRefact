package de.metas.ui.web.order.products_proposal.model.ProductsProposalRowChangeRequest;
 import java.math.BigDecimal;
import java.util.Optional;
import de.metas.pricing.ProductPriceId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Builder
@Value
public class RowSaved implements ProductsProposalRowChangeRequest{

@NonNull
 private ProductPriceId productPriceId;

@NonNull
 private ProductProposalPrice price;


}