package org.opengeoportal.download.controllers;
 import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.opengeoportal.download.controllers.RequestStatusController.StatusSummary;
public class RequestStatus {

 private  List<RequestStatusElement> requestStatus;

 private  UUID requestId;

 private  String type;

 private  StatusSummary status;

 private  List<RequestedLayerStatus> requestedLayerStatuses;


public void addRequestStatusElement(UUID requestId,String type,StatusSummary status,List<RequestedLayerStatus> requestedLayerStatuses){
    requestStatus.add(new ExtendedRequestStatusElement(requestId, type, status, requestedLayerStatuses));
}


public void setRequestedLayerStatuses(List<RequestedLayerStatus> requestedLayerStatuses){
    this.requestedLayerStatuses = requestedLayerStatuses;
}


public List<RequestStatusElement> getRequestStatus(){
    return requestStatus;
}


public void setRequestId(UUID requestId){
    this.requestId = requestId;
}


public String getType(){
    return type;
}


public List<RequestedLayerStatus> getRequestedLayerStatuses(){
    return requestedLayerStatuses;
}


public StatusSummary getStatus(){
    return status;
}


public void setType(String type){
    this.type = type;
}


public UUID getRequestId(){
    return requestId;
}


public void setStatus(StatusSummary status){
    this.status = status;
}


}