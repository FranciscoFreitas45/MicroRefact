package org.opengeoportal.download.types;
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
public void setResponseMIMEType(String responseMIMEType){
    this.responseMIMEType = responseMIMEType;
}


public void setJobId(UUID jobId){
    this.jobId = jobId;
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


public void setFutureValue(Future<?> futureValue){
    this.futureValue = futureValue;
}


public void setOwsInfo(List<OwsInfo> owsInfo){
    this.owsInfo = owsInfo;
}


public void setTargetDirectory(File targetDirectory){
    this.targetDirectory = targetDirectory;
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


public void setEmailAddress(String emailAddress){
    this.emailAddress = emailAddress;
}


public BoundingBox getRequestedBounds(){
    return requestedBounds;
}


public void setDownloadedFiles(Set<File> downloadedFiles){
    this.downloadedFiles = downloadedFiles;
}


public void setShouldHaveFiles(Boolean shouldHaveFiles){
    this.shouldHaveFiles = shouldHaveFiles;
}


public Set<File> getDownloadedFiles(){
    return downloadedFiles;
}


public void setEpsgCode(String epsgCode){
    this.epsgCode = epsgCode;
}


public UUID getJobId(){
    return jobId;
}


public void setRequestedBounds(BoundingBox requestedBounds){
    this.requestedBounds = requestedBounds;
}


public boolean hasMetadata(){
    return metadata;
}


public void setResponseHeaders(Map<String,List<String>> responseHeaders){
    this.responseHeaders = responseHeaders;
}


public String getRequestedFormat(){
    return requestedFormat;
}


public void setStatus(Status status){
    this.status = status;
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


public void setMetadata(Boolean metadata){
    this.metadata = metadata;
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


public void setRequestedFormat(String requestedFormat){
    requestedFormat = requestedFormat.toLowerCase().trim();
    if (requestedFormat.equals("kml")) {
        requestedFormat = "kmz";
    }
    this.requestedFormat = requestedFormat;
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