package de.metas.ui.web.picking.pickingslot.process;
 import org.compiere.model.I_C_UOM;
import de.metas.handlingunits.model.I_M_ShipmentSchedule;
import de.metas.inoutcandidate.api.IShipmentScheduleBL;
import de.metas.inoutcandidate.api.IShipmentSchedulePA;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.process.IProcessPrecondition;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
import de.metas.ui.web.picking.pickingslot.PickingSlotView;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.util.Services;
public class PickingSlotViewBasedProcess extends ViewBasedProcessTemplateimplements IProcessPrecondition{

 private  I_M_ShipmentSchedule _shipmentSchedule;


public I_M_ShipmentSchedule getCurrentShipmentSchedule(){
    I_M_ShipmentSchedule shipmentSchedule = _shipmentSchedule;
    if (shipmentSchedule == null) {
        final ShipmentScheduleId shipmentScheduleId = getCurrentShipmentScheduleId();
        final IShipmentSchedulePA shipmentSchedulesRepo = Services.get(IShipmentSchedulePA.class);
        _shipmentSchedule = shipmentSchedule = shipmentSchedulesRepo.getById(shipmentScheduleId, I_M_ShipmentSchedule.class);
    }
    return shipmentSchedule;
}


public I_C_UOM getCurrentShipmentScheuduleUOM(){
    final I_M_ShipmentSchedule shipmentSchedule = getCurrentShipmentSchedule();
    final I_C_UOM uom = Services.get(IShipmentScheduleBL.class).getUomOfProduct(shipmentSchedule);
    return uom;
}


public void invalidatePickingSlotsView(){
    invalidateView();
}


public ShipmentScheduleId getCurrentShipmentScheduleId(){
    return getView().getCurrentShipmentScheduleId();
}


@Override
public PickingSlotRow getSingleSelectedRow(){
    return PickingSlotRow.cast(super.getSingleSelectedRow());
}


public void invalidatePackablesView(){
    invalidateParentView();
}


@Override
public PickingSlotView getView(){
    return PickingSlotView.cast(super.getView());
}


}