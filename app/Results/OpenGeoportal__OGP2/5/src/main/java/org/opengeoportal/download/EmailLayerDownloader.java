package org.opengeoportal.download;
 import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import org.opengeoportal.download.methods.EmailDownloadMethod;
import org.opengeoportal.download.types.LayerRequest;
import org.opengeoportal.download.types.LayerRequest.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.opengeoportal.Interface.RequestStatusManager;
public class EmailLayerDownloader implements LayerDownloader{

 private  EmailDownloadMethod emailDownloadMethod;

@Autowired
 private  RequestStatusManager requestStatusManager;

 final  Logger logger;


public void setEmailDownloadMethod(EmailDownloadMethod emailDownloadMethod){
    this.emailDownloadMethod = emailDownloadMethod;
}


public EmailDownloadMethod getEmailDownloadMethod(){
    return emailDownloadMethod;
}


@Override
public Boolean hasRequiredInfo(LayerRequest layer){
    return this.emailDownloadMethod.hasRequiredInfo(layer);
}


@Async
@Override
public void downloadLayers(UUID requestId,MethodLevelDownloadRequest request){
    List<LayerRequest> layerList = request.getRequestList();
    for (LayerRequest layer : layerList) {
        layer.setShouldHaveFiles(false);
    }
    // check to see if the filename exists
    // this should fire off a callable that asynchronously calls the download method
    logger.debug("Trying to send email...");
    Future<?> emailFuture = this.emailDownloadMethod.sendEmail(layerList);
    Boolean emailSent = (Boolean) emailFuture.get();
    for (LayerRequest currentLayer : layerList) {
        if (emailSent) {
            currentLayer.setStatus(Status.SUCCESS);
        } else {
            currentLayer.setStatus(Status.FAILED);
        }
    }
    if (emailSent) {
        DownloadRequest downloadRequest = requestStatusManager.getDownloadRequest(requestId);
        downloadRequest.setEmailSent(emailSent);
        logger.info("Email requested.");
    } else {
        logger.error("Error requesting Email");
    }
}


}