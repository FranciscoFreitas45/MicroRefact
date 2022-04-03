package de.metas.ui.web.pickingV2.productsToPick.rows.factory;
 import java.util.Optional;
import javax.annotation.Nullable;
import org.adempiere.mm.attributes.AttributeSetInstanceId;
import org.adempiere.warehouse.WarehouseId;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.ShipmentAllocationBestBeforePolicy;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.material.planning.pporder.PPOrderBOMLineId;
import de.metas.material.planning.pporder.PPOrderId;
import de.metas.order.OrderLineId;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.shipping.ShipperId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@Getter
@ToString
public class AllocablePackageable {

 private  BPartnerId customerId;

 private  ProductId productId;

 private  AttributeSetInstanceId asiId;

 private  ShipmentScheduleId shipmentScheduleId;

 private  Optional<ShipmentAllocationBestBeforePolicy> bestBeforePolicy;

 private  WarehouseId warehouseId;

 private  OrderLineId salesOrderLineIdOrNull;

 private  ShipperId shipperId;

 private  PPOrderId pickFromOrderId;

 private  PPOrderBOMLineId issueToOrderBOMLineId;

 private  Quantity qtyToAllocateTarget;

 private  Quantity qtyToAllocate;


public boolean isAllocated(){
    return getQtyToAllocate().signum() == 0;
}


public void allocateQty(Quantity qty){
    qtyToAllocate = qtyToAllocate.subtract(qty);
}


}