package de.metas.ui.web.handlingunits.process;
 import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Env;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHUStatusBL;
import de.metas.handlingunits.inventory.IHUInventoryBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_Inventory;
import de.metas.inventory.event.InventoryUserNotificationsProducer;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.product.acct.api.ActivityId;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import de.metas.util.Services;
public class WEBUI_M_HU_MoveToGarbage extends HUEditorProcessTemplateimplements IProcessPrecondition{

 private  IHUInventoryBL huInventoryBL;

 private  Set<HuId> huIdsDestroyed;


@Override
public String doIt(){
    final List<I_M_HU> husToDestroy = streamSelectedHUs(Select.ONLY_TOPLEVEL).collect(ImmutableList.toImmutableList());
    if (husToDestroy.isEmpty()) {
        throw new AdempiereException("@NoSelection@");
    }
    final Timestamp movementDate = Env.getDate(getCtx());
    final List<I_M_Inventory> inventories = huInventoryBL.moveToGarbage(husToDestroy, movementDate, ActivityId.ofRepoIdOrNull(-1), null, true, true);
    // 
    // Send notifications
    InventoryUserNotificationsProducer.newInstance().notifyGenerated(inventories);
    huIdsDestroyed = husToDestroy.stream().map(I_M_HU::getM_HU_ID).map(HuId::ofRepoId).collect(ImmutableSet.toImmutableSet());
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
    final IHUStatusBL huStatusBL = Services.get(IHUStatusBL.class);
    if (!streamSelectedHUs(Select.ONLY_TOPLEVEL).anyMatch(huStatusBL::isPhysicalHU)) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("only 'physical' HUs can be disposed");
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    // Invalidate the view
    if (huIdsDestroyed != null && !huIdsDestroyed.isEmpty()) {
        getView().removeHUIdsAndInvalidate(huIdsDestroyed);
    }
}


}