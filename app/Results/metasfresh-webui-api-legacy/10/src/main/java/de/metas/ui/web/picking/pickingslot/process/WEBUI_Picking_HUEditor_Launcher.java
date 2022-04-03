package de.metas.ui.web.picking.pickingslot.process;
 import org.springframework.beans.factory.annotation.Autowired;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.picking.husToPick.HUsToPickViewFactory;
import de.metas.ui.web.picking.pickingslot.PickingSlotRowId;
import de.metas.ui.web.picking.pickingslot.PickingSlotView;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
public class WEBUI_Picking_HUEditor_Launcher extends PickingSlotViewBasedProcess{

@Autowired
 private  IViewsRepository viewsRepo;

@Autowired
 private  HUsToPickViewFactory husToPickViewFactory;


@Override
public String doIt(){
    final IView husToPickView = createHUsToPickView();
    getResult().setWebuiViewToOpen(WebuiViewToOpen.builder().viewId(husToPickView.getViewId().getViewId()).profileId("husToPick").target(ViewOpenTarget.IncludedView).build());
    return MSG_OK;
}


public IView createHUsToPickView(){
    final PickingSlotView pickingSlotsView = getView();
    final PickingSlotRowId pickingSlotRowId = getSingleSelectedRow().getPickingSlotRowId();
    final ViewId pickingSlotViewId = pickingSlotsView.getViewId();
    final ShipmentScheduleId shipmentScheduleId = pickingSlotsView.getCurrentShipmentScheduleId();
    final CreateViewRequest createRequest = husToPickViewFactory.createViewRequest(pickingSlotViewId, pickingSlotRowId, shipmentScheduleId);
    final IView husToPickView = viewsRepo.createView(createRequest);
    return husToPickView;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!getSelectedRowIds().isSingleDocumentId()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    return ProcessPreconditionsResolution.accept();
}


}