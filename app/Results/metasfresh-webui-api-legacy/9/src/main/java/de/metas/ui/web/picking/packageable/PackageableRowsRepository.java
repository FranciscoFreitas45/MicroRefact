package de.metas.ui.web.picking.packageable;
 import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_M_Product;
import org.springframework.stereotype.Component;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.inoutcandidate.api.IPackagingDAO;
import de.metas.inoutcandidate.api.Packageable;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.order.OrderLineId;
import de.metas.quantity.Quantity;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.util.Services;
import lombok.NonNull;
@Component
public class PackageableRowsRepository {

 private  Supplier<LookupDataSource> orderLookup;

 private  Supplier<LookupDataSource> productLookup;

 private  Supplier<LookupDataSource> bpartnerLookup;


public PackageableRow createPackageableRow(ViewId viewId,Packageable packageable){
    final Quantity qtyPickedOrDelivered = packageable.getQtyPickedOrDelivered();
    final Optional<OrderLineId> orderLineId = Optional.ofNullable(packageable.getSalesOrderLineIdOrNull());
    return PackageableRow.builder().shipmentScheduleId(packageable.getShipmentScheduleId()).salesOrderLineId(orderLineId).viewId(viewId).order(orderLookup.get().findById(packageable.getSalesOrderId())).product(productLookup.get().findById(packageable.getProductId())).bpartner(bpartnerLookup.get().findById(packageable.getCustomerId())).preparationDate(packageable.getPreparationDate()).qtyOrdered(packageable.getQtyOrdered()).qtyPicked(qtyPickedOrDelivered).build();
}


public PackageableRowsData createRowsData(ViewId viewId,Set<ShipmentScheduleId> shipmentScheduleIds){
    if (shipmentScheduleIds.isEmpty()) {
        return PackageableRowsData.EMPTY;
    }
    final Set<ShipmentScheduleId> shipmentScheduleIdsCopy = ImmutableSet.copyOf(shipmentScheduleIds);
    return PackageableRowsData.ofSupplier(() -> retrieveRowsByShipmentScheduleIds(viewId, shipmentScheduleIdsCopy));
}


public List<PackageableRow> retrieveRowsByShipmentScheduleIds(ViewId viewId,Set<ShipmentScheduleId> shipmentScheduleIds){
    if (shipmentScheduleIds.isEmpty()) {
        return ImmutableList.of();
    }
    return Services.get(IPackagingDAO.class).getByShipmentScheduleIds(shipmentScheduleIds).stream().map(packageable -> createPackageableRow(viewId, packageable)).collect(ImmutableList.toImmutableList());
}


}