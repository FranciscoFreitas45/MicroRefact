package org.opengeoportal.download.methods;
 import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Future;
import org.apache.commons.io.IOUtils;
import org.opengeoportal.download.types.LayerRequest;
import org.opengeoportal.download.types.LayerRequest.Status;
import org.opengeoportal.layer.BoundingBox;
import org.opengeoportal.utilities.OgpUtils;
import org.opengeoportal.utilities.http.HttpRequester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import com.fasterxml.jackson.core.JsonParseException;
import org.opengeoportal.Interface.HttpRequester;
import org.opengeoportal.DTO.HttpRequester;
public class HGLEmailDownloadMethod implements EmailDownloadMethod{

 private  Boolean INCLUDES_METADATA;

 private  HttpRequester httpRequester;

 private  List<LayerRequest> layerList;

 final  Logger logger;


@Override
public String createDownloadRequest(){
    LayerRequest representativeLayer = this.layerList.get(0);
    BoundingBox bounds = representativeLayer.getRequestedBounds();
    String userEmail = representativeLayer.getEmailAddress();
    String layerQuery = "";
    for (LayerRequest currentLayer : this.layerList) {
        layerQuery += "LayerName=" + currentLayer.getLayerInfo().getName() + "&";
    }
    String getFeatureRequest = layerQuery + "UserEmail=" + userEmail + "&Clip=true&EmailAdmin=true&AppID=55&EncryptionKey=OPENGEOPORTALROCKS" + "&XMin=" + bounds.getMinX() + "&YMin=" + bounds.getMinY() + "&XMax=" + bounds.getMaxX() + "&YMax=" + bounds.getMaxY();
    return getFeatureRequest;
}


public String getUrl(LayerRequest layer){
    logger.info("Download URL: " + layer.getDownloadUrl());
    return layer.getDownloadUrl().get(0);
}


public void setHttpRequester(HttpRequester httpRequester){
    this.httpRequester = httpRequester;
}


@Override
@Async
public Future<Boolean> sendEmail(List<LayerRequest> layerList){
    this.layerList = layerList;
    InputStream inputStream = null;
    try {
        this.validate(layerList.get(0));
        inputStream = this.httpRequester.sendRequest(this.getUrl(layerList.get(0)), createDownloadRequest(), "GET");
        return new AsyncResult<Boolean>(true);
    } catch (Exception e) {
        logger.error(e.getMessage());
        return new AsyncResult<Boolean>(false);
    } finally {
        IOUtils.closeQuietly(inputStream);
    }
}


public void setLayerList(List<LayerRequest> layerList){
    this.layerList = layerList;
}


public void setAllLayerStatus(Status status){
    for (LayerRequest currentLayer : this.layerList) {
        currentLayer.setStatus(status);
    }
}


@Override
public Boolean includesMetadata(){
    return INCLUDES_METADATA;
}


@Override
public Boolean hasRequiredInfo(LayerRequest layerRequest){
    if (!OgpUtils.isWellFormedEmailAddress(layerRequest.getEmailAddress())) {
        return false;
    }
    try {
        if (getUrl(layerRequest) != null) {
            return true;
        }
    } catch (JsonParseException e) {
        logger.error(e.getMessage());
    }
    logger.info("Layer does not have required info for HGLEmailDownload");
    return false;
}


public void validate(LayerRequest currentLayer){
    if (currentLayer.getEmailAddress().isEmpty()) {
        throw new Exception("A valid email address must be supplied.");
    }
}


}