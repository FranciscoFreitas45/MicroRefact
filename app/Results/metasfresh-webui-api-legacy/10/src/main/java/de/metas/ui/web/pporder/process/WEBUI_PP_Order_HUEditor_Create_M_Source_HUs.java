package de.metas.ui.web.pporder.process;
 import de.metas.ui.web.handlingunits.WEBUI_HU_Constants.MSG_WEBUI_SELECT_ACTIVE_UNSELECTED_HU;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.sourcehu.SourceHUsService;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.util.Check;
import de.metas.util.Services;
public class WEBUI_PP_Order_HUEditor_Create_M_Source_HUs extends WEBUI_PP_Order_HUEditor_ProcessBaseimplements IProcessPrecondition{

@Autowired
 private  SourceHUsService sourceHuService;

 private  Set<HuId> topLevelHUIdsProcessed;


public void createSourceHU(HUEditorRow row){
    // shall not happen
    Check.assume(row.isTopLevel(), "Only top level rows are allowed");
    final HuId topLevelHUId = row.getHuId();
    sourceHuService.addSourceHuMarker(topLevelHUId);
    topLevelHUIdsProcessed.add(topLevelHUId);
}


@Override
public String doIt(){
    retrieveSelectedAndEligibleHUEditorRows().forEach(this::createSourceHU);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final boolean anyHuMatches = retrieveSelectedAndEligibleHUEditorRows().anyMatch(huRow -> huRow.isTopLevel());
    if (anyHuMatches) {
        return ProcessPreconditionsResolution.accept();
    }
    final ITranslatableString reason = Services.get(IMsgBL.class).getTranslatableMsgText(MSG_WEBUI_SELECT_ACTIVE_UNSELECTED_HU);
    return ProcessPreconditionsResolution.reject(reason);
}


@Override
public void postProcess(boolean success){
    if (!success) {
        return;
    }
    // PP_Order
    invalidateParentView();
    // HU Editor
    getView().removeHUIdsAndInvalidate(topLevelHUIdsProcessed);
// invalidateView();
}


}