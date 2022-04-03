package de.metas.ui.web.picking.husToPick.process;
 import de.metas.handlingunits.sourcehu.SourceHUsService;
import de.metas.process.IProcessPrecondition;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_HUEditor_Launcher;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_PickQtyToExistingHU;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_PickQtyToNewHU;
public class WEBUI_Picking_HUEditor_Create_M_Source_HUs extends HUsToPickViewBasedProcessimplements IProcessPrecondition{


@Override
public String doIt(){
    final SourceHUsService sourceHuService = SourceHUsService.get();
    retrieveEligibleHUEditorRows().forEach(huEditorRow -> sourceHuService.addSourceHuMarker(huEditorRow.getHuId()));
    invalidateAndGoBackToPickingSlotsView();
    return MSG_OK;
}


}