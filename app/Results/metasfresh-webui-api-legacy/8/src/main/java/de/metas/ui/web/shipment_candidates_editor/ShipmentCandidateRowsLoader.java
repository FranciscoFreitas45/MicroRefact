package de.metas.ui.web.shipment_candidates_editor;
 import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import org.adempiere.mm.attributes.AttributeSetInstanceId;
import org.adempiere.warehouse.WarehouseId;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.bpartner.BPartnerId;
import de.metas.handlingunits.model.I_M_ShipmentSchedule;
import de.metas.inoutcandidate.api.IShipmentScheduleBL;
import de.metas.inoutcandidate.api.IShipmentScheduleEffectiveBL;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.interfaces.I_C_OrderLine;
import de.metas.order.IOrderDAO;
import de.metas.order.OrderAndLineId;
import de.metas.order.OrderId;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.uom.UomId;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
public class ShipmentCandidateRowsLoader {

 private  IShipmentScheduleBL shipmentScheduleBL;

 private  IShipmentScheduleEffectiveBL shipmentScheduleEffectiveBL;

 private  IOrderDAO ordersRepo;

 private  LookupDataSource salesOrdersLookup;

 private  LookupDataSource customersLookup;

 private  LookupDataSource warehousesLookup;

 private  LookupDataSource productsLookup;

 private  LookupDataSource asiLookup;

 private  LookupDataSource catchUOMsLookup;

 private  ImmutableSet<ShipmentScheduleId> shipmentScheduleIds;

 private  Map<OrderAndLineId,I_C_OrderLine> salesOrderLines;


public ShipmentCandidateRow toShipmentCandidateRow(I_M_ShipmentSchedule record){
    final AttributeSetInstanceId asiId = AttributeSetInstanceId.ofRepoIdOrNone(record.getM_AttributeSetInstance_ID());
    final BigDecimal qtyOrdered = shipmentScheduleEffectiveBL.computeQtyOrdered(record);
    final boolean catchWeight = shipmentScheduleBL.isCatchWeight(record);
    final BigDecimal qtyToDeliverCatchOverride = extractQtyToDeliverCatchOverride(record);
    final PackingInfo packingInfo = extractPackingInfo(record);
    final Quantity qtyCUsToDeliver = extractQtyCUToDeliver(record);
    final BigDecimal qtyToDeliverUserEntered = packingInfo.computeQtyUserEnteredByQtyCUs(qtyCUsToDeliver);
    return ShipmentCandidateRow.builder().shipmentScheduleId(ShipmentScheduleId.ofRepoId(record.getM_ShipmentSchedule_ID())).salesOrder(extractSalesOrder(record)).salesOrderLineNo(extractSalesOrderLineNo(record)).customer(extractCustomer(record)).warehouse(extractWarehouse(record)).product(extractProduct(record)).packingInfo(packingInfo).preparationDate(extractPreparationTime(record)).qtyOrdered(packingInfo.computeQtyUserEnteredByQtyCUs(qtyOrdered)).qtyToDeliverUserEnteredInitial(qtyToDeliverUserEntered).qtyToDeliverUserEntered(qtyToDeliverUserEntered).catchWeight(catchWeight).qtyToDeliverCatchOverrideInitial(qtyToDeliverCatchOverride).qtyToDeliverCatchOverride(qtyToDeliverCatchOverride).catchUOM(extractCatchUOM(record)).asiIdInitial(asiId).asi(toLookupValue(asiId)).processed(record.isProcessed()).build();
}


public LookupValue extractWarehouse(I_M_ShipmentSchedule record){
    final WarehouseId warehouseId = shipmentScheduleBL.getWarehouseId(record);
    return warehousesLookup.findById(warehouseId);
}


public int extractSalesOrderLineNo(I_M_ShipmentSchedule record){
    final OrderAndLineId salesOrderAndLineId = extractSalesOrderAndLineId(record);
    if (salesOrderAndLineId == null) {
        return 0;
    }
    final I_C_OrderLine salesOrderLine = salesOrderLines.get(salesOrderAndLineId);
    if (salesOrderLine == null) {
        return 0;
    }
    return salesOrderLine.getLine();
}


public LookupValue extractSalesOrder(I_M_ShipmentSchedule record){
    final OrderId salesOrderId = OrderId.ofRepoIdOrNull(record.getC_Order_ID());
    return salesOrderId != null ? salesOrdersLookup.findById(salesOrderId) : null;
}


public LookupValue extractCatchUOM(I_M_ShipmentSchedule record){
    final UomId catchUomId = UomId.ofRepoIdOrNull(record.getCatch_UOM_ID());
    return catchUomId != null ? catchUOMsLookup.findById(catchUomId) : null;
}


public Quantity extractQtyCUToDeliver(I_M_ShipmentSchedule record){
    return shipmentScheduleBL.getQtyToDeliver(record);
}


public LookupValue extractCustomer(I_M_ShipmentSchedule record){
    final BPartnerId customerId = shipmentScheduleBL.getBPartnerId(record);
    return customersLookup.findById(customerId);
}


public LookupValue toLookupValue(AttributeSetInstanceId asiId){
    return asiId.isRegular() ? asiLookup.findById(asiId) : IntegerLookupValue.of(asiId.getRepoId(), "");
}


public BigDecimal extractQtyToDeliverCatchOverride(I_M_ShipmentSchedule record){
    return shipmentScheduleBL.getCatchQtyOverride(record).map(qty -> qty.toBigDecimal()).orElse(null);
}


public ShipmentCandidateRows load(){
    final Collection<I_M_ShipmentSchedule> records = shipmentScheduleBL.getByIdsOutOfTrx(shipmentScheduleIds, I_M_ShipmentSchedule.class).values();
    final ImmutableSet<OrderAndLineId> salesOrderAndLineIds = extractSalesOrderAndLineId(records);
    this.salesOrderLines = ordersRepo.getOrderLinesByIds(salesOrderAndLineIds);
    final ImmutableList<ShipmentCandidateRow> rows = records.stream().map(this::toShipmentCandidateRow).sorted(Comparator.comparing(ShipmentCandidateRow::getSalesOrderDisplayNameOrEmpty).thenComparing(ShipmentCandidateRow::getSalesOrderLineNo)).collect(ImmutableList.toImmutableList());
    return ShipmentCandidateRows.builder().rows(rows).build();
}


public PackingInfo extractPackingInfo(I_M_ShipmentSchedule record){
    final BigDecimal qtyCUsPerTU = record.getQtyItemCapacity();
    if (qtyCUsPerTU == null || qtyCUsPerTU.signum() <= 0) {
        return PackingInfo.NONE;
    } else {
        return PackingInfo.builder().qtyCUsPerTU(qtyCUsPerTU).description(record.getPackDescription()).build();
    }
}


public LookupValue extractProduct(I_M_ShipmentSchedule record){
    final ProductId productId = ProductId.ofRepoId(record.getM_Product_ID());
    return productsLookup.findById(productId);
}


@Nullable
public OrderAndLineId extractSalesOrderAndLineId(I_M_ShipmentSchedule record){
    return OrderAndLineId.ofRepoIdsOrNull(record.getC_Order_ID(), record.getC_OrderLine_ID());
}


public ZonedDateTime extractPreparationTime(I_M_ShipmentSchedule record){
    return shipmentScheduleBL.getPreparationDate(record);
}


}