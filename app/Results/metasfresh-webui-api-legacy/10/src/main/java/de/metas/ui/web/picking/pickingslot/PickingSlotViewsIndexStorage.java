package de.metas.ui.web.picking.pickingslot;
 import java.util.Set;
import java.util.stream.Stream;
import org.adempiere.exceptions.AdempiereException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.collect.ImmutableSet;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.ui.web.picking.PickingConstants;
import de.metas.ui.web.picking.packageable.PackageableRow;
import de.metas.ui.web.picking.packageable.PackageableView;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsIndexStorage;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.event.ViewChangesCollector;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.NonNull;
@Component
public class PickingSlotViewsIndexStorage implements IViewsIndexStorage{

@Autowired
 private  PickingSlotViewFactory pickingSlotViewFactory;

 private  IViewsRepository viewsRepository;


public PackageableView getPackageableViewByPickingSlotViewId(ViewId pickingSlotViewId){
    final ViewId pickingViewId = extractPickingViewId(pickingSlotViewId);
    final PackageableView view = PackageableView.cast(getViewsRepository().getView(pickingViewId));
    return view;
}


@Override
public PickingSlotView getByIdOrNull(ViewId pickingSlotViewId){
    final boolean create = true;
    return getOrCreatePickingSlotView(pickingSlotViewId, create);
}


@Override
public void put(IView pickingSlotView){
    final ViewId pickingSlotViewId = pickingSlotView.getViewId();
    final PackageableView packageableView = getPackageableViewByPickingSlotViewId(pickingSlotViewId);
    final DocumentId rowId = extractRowId(pickingSlotViewId);
    packageableView.setPickingSlotView(rowId, PickingSlotView.cast(pickingSlotView));
}


public PickingSlotView getOrCreatePickingSlotView(ViewId pickingSlotViewId,boolean create){
    final PackageableView packageableView = getPackageableViewByPickingSlotViewId(pickingSlotViewId);
    final DocumentId packageableRowId = extractRowId(pickingSlotViewId);
    if (create) {
        return packageableView.computePickingSlotViewIfAbsent(packageableRowId, () -> {
            final PackageableRow packageableRow = packageableView.getById(packageableRowId);
            final CreateViewRequest createViewRequest = CreateViewRequest.builder(PickingConstants.WINDOWID_PickingSlotView, JSONViewDataType.includedView).setParentViewId(packageableView.getViewId()).setParentRowId(packageableRow.getId()).build();
            // provide all pickingView's M_ShipmentSchedule_IDs to the factory, because we want to show the same picking slots and picked HU-rows for all of them.
            final Set<ShipmentScheduleId> allShipmentScheduleIds = packageableView.streamByIds(DocumentIdsSelection.ALL).map(PackageableRow::cast).map(PackageableRow::getShipmentScheduleId).collect(ImmutableSet.toImmutableSet());
            return pickingSlotViewFactory.createView(createViewRequest, allShipmentScheduleIds);
        });
    } else {
        return packageableView.getPickingSlotViewOrNull(packageableRowId);
    }
}


@NonNull
public IViewsRepository getViewsRepository(){
    return viewsRepository;
}


public ViewId createViewId(ViewId pickingViewId,DocumentId pickingRowId){
    if (!PickingConstants.WINDOWID_PickingView.equals(pickingViewId.getWindowId())) {
        throw new AdempiereException("Invalid pickingViewId '" + pickingViewId + "'. WindowId not matching.").setParameter("expectedWindowId", PickingConstants.WINDOWID_PickingView);
    }
    return ViewId.ofParts(PickingConstants.WINDOWID_PickingSlotView, pickingViewId.getViewIdPart(), pickingRowId.toJson());
}


public ViewId extractPickingViewId(ViewId pickingSlotViewId){
    final String viewIdPart = pickingSlotViewId.getViewIdPart();
    return ViewId.ofParts(PickingConstants.WINDOWID_PickingView, viewIdPart);
}


@Override
public void invalidateView(ViewId pickingSlotViewId){
    final PickingSlotView pickingSlotView = getOrCreatePickingSlotView(pickingSlotViewId, false);
    if (pickingSlotView == null) {
        return;
    }
    pickingSlotView.invalidateAll();
    ViewChangesCollector.getCurrentOrAutoflush().collectFullyChanged(pickingSlotView);
}


@Override
public void setViewsRepository(IViewsRepository viewsRepository){
    this.viewsRepository = viewsRepository;
}


public DocumentId extractRowId(ViewId pickingSlotViewId){
    final String rowIdStr = pickingSlotViewId.getPart(2);
    return DocumentId.of(rowIdStr);
}


@Override
public Stream<IView> streamAllViews(){
    // Do we really have to implement this?
    return Stream.empty();
}


@Override
public WindowId getWindowId(){
    return PickingConstants.WINDOWID_PickingSlotView;
}


@Override
public void closeById(ViewId pickingSlotViewId,ViewCloseAction closeAction){
    final DocumentId rowId = extractRowId(pickingSlotViewId);
    final PackageableView packageableView = getPackageableViewByPickingSlotViewId(pickingSlotViewId);
    packageableView.removePickingSlotView(rowId, closeAction);
}


}