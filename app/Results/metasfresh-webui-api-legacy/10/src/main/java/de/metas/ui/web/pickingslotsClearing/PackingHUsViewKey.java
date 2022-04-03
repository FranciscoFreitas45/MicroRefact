package de.metas.ui.web.pickingslotsClearing;
 import de.metas.ui.web.view.ViewId;
import lombok.NonNull;
@lombok.Value
public class PackingHUsViewKey {

 private  int bpartnerId;

 private  int bpartnerLocationId;

 private  ViewId packingHUsViewId;


public PackingHUsViewKey ofPackingHUsViewId(ViewId packingHUsViewId){
    final String pickingSlotsClearingViewIdPart = packingHUsViewId.getViewIdPart();
    final int bpartnerId = packingHUsViewId.getPartAsInt(2);
    final int bpartnerLocationId = packingHUsViewId.getPartAsInt(3);
    return new PackingHUsViewKey(pickingSlotsClearingViewIdPart, bpartnerId, bpartnerLocationId);
}


public boolean isBPartnerIdMatching(int bpartnerIdToMatch){
    return (bpartnerIdToMatch <= 0 && bpartnerId <= 0) || bpartnerIdToMatch == bpartnerId;
}


public ViewId extractPickingSlotClearingViewId(ViewId packingHUsViewId){
    return ViewId.ofParts(PickingSlotsClearingViewFactory.WINDOW_ID, packingHUsViewId.getViewIdPart());
}


public ViewId getPickingSlotsClearingViewId(){
    return extractPickingSlotClearingViewId(packingHUsViewId);
}


public boolean isBPartnerLocationIdMatching(int bpartnerLocationIdToMatch){
    return (bpartnerLocationIdToMatch <= 0 && bpartnerLocationId <= 0) || bpartnerLocationIdToMatch == bpartnerLocationId;
}


}