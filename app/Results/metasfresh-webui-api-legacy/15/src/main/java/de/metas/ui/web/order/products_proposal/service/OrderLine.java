package de.metas.ui.web.order.products_proposal.service;
 import java.math.BigDecimal;
import javax.annotation.Nullable;
import de.metas.handlingunits.HUPIItemProductId;
import de.metas.order.OrderLineId;
import de.metas.product.ProductId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class OrderLine {

@NonNull
 private OrderLineId orderLineId;

@NonNull
 private ProductId productId;

@Nullable
 private HUPIItemProductId packingMaterialId;

 private boolean packingMaterialWithInfiniteCapacity;

@NonNull
 private BigDecimal priceActual;

@NonNull
 private BigDecimal qtyEnteredCU;

 private int qtyEnteredTU;

 private String description;


public boolean isMatching(ProductId productId,HUPIItemProductId packingMaterialId){
    return ProductId.equals(this.productId, productId) && HUPIItemProductId.equals(HUPIItemProductId.nullToVirtual(this.packingMaterialId), HUPIItemProductId.nullToVirtual(packingMaterialId));
}


}