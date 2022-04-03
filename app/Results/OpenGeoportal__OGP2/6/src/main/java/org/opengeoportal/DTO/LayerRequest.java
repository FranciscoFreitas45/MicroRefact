package org.opengeoportal.DTO;
 import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Future;
import org.opengeoportal.layer.BoundingBox;
import org.opengeoportal.ogc.OwsInfo;
import org.opengeoportal.solr.SolrRecord;
import org.opengeoportal.utilities.LocationFieldUtils;
import org.opengeoportal.utilities.OgpUtils;
import com.fasterxml.jackson.core.JsonParseException;
import org.opengeoportal.Interface.SolrRecord;
import org.opengeoportal.Interface.BoundingBox;
public class LayerRequest {

 final  String id;

 final  SolrRecord layerInfo;

 private List<OwsInfo> owsInfo;

 private  String requestedFormat;

 private  UUID jobId;

 private  String emailAddress;

 private  Status status;

 private  File targetDirectory;

 private  BoundingBox requestedBounds;

 private  String epsgCode;

 private  Boolean shouldHaveFiles;

 public  Set<File> downloadedFiles;

 public  String responseMIMEType;

 public  Map<String,List<String>> responseHeaders;

 public  Boolean metadata;

 private  Date timeStamp;

 private  Future<?> futureValue;

public LayerRequest(SolrRecord record, String requestedFormat) {
    this.id = record.getLayerId();
    this.layerInfo = record;
    this.timeStamp = new Date();
    // probably best to make all values with a limited set of possibilities enums
    this.setRequestedFormat(requestedFormat);
    this.status = Status.PROCESSING;
}
public Future<?> getFutureValue(){
    return futureValue;
}


public Boolean getShouldHaveFiles(){
    return shouldHaveFiles;
}


public List<OwsInfo> getOwsInfo(){
    return owsInfo;
}


public String getId(){
    return id;
}


public Status getStatus(){
    return this.status;
}


public String getResponseMIMEType(){
    return responseMIMEType;
}


public String getWmsUrl(){
    return LocationFieldUtils.getWmsUrl(this.layerInfo.getLocation());
}


public Map<String,List<String>> getResponseHeaders(){
    return responseHeaders;
}


public SolrRecord getLayerInfo(){
    return this.layerInfo;
}


public File getTargetDirectory(){
    return targetDirectory;
}


public BoundingBox getRequestedBounds(){
    return requestedBounds;
}


public Set<File> getDownloadedFiles(){
    return downloadedFiles;
}


public UUID getJobId(){
    return jobId;
}


public String getRequestedFormat(){
    return requestedFormat;
}


public String getWcsUrl(){
    String url = "";
    try {
        url = LocationFieldUtils.getWcsUrl(this.layerInfo.getLocation());
    } catch (JsonParseException e) {
        Map<String, String> infoMap = OwsInfo.findWmsInfo(this.getOwsInfo()).getInfoMap();
        if (infoMap.get("owsType").equalsIgnoreCase("wcs")) {
            url = infoMap.get("owsUrl");
        }
    }
    if (url.isEmpty()) {
        throw new Exception("No WCS url found!");
    }
    return url;
}


public String getEpsgCode(){
    return epsgCode;
}


public Date getTimeStamp(){
    return this.timeStamp;
}


public String getEmailAddress(){
    return emailAddress;
}


public String getLayerNameNS(){
    return OgpUtils.getLayerNameNS(this.layerInfo.getWorkspaceName(), this.layerInfo.getName());
}


public List<String> getDownloadUrl(){
    return LocationFieldUtils.getDownloadUrl(this.layerInfo.getLocation());
}


public String getWfsUrl(){
    String url = "";
    try {
        url = LocationFieldUtils.getWfsUrl(this.layerInfo.getLocation());
    } catch (JsonParseException e) {
        Map<String, String> infoMap = OwsInfo.findWmsInfo(this.getOwsInfo()).getInfoMap();
        if (infoMap.get("owsType").equalsIgnoreCase("wfs")) {
            url = infoMap.get("owsUrl");
        }
    }
    if (url.isEmpty()) {
        throw new Exception("No WFS url found!");
    }
    return url;
}


}