package de.metas.ui.web.picking.pickingslot.process;
 import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_MISSING_SOURCE_HU;
import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_SELECT_PICKED_CU;
import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_SELECT_PICKED_HU;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.handlingunits.picking.requests.RemoveQtyFromHURequest;
import de.metas.handlingunits.sourcehu.HuId2SourceHUsService;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorRowType;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
public class WEBUI_Picking_ReturnQtyToSourceHU extends WEBUI_Picking_With_M_Source_HU_Baseimplements IProcessPrecondition{

@Autowired
 private  PickingCandidateService pickingCandidateService;

@Autowired
 private  HuId2SourceHUsService sourceHUsRepository;

 private  String PARAM_QTY_CU;

@Param(parameterName = PARAM_QTY_CU, mandatory = true)
 private  BigDecimal qtyCU;


@Override
public String doIt(){
    final PickingSlotRow pickingSlotRow = getSingleSelectedRow();
    pickingCandidateService.removeQtyFromHU(RemoveQtyFromHURequest.builder().qtyCU(qtyCU).huId(pickingSlotRow.getHuId()).productId(pickingSlotRow.getHuProductId()).build());
    invalidateView();
    invalidateParentView();
    return MSG_OK;
}


public boolean checkSourceHuPreconditionIncludingEmptyHUs(){
    final HuId huId = getSingleSelectedRow().getHuId();
    final Collection<HuId> sourceHUs = sourceHUsRepository.retrieveMatchingSourceHUIds(huId);
    return !sourceHUs.isEmpty();
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
    final String rowType = pickingSlotRow.getType().getName();
    final boolean cuRow = Objects.equals(rowType, HUEditorRowType.VHU.getName()) || Objects.equals(rowType, HUEditorRowType.HUStorage.getName());
    if (cuRow) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_SELECT_PICKED_CU));
    }
    if (!checkSourceHuPreconditionIncludingEmptyHUs()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_MISSING_SOURCE_HU));
    }
    return ProcessPreconditionsResolution.accept();
}


}