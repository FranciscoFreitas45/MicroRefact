package de.metas.ui.web.picking.husToPick.process;
 import org.adempiere.exceptions.AdempiereException;
import de.metas.handlingunits.HuId;
import de.metas.process.IProcessPrecondition;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.picking.pickingslot.process.WEBUI_Picking_HUEditor_Launcher;
public class WEBUI_Picking_HUEditor_PickHU extends HUsToPickViewBasedProcessimplements IProcessPrecondition{


public void pickHuRow(HUEditorRow huRow){
    final HuId huId = huRow.getHuId();
    if (!huRow.isTopLevel()) {
        // TODO: extract as top level
        throw new AdempiereException("Not a top level HU");
    }
    addHUIdToCurrentPickingSlot(huId);
}


@Override
public String doIt(){
    retrieveEligibleHUEditorRows().forEach(this::pickHuRow);
    invalidateAndGoBackToPickingSlotsView();
    return MSG_OK;
}


}