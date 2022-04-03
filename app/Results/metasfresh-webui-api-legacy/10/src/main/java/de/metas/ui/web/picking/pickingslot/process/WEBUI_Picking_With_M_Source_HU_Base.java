package de.metas.ui.web.picking.pickingslot.process;
 import java.util.List;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_ShipmentSchedule;
import de.metas.handlingunits.picking.IHUPickingSlotBL;
import de.metas.handlingunits.picking.IHUPickingSlotBL.PickingHUsQuery;
import de.metas.inoutcandidate.api.IPackagingDAO;
import de.metas.inoutcandidate.api.IShipmentScheduleBL;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.quantity.Quantity;
import de.metas.util.Services;
public class WEBUI_Picking_With_M_Source_HU_Base extends PickingSlotViewBasedProcess{

 private  IHUPickingSlotBL huPickingSlotBL;


public boolean checkSourceHuPrecondition(){
    final List<I_M_HU> sourceHUs = retrieveAvailableSourceHUs();
    return !sourceHUs.isEmpty();
}


public Quantity retrieveQtyToPick(){
    final ShipmentScheduleId shipmentScheduleId = getCurrentShipmentScheduleId();
    final I_M_ShipmentSchedule shipmentSchedule = getCurrentShipmentSchedule();
    final Quantity qtyToDeliverTarget = Services.get(IShipmentScheduleBL.class).getQtyToDeliver(shipmentSchedule);
    final Quantity qtyPickedPlanned = Services.get(IPackagingDAO.class).retrieveQtyPickedPlanned(shipmentScheduleId).orElse(null);
    if (qtyPickedPlanned == null) {
        return qtyToDeliverTarget.toZero();
    }
    return qtyToDeliverTarget.subtract(qtyPickedPlanned).toZeroIfNegative();
}


public List<I_M_HU> retrieveAvailableSourceHUs(){
    final I_M_ShipmentSchedule shipmentSchedule = getCurrentShipmentSchedule();
    final PickingHUsQuery query = PickingHUsQuery.builder().onlyIfAttributesMatchWithShipmentSchedules(true).shipmentSchedule(shipmentSchedule).onlyTopLevelHUs(true).build();
    final List<I_M_HU> sourceHUs = huPickingSlotBL.retrieveAvailableSourceHUs(query);
    return sourceHUs;
}


}