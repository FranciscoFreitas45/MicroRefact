package de.metas.ui.web.shipment_candidates_editor;
 import java.util.Set;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;
import org.compiere.model.I_M_Warehouse;
import de.metas.inoutcandidate.api.IShipmentScheduleBL;
import de.metas.inoutcandidate.api.IShipmentScheduleEffectiveBL;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
public class ShipmentCandidateRowsRepository {

 private  IShipmentScheduleBL shipmentScheduleBL;

 private  IShipmentScheduleEffectiveBL shipmentScheduleEffectiveBL;

 private  LookupDataSource salesOrdersLookup;

 private  LookupDataSource customersLookup;

 private  LookupDataSource warehousesLookup;

 private  LookupDataSource productsLookup;

 private  LookupDataSource asiLookup;

 private  LookupDataSource catchUOMsLookup;


public ShipmentCandidateRows getByShipmentScheduleIds(Set<ShipmentScheduleId> shipmentScheduleIds){
    return ShipmentCandidateRowsLoader.builder().shipmentScheduleBL(shipmentScheduleBL).shipmentScheduleEffectiveBL(shipmentScheduleEffectiveBL).salesOrdersLookup(salesOrdersLookup).customersLookup(customersLookup).warehousesLookup(warehousesLookup).productsLookup(productsLookup).asiLookup(asiLookup).catchUOMsLookup(catchUOMsLookup).shipmentScheduleIds(shipmentScheduleIds).load();
}


}