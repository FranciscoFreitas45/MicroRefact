package org.opengeoportal.download;
 import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Future;
import org.opengeoportal.download.methods.MultiLayerDownloadMethod;
import org.opengeoportal.download.types.LayerRequest;
import org.opengeoportal.download.types.LayerRequest.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
public class MultiLayerDownloader implements LayerDownloader{

 private  MultiLayerDownloadMethod multiLayerDownloadMethod;

 final  Logger logger;

 private  List<Future<Set<File>>> downloadFutures;


public void setMultiLayerDownloadMethod(MultiLayerDownloadMethod multiLayerDownloadMethod){
    this.multiLayerDownloadMethod = multiLayerDownloadMethod;
}


@Override
public Boolean hasRequiredInfo(LayerRequest layer){
    return multiLayerDownloadMethod.hasRequiredInfo(layer);
}


@Async
@Override
public void downloadLayers(UUID requestId,MethodLevelDownloadRequest request){
    List<LayerRequest> layerList = request.getRequestList();
    for (LayerRequest currentLayer : layerList) {
        // this.downloadMethod.validate(currentLayer);
        // check to see if the filename exists
        // this should fire off a callable that asynchronously calls the download method
        try {
            Future<Set<File>> currentFile = this.multiLayerDownloadMethod.download(currentLayer);
            downloadFutures.add(currentFile);
        } catch (Exception e) {
            // e.printStackTrace();
            logger.error("an error downloading this layer: " + currentLayer.getLayerInfo().getName());
            currentLayer.setStatus(Status.FAILED);
            continue;
        }
    }
    List<File> downloadedLayers = new ArrayList<File>();
    for (Future<Set<File>> currentFuture : downloadFutures) {
        downloadedLayers.addAll(currentFuture.get());
    }
}


public MultiLayerDownloadMethod getMultiLayerDownloadMethod(){
    return multiLayerDownloadMethod;
}


}