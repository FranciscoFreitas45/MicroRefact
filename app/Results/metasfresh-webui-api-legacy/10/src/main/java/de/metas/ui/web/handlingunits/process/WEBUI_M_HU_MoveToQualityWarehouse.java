package de.metas.ui.web.handlingunits.process;
 import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_Warehouse;
import de.metas.handlingunits.movement.api.HUMovementResult;
import de.metas.handlingunits.movement.api.IHUMovementBL;
import de.metas.printing.esb.base.util.Check;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.util.Services;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
public class WEBUI_M_HU_MoveToQualityWarehouse extends HUEditorProcessTemplateimplements IProcessPrecondition{

 private  IHUMovementBL huMovementBL;

@Param(parameterName = I_M_Warehouse.COLUMNNAME_M_Warehouse_ID, mandatory = true)
 private  I_M_Warehouse warehouse;

 private  HUMovementResult movementResult;


@Override
public String doIt(){
    Check.assume(warehouse.isQualityReturnWarehouse(), "not a quality returns warehouse");
    final List<I_M_HU> selectedTopLevelHUs = streamSelectedHUs(Select.ONLY_TOPLEVEL).collect(ImmutableList.toImmutableList());
    if (selectedTopLevelHUs.isEmpty()) {
        throw new AdempiereException("@NoSelection@");
    }
    movementResult = huMovementBL.moveHUsToWarehouse(selectedTopLevelHUs, warehouse);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!isHUEditorView()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not the HU view");
    }
    if (!streamSelectedHUIds(Select.ONLY_TOPLEVEL).findAny().isPresent()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(WEBUI_HU_Constants.MSG_WEBUI_ONLY_TOP_LEVEL_HU));
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    // Invalidate the view
    // On refresh we expect the HUs we moved, to not be present here anymore.
    if (movementResult != null) {
        getView().removeHUsAndInvalidate(movementResult.getHusMoved());
    }
}


}