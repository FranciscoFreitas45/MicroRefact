package org.opengeoportal.DTO;
 import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Future;
import org.opengeoportal.download.controllers.RequestStatusController.StatusSummary;
import org.opengeoportal.layer.BoundingBox;
import org.opengeoportal.solr.SolrRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.opengeoportal.Interface.BoundingBox;
import org.opengeoportal.Interface.SolrRecord;
public class ImageRequest {

 final  Logger logger;

 private  UUID requestId;

 private  String sessionId;

 private String srs;

 private String bbox;

 private BoundingBox bounds;

 private String format;

 private int height;

 private int width;

 private List<LayerImage> layerImage;

 private File downloadFile;

 private Boolean downloadFileSet;

 private String name;

 private int opacity;

 private int zIndex;

 private String layerId;

 private String sld;

 private SolrRecord solrRecord;

 private File imageFile;

 private Future<File> imageFileFuture;

 private ImageStatus imageStatus;

 private  URL url;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public ImageStatus getImageStatus(){
    return imageStatus;
}


public String getName(){
    return name;
}


public StatusSummary getStatusSummary(){
    return getRawStatusSummary();
}


public List<LayerImage> getLayers(){
    return this.layerImage;
}


public Set<String> getLayerIds(){
    Set<String> layerIds = new HashSet<String>();
    for (LayerImage layerImage : this.layerImage) {
        layerIds.add(layerImage.getLayerId());
    }
    return layerIds;
}


public int getWidth(){
    return width;
}


public BoundingBox getBounds(){
    return bounds;
}


public File getImageFile(){
    return imageFile;
}


public int getOpacity(){
    return opacity;
}


public int getHeight(){
    return height;
}


public SolrRecord getSolrRecord(){
    return solrRecord;
}


public int getzIndex(){
    return zIndex;
}


public String getFormat(){
    return format;
}


public Future<File> getImageFileFuture(){
    return imageFileFuture;
}


public String getLayerId(){
    return layerId;
}


public File getDownloadFile(){
    return downloadFile;
}


public StatusSummary getRawStatusSummary(){
    // Processing or Complete for the request
    StatusSummary completionStatus = null;
    int successCount = 0;
    int failureCount = 0;
    List<LayerImage> layerList = getLayers();
    for (LayerImage request : layerList) {
        logger.info(request.getImageStatus().toString());
        if (request.getImageStatus().equals(ImageStatus.PROCESSING)) {
            return StatusSummary.PROCESSING;
        } else if (request.getImageStatus().equals(ImageStatus.SUCCESS)) {
            successCount++;
        } else if (request.getImageStatus().equals(ImageStatus.FAILED)) {
            failureCount++;
        }
    }
    if (failureCount == 0) {
        if (!downloadFileSet) {
            completionStatus = StatusSummary.PROCESSING;
        } else if (getDownloadFile().exists()) {
            completionStatus = StatusSummary.COMPLETE_SUCCEEDED;
        } else {
            completionStatus = StatusSummary.PROCESSING;
        }
    } else if (successCount == 0) {
        completionStatus = StatusSummary.COMPLETE_FAILED;
    } else {
        if (!downloadFileSet) {
            completionStatus = StatusSummary.PROCESSING;
        } else if (getDownloadFile().exists()) {
            completionStatus = StatusSummary.COMPLETE_PARTIAL;
        } else {
            completionStatus = StatusSummary.PROCESSING;
        }
    }
    return completionStatus;
}


public UUID getRequestId(){
    return requestId;
}


public String getBbox(){
    return bbox;
}


public URL getUrl(){
    return url;
}


public String getSrs(){
    return srs;
}


public String getSessionId(){
    return sessionId;
}


public String getSld(){
    return sld;
}


@JsonIgnore
public boolean equals(Object o){
    if (!(o instanceof LayerImage))
        return false;
    LayerImage n = (LayerImage) o;
    return n.layerId.equals(layerId);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))

.queryParam("o",o)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public void setRequestId(UUID requestId){
    this.requestId = requestId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRequestId"))

.queryParam("requestId",requestId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSessionId(String sessionId){
    this.sessionId = sessionId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSessionId"))

.queryParam("sessionId",sessionId)
;
restTemplate.put(builder.toUriString(),null);
}


}