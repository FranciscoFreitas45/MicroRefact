package org.opengeoportal.export.geocommons;
 import java.util.List;
import java.util.UUID;
import org.opengeoportal.download.controllers.RequestStatusController.StatusSummary;
public class GeoCommonsExportRequest {

 private String sessionId;

 private UUID requestId;

 private String username;

 private String password;

 private String title;

 private String description;

 private String basemap;

 private String bbox;

 private String location;

 private ExportStatus exportStatus;

 private List<String> layerIds;


public void setPassword(String password){
    this.password = password;
}


public void setBasemap(String basemap){
    this.basemap = basemap;
}


public String getBbox(){
    return bbox;
}


public String getLocation(){
    return location;
}


public void setBbox(String bbox){
    this.bbox = bbox;
}


public void setUsername(String username){
    this.username = username;
}


public void setLayerIds(List<String> layerIds){
    this.layerIds = layerIds;
}


public StatusSummary getStatusSummary(){
    // Processing or Complete for the request
    ExportStatus exportStatus = this.getExportStatus();
    if (exportStatus.equals(ExportStatus.PROCESSING)) {
        return StatusSummary.PROCESSING;
    } else if (exportStatus.equals(ExportStatus.SUCCESS)) {
        return StatusSummary.COMPLETE_SUCCEEDED;
    } else if (exportStatus.equals(ExportStatus.FAILED)) {
        return StatusSummary.COMPLETE_FAILED;
    }
    return StatusSummary.COMPLETE_FAILED;
}


public void setTitle(String title){
    this.title = title;
}


public ExportStatus getExportStatus(){
    return exportStatus;
}


public List<String> getLayerIds(){
    return layerIds;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public String getUsername(){
    return username;
}


public String getBasemap(){
    return basemap;
}


public String getPassword(){
    return password;
}


public String getTitle(){
    return title;
}


public void setSessionId(String sessionId){
    this.sessionId = sessionId;
}


public void setRequestId(UUID requestId){
    this.requestId = requestId;
}


public void setLocation(String location){
    this.location = location;
}


public void setExportStatus(ExportStatus exportStatus){
    this.exportStatus = exportStatus;
}


public String getSessionId(){
    return sessionId;
}


public UUID getRequestId(){
    return requestId;
}


}