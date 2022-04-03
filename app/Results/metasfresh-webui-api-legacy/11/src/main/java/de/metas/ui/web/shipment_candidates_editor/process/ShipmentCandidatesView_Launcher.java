package de.metas.ui.web.shipment_candidates_editor.process;
 import org.adempiere.exceptions.AdempiereException;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableSet;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.shipment_candidates_editor.ShipmentCandidatesViewFactory;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
public class ShipmentCandidatesView_Launcher extends ViewBasedProcessTemplateimplements IProcessPrecondition{

@Autowired
 private  IViewsRepository viewsFactory;


@Override
public String doIt(){
    final ImmutableSet<ShipmentScheduleId> shipmentScheduleIds = getSelectedShipmentScheduleIds();
    if (shipmentScheduleIds.isEmpty()) {
        throw new AdempiereException("@NoSelection@");
    }
    final ViewId viewId = viewsFactory.createView(CreateViewRequest.builder(ShipmentCandidatesViewFactory.WINDOWID).setFilterOnlyIds(ShipmentScheduleId.toIntSet(shipmentScheduleIds)).build()).getViewId();
    getResult().setWebuiViewToOpen(WebuiViewToOpen.builder().viewId(viewId.getViewId()).target(ViewOpenTarget.ModalOverlay).build());
    return MSG_OK;
}


public ImmutableSet<ShipmentScheduleId> getSelectedShipmentScheduleIds(){
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    if (selectedRowIds.isEmpty()) {
        return ImmutableSet.of();
    } else if (selectedRowIds.isAll()) {
        return getView().streamByIds(DocumentIdsSelection.ALL).map(IViewRow::getId).map(rowId -> ShipmentScheduleId.ofRepoId(rowId.toInt())).collect(ImmutableSet.toImmutableSet());
    } else {
        return selectedRowIds.stream().map(rowId -> ShipmentScheduleId.ofRepoId(rowId.toInt())).collect(ImmutableSet.toImmutableSet());
    }
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    if (selectedRowIds.isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection().toInternal();
    }
    return ProcessPreconditionsResolution.accept();
}


}