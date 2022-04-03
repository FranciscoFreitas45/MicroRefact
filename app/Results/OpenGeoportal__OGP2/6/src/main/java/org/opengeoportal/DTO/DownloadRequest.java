package org.opengeoportal.DTO;
 import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.opengeoportal.download.MethodLevelDownloadRequest;
import org.opengeoportal.download.controllers.RequestStatusController.StatusSummary;
import org.opengeoportal.download.types.LayerRequest;
import org.opengeoportal.download.types.LayerRequest.Status;
import org.opengeoportal.layer.BoundingBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.opengeoportal.Interface.BoundingBox;
public class DownloadRequest {

 final  Logger logger;

 private  BoundingBox bounds;

 private  String email;

 private  List<RequestedLayerFormat> requestedLayerFormat;

 private  String format;

 private  String layerId;

 private  UUID requestId;

 private  String sessionId;

 private  File downloadPackage;

 private  List<MethodLevelDownloadRequest> requestList;

 private  Boolean downloadPackageSet;

 private  Boolean emailSent;

 private  Integer dlpackageSetCounter;


public List<MethodLevelDownloadRequest> getRequestList(){
    return requestList;
}


public StatusSummary getStatusSummary(){
    logger.debug("getting status summary");
    StatusSummary completionStatus = StatusSummary.COMPLETE_FAILED;
    try {
        completionStatus = getRawStatusSummary();
    } catch (Exception e) {
        e.printStackTrace();
    }
    if (completionStatus.equals(StatusSummary.COMPLETE_SUCCEEDED) || completionStatus.equals(StatusSummary.COMPLETE_PARTIAL)) {
        if (!isPostProcessingComplete()) {
            return StatusSummary.PROCESSING;
        }
    }
    return completionStatus;
}


public BoundingBox getBounds(){
    return bounds;
}


public Boolean getEmailSent(){
    return emailSent;
}


public List<RequestedLayerFormat> getRequestedLayerFormat(){
    return requestedLayerFormat;
}


public String getFormat(){
    return format;
}


public String getLayerId(){
    return layerId;
}


public StatusSummary getRawStatusSummary(){
    // Processing or Complete for the request
    logger.debug("Getting raw status summary");
    List<LayerRequest> layerList = new ArrayList<LayerRequest>();
    for (MethodLevelDownloadRequest request : requestList) {
        layerList.addAll(request.getRequestList());
    }
    return getStatusSummaryForList(layerList);
}


public UUID getRequestId(){
    return requestId;
}


public String getRequestedFormatForLayerId(String layerId){
    for (RequestedLayerFormat layer : requestedLayerFormat) {
        if (layer.getLayerId().equalsIgnoreCase(layerId)) {
            return layer.getFormat();
        }
    }
    throw new Exception("No format found for the specified layer.");
}


public StatusSummary getStatusSummaryForList(List<LayerRequest> layerList){
    // Processing or Complete for the request
    logger.debug("Getting raw status summary");
    StatusSummary completionStatus = null;
    int successCount = 0;
    int failureCount = 0;
    for (LayerRequest request : layerList) {
        logger.debug("status: " + request.getStatus().toString());
        if (request.getStatus().equals(Status.PROCESSING)) {
            return StatusSummary.PROCESSING;
        } else if (request.getStatus().equals(Status.SUCCESS)) {
            successCount++;
        } else if (request.getStatus().equals(Status.FAILED)) {
            failureCount++;
        }
    }
    if (layerList.size() == 0) {
        completionStatus = StatusSummary.COMPLETE_FAILED;
    } else if (failureCount == 0) {
        completionStatus = StatusSummary.COMPLETE_SUCCEEDED;
    } else if (successCount == 0) {
        completionStatus = StatusSummary.COMPLETE_FAILED;
    } else {
        completionStatus = StatusSummary.COMPLETE_PARTIAL;
    }
    return completionStatus;
}


public String getEmail(){
    return email;
}


public Set<String> getRequestedLayerIds(){
    Set<String> layerIds = new HashSet<String>();
    for (RequestedLayerFormat layer : requestedLayerFormat) {
        layerIds.add(layer.getLayerId());
    }
    return layerIds;
}


public File getDownloadPackage(){
    return downloadPackage;
}


public String getSessionId(){
    return sessionId;
}


}