package org.opengeoportal.download;
 import java.util.ArrayList;
import java.util.List;
import org.opengeoportal.download.types.LayerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class MethodLevelDownloadRequest {

 final  Logger logger;

 private  String downloadKey;

 private  LayerDownloader layerDownloader;

 private  List<LayerRequest> requestList;

MethodLevelDownloadRequest(String downloadKey, LayerDownloader layerDownloader) {
    this.downloadKey = downloadKey;
    this.layerDownloader = layerDownloader;
    setRequestList(new ArrayList<LayerRequest>());
}
public void addLayerRequest(LayerRequest layerRequest){
    this.requestList.add(layerRequest);
}


public LayerDownloader getLayerDownloader(){
    return layerDownloader;
}


public String getDownloadKey(){
    return downloadKey;
}


public List<LayerRequest> getRequestList(){
    return requestList;
}


public void setRequestList(List<LayerRequest> layerRequests){
    requestList = layerRequests;
}


public void setLayerDownloader(LayerDownloader layerDownloader){
    this.layerDownloader = layerDownloader;
}


public void setDownloadKey(String downloadKey){
    this.downloadKey = downloadKey;
}


}