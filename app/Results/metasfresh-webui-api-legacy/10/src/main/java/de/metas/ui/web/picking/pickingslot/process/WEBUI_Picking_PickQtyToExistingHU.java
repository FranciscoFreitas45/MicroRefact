package de.metas.ui.web.picking.pickingslot.process;
 import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_MISSING_SOURCE_HU;
import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_NO_UNPROCESSED_RECORDS;
import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_SELECT_PICKED_HU;
import java.math.BigDecimal;
import java.util.Objects;
import org.compiere.model.I_C_UOM;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.handlingunits.picking.requests.AddQtyToHURequest;
import de.metas.picking.api.PickingConfigRepository;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.quantity.Quantity;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
import de.metas.ui.web.picking.pickingslot.PickingSlotViewFactory;
import lombok.NonNull;
public class WEBUI_Picking_PickQtyToExistingHU extends WEBUI_Picking_With_M_Source_HU_Baseimplements IProcessDefaultParametersProvider,IProcessPrecondition{

@Autowired
 private  PickingConfigRepository pickingConfigRepo;

@Autowired
 private  PickingCandidateService pickingCandidateService;

 private  String PARAM_QTY_CU;

@Param(parameterName = PARAM_QTY_CU, mandatory = true)
 private  BigDecimal qtyCU;


public Quantity getQtyToPack(){
    final I_C_UOM uom = getCurrentShipmentScheuduleUOM();
    return Quantity.of(qtyCU, uom);
}


@Override
public Object getParameterDefaultValue(IProcessDefaultParameter parameter){
    if (Objects.equals(PARAM_QTY_CU, parameter.getColumnName())) {
        return retrieveQtyToPick().toBigDecimal();
    } else {
        return DEFAULT_VALUE_NOTAVAILABLE;
    }
}


@Override
public String doIt(){
    final PickingSlotRow pickingSlotRow = getSingleSelectedRow();
    final boolean allowOverDelivery = pickingConfigRepo.getPickingConfig().isAllowOverDelivery();
    pickingCandidateService.addQtyToHU(AddQtyToHURequest.builder().qtyToPack(getQtyToPack()).packToHuId(pickingSlotRow.getHuId()).pickingSlotId(pickingSlotRow.getPickingSlotId()).shipmentScheduleId(getCurrentShipmentScheduleId()).allowOverDelivery(allowOverDelivery).build());
    invalidateView();
    invalidateParentView();
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!getSelectedRowIds().isSingleDocumentId()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final PickingSlotRow pickingSlotRow = getSingleSelectedRow();
    if (!pickingSlotRow.isPickedHURow()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_SELECT_PICKED_HU));
    }
    if (pickingSlotRow.isProcessed()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_NO_UNPROCESSED_RECORDS));
    }
    if (!checkSourceHuPrecondition()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_MISSING_SOURCE_HU));
    }
    return ProcessPreconditionsResolution.accept();
}


}