package de.metas.ui.web.picking.packageable;
 import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.handlingunits.picking.requests.CloseForShipmentSchedulesRequest;
import de.metas.i18n.ITranslatableString;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.inoutcandidate.model.I_M_Packageable_V;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.picking.pickingslot.PickingSlotView;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Builder;
import lombok.NonNull;
public class PackageableView extends AbstractCustomView<PackageableRow>{

 private  PickingCandidateService pickingCandidateService;

 private  ConcurrentHashMap<DocumentId,PickingSlotView> pickingSlotsViewByRowId;


@Override
public PackageableRowsData getRowsData(){
    return PackageableRowsData.cast(super.getRowsData());
}


public PackageableView cast(IView view){
    return (PackageableView) view;
}


public void setPickingSlotView(DocumentId rowId,PickingSlotView pickingSlotView){
    pickingSlotsViewByRowId.put(rowId, pickingSlotView);
}


@Override
public String getTableNameOrNull(DocumentId ignored){
    return I_M_Packageable_V.Table_Name;
}


public PickingSlotView computePickingSlotViewIfAbsent(DocumentId rowId,Supplier<PickingSlotView> pickingSlotViewFactory){
    return pickingSlotsViewByRowId.computeIfAbsent(rowId, id -> pickingSlotViewFactory.get());
}


public PickingSlotView getPickingSlotViewOrNull(DocumentId rowId){
    return pickingSlotsViewByRowId.get(rowId);
}


public void closePickingCandidatesFromRackSystemPickingSlots(){
    final Set<ShipmentScheduleId> shipmentScheduleIds = getRows().stream().map(PackageableRow::getShipmentScheduleId).collect(ImmutableSet.toImmutableSet());
    // Close all picking candidates which are on a rack system picking slot (gh2740)
    pickingCandidateService.closeForShipmentSchedules(CloseForShipmentSchedulesRequest.builder().shipmentScheduleIds(shipmentScheduleIds).pickingSlotIsRackSystem(true).failOnError(// close as much candidates as it's possible
    false).build());
}


@Override
public void close(ViewCloseAction action){
    if (action.isDone()) {
        closePickingCandidatesFromRackSystemPickingSlots();
    }
}


public void removePickingSlotView(DocumentId rowId,ViewCloseAction viewCloseAction){
    final PickingSlotView view = pickingSlotsViewByRowId.remove(rowId);
    if (view != null) {
        view.close(viewCloseAction);
    }
}


}