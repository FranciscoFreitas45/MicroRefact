package org.opengeoportal.download;
 import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Future;
import org.opengeoportal.download.methods.PerLayerDownloadMethod;
import org.opengeoportal.download.types.LayerRequest;
import org.opengeoportal.download.types.LayerRequest.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
public class PerLayerDownloader implements LayerDownloader{

 private  PerLayerDownloadMethod perLayerDownloadMethod;

@Autowired
 private  DownloadPackager downloadPackager;

 final  Logger logger;


public PerLayerDownloadMethod getPerLayerDownloadMethod(){
    return perLayerDownloadMethod;
}


public void setPerLayerDownloadMethod(PerLayerDownloadMethod perLayerDownloadMethod){
    this.perLayerDownloadMethod = perLayerDownloadMethod;
}


@Override
public Boolean hasRequiredInfo(LayerRequest layer){
    return perLayerDownloadMethod.hasRequiredInfo(layer);
}


@SuppressWarnings("unchecked")
@Async
@Override
public void downloadLayers(UUID requestId,MethodLevelDownloadRequest downloadRequest){
    // downloadStatusManager.addDownloadRequestStatus(requestId, sessionId, layerRequests);
    List<LayerRequest> layerList = downloadRequest.getRequestList();
    for (LayerRequest currentLayer : layerList) {
        // this.downloadMethod.validate(currentLayer);
        // check to see if the filename exists
        try {
            logger.info("Requesting download for: " + currentLayer.getLayerNameNS());
            currentLayer.setFutureValue(this.perLayerDownloadMethod.download(currentLayer));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("An error occurred downloading this layer: " + currentLayer.getLayerNameNS());
            currentLayer.setStatus(Status.FAILED);
            continue;
        }
    }
    int successCount = 0;
    for (LayerRequest currentLayer : layerList) {
        try {
            currentLayer.getDownloadedFiles().addAll((Set<File>) currentLayer.getFutureValue().get());
            currentLayer.setStatus(Status.SUCCESS);
            successCount++;
            logger.info("finished download for: " + currentLayer.getLayerNameNS());
        } catch (Exception e) {
            logger.error("An error occurred downloading this layer: " + currentLayer.getLayerNameNS());
            currentLayer.setStatus(Status.FAILED);
        }
    }
    if (successCount > 0) {
        // this is going to try to package files each time a download method is complete.
        Future<Boolean> ready = downloadPackager.packageFiles(requestId);
        Boolean response = ready.get();
        logger.info("Packager response: " + Boolean.toString(response));
    } else {
        logger.error("No Files to package.  Download failed.");
    }
}


}