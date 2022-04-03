package org.opengeoportal.download.controllers.RequestStatus;
 import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.opengeoportal.download.controllers.RequestStatusController.StatusSummary;
public class RequestStatusElement {

 private  UUID requestId;

 private  String type;

 private  StatusSummary status;

RequestStatusElement(UUID requestId, String type, StatusSummary status) {
    this.requestId = requestId;
    this.setType(type);
    this.status = status;
}
public void setRequestId(UUID requestId){
    this.requestId = requestId;
}


public String getType(){
    return type;
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