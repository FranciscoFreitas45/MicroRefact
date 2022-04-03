package org.opengeoportal.download.DownloadRequest;
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
public class RequestedLayerFormat {

@JsonProperty("format")
 private  String format;

@JsonProperty("layerId")
 private  String layerId;


public void setLayerId(String layerId){
    this.layerId = layerId;
}


public void setFormat(String format){
    this.format = format;
}


public String getFormat(){
    return format;
}


public String getLayerId(){
    return layerId;
}


}