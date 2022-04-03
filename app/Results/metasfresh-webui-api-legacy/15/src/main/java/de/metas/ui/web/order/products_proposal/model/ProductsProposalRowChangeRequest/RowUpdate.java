package de.metas.ui.web.order.products_proposal.model.ProductsProposalRowChangeRequest;
 import java.math.BigDecimal;
import java.util.Optional;
import de.metas.pricing.ProductPriceId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class RowUpdate implements ProductsProposalRowChangeRequest{

 private ProductProposalPrice price;

 private Integer lastShipmentDays;

 private ProductPriceId copiedFromProductPriceId;


}