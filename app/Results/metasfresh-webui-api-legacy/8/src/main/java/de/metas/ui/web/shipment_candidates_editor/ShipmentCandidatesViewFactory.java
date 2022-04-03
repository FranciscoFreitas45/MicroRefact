package de.metas.ui.web.shipment_candidates_editor;
 import java.util.Set;
import de.metas.i18n.IMsgBL;
import de.metas.inoutcandidate.api.IShipmentScheduleBL;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Services;
import lombok.NonNull;
@ViewFactory(windowId = ShipmentCandidatesViewFactory.WINDOWID_String)
public class ShipmentCandidatesViewFactory implements IViewFactory{

 public  String WINDOWID_String;

 public  WindowId WINDOWID;

 private  IShipmentScheduleBL shipmentScheduleBL;

 private  ShipmentCandidateRowsRepository rowsRepo;


@Override
public ShipmentCandidatesView createView(CreateViewRequest request){
    final ViewId viewId = request.getViewId();
    viewId.assertWindowId(WINDOWID);
    final Set<ShipmentScheduleId> shipmentScheduleIds = ShipmentScheduleId.fromIntSet(request.getFilterOnlyIds());
    final ShipmentCandidateRows rows = rowsRepo.getByShipmentScheduleIds(shipmentScheduleIds);
    return ShipmentCandidatesView.builder().shipmentScheduleBL(shipmentScheduleBL).viewId(viewId).rows(rows).build();
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    return ViewLayout.builder().setWindowId(WINDOWID).setCaption(Services.get(IMsgBL.class).translatable("M_ShipmentSchedule_ID")).setAllowOpeningRowDetails(false).allowViewCloseAction(ViewCloseAction.CANCEL).allowViewCloseAction(ViewCloseAction.DONE).addElementsFromViewRowClass(ShipmentCandidateRow.class, viewDataType).build();
}


}