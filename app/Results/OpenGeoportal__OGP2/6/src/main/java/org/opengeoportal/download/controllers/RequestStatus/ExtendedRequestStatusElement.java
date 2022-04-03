package org.opengeoportal.download.controllers.RequestStatus;
 import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.opengeoportal.download.controllers.RequestStatusController.StatusSummary;
public class ExtendedRequestStatusElement extends RequestStatusElement{

 private  List<RequestedLayerStatus> requestedLayerStatuses;

ExtendedRequestStatusElement(UUID requestId, String type, StatusSummary status, List<RequestedLayerStatus> requestedLayerStatuses) {
    super(requestId, type, status);
    this.setRequestedLayerStatuses(requestedLayerStatuses);
}
public void setRequestedLayerStatuses(List<RequestedLayerStatus> requestedLayerStatuses){
    this.requestedLayerStatuses = requestedLayerStatuses;
}


public List<RequestedLayerStatus> getRequestedLayerStatuses(){
    return requestedLayerStatuses;
}


}