package de.metas.ui.web.pporder.process;
 import org.compiere.Adempiere;
import org.eevolution.api.PPOrderPlanningStatus;
import de.metas.handlingunits.model.I_PP_Order;
import de.metas.handlingunits.pporder.api.IHUPPOrderBL;
import de.metas.material.planning.pporder.PPOrderId;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.pporder.PPOrderLinesView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.util.Services;
import lombok.NonNull;
public class WEBUI_PP_Order_ChangePlanningStatus_Template extends WEBUI_PP_Order_Templateimplements IProcessPrecondition{

 private  IHUPPOrderBL huPPOrderBL;

 private  IViewsRepository viewsRepo;

 private  PPOrderPlanningStatus targetPlanningStatus;


@Override
public String doIt(){
    huPPOrderBL.processPlanning(targetPlanningStatus, getView().getPpOrderId());
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final PPOrderPlanningStatus planningStatus = getView().getPlanningStatus();
    if (!huPPOrderBL.canChangePlanningStatus(planningStatus, targetPlanningStatus)) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not applicable for current status");
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    final PPOrderLinesView ppOrderLinesView = getView();
    ppOrderLinesView.invalidateAll();
    final PPOrderId ppOrderId = ppOrderLinesView.getPpOrderId();
    viewsRepo.notifyRecordChanged(I_PP_Order.Table_Name, ppOrderId.getRepoId());
}


}