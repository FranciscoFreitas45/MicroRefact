package de.metas.ui.web.picking.packageable;
 import java.util.Set;
import javax.annotation.Nullable;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.ui.web.picking.PickingConstants;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.IncludedViewLayout;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.NonNull;
@ViewFactory(windowId = PickingConstants.WINDOWID_PickingView_String, viewTypes = { JSONViewDataType.grid, JSONViewDataType.includedView })
public class PackageableViewFactory implements IViewFactory{

 private  PackageableRowsRepository pickingViewRepo;

 private  PickingCandidateService pickingCandidateService;


public Set<ShipmentScheduleId> extractShipmentScheduleIds(CreateViewRequest request){
    return request.getFilterOnlyIds().stream().map(ShipmentScheduleId::ofRepoId).collect(ImmutableSet.toImmutableSet());
}


@Override
public IView createView(CreateViewRequest request){
    final ViewId viewId = request.getViewId();
    if (!PickingConstants.WINDOWID_PickingView.equals(viewId.getWindowId())) {
        throw new IllegalArgumentException("Invalid request's windowId: " + request);
    }
    final Set<ShipmentScheduleId> shipmentScheduleIds = extractShipmentScheduleIds(request);
    final PackageableRowsData rowsData = pickingViewRepo.createRowsData(viewId, shipmentScheduleIds);
    return PackageableView.builder().viewId(viewId).rowsData(rowsData).pickingCandidateService(pickingCandidateService).build();
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    return ViewLayout.builder().setWindowId(PickingConstants.WINDOWID_PickingView).setCaption("Picking").setIncludedViewLayout(IncludedViewLayout.builder().openOnSelect(true).build()).addElementsFromViewRowClass(PackageableRow.class, viewDataType).build();
}


}