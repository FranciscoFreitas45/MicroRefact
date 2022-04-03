package org.opengeoportal.download;
 import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.opengeoportal.export.geocommons.GeoCommonsExportRequest;
import org.opengeoportal.proxy.controllers.ImageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class RequestStatusManagerImpl implements RequestStatusManager{

 private List<DownloadRequest> globalDownloadRequestRegistry;

 private List<ImageRequest> globalImageRequestRegistry;

 private List<GeoCommonsExportRequest> globalExportRequestRegistry;

 final  Logger logger;


@Override
public ImageRequest getImageRequest(UUID requestId){
    for (ImageRequest status : globalImageRequestRegistry) {
        if (status.getRequestId().equals(requestId)) {
            return status;
        }
    }
    // logger.info("No status found for image request.");
    return null;
}


public List<DownloadRequest> getDownloadRequestBySessionId(String sessionId){
    List<DownloadRequest> sessionStatus = new ArrayList<DownloadRequest>();
    for (DownloadRequest status : globalDownloadRequestRegistry) {
        if (status.getSessionId().equals(sessionId)) {
            sessionStatus.add(status);
        }
    }
    return sessionStatus;
}


public List<ImageRequest> getImageRequestBySessionId(String sessionId){
    List<ImageRequest> sessionStatus = new ArrayList<ImageRequest>();
    for (ImageRequest status : globalImageRequestRegistry) {
        if (status.getSessionId().equals(sessionId)) {
            sessionStatus.add(status);
        }
    }
    return sessionStatus;
}


@Override
public void removeRequestsBySessionId(String sessionId){
    removeImageRequestsBySessionId(sessionId);
    removeDownloadRequestsBySessionId(sessionId);
    removeExportRequestsBySessionId(sessionId);
}


@Override
public void addImageRequest(UUID requestId,String sessionId,ImageRequest imageRequest){
    logger.info("Adding image request status object...");
    imageRequest.setRequestId(requestId);
    imageRequest.setSessionId(sessionId);
    globalImageRequestRegistry.add(imageRequest);
}


@Override
public GeoCommonsExportRequest getExportRequest(UUID requestId){
    for (GeoCommonsExportRequest status : globalExportRequestRegistry) {
        if (status.getRequestId().equals(requestId)) {
            return status;
        }
    }
    // logger.info("No status found for image request.");
    return null;
}


@Override
public DownloadRequest getDownloadRequest(UUID requestId){
    for (DownloadRequest status : globalDownloadRequestRegistry) {
        if (status.getRequestId().equals(requestId)) {
            return status;
        }
    }
    // logger.info("No status found for download request");
    return null;
}


@Override
public void addDownloadRequest(DownloadRequest downloadRequest){
    logger.info("Adding download request status object...");
    globalDownloadRequestRegistry.add(downloadRequest);
}


public void removeDownloadRequestsBySessionId(String sessionId){
    List<DownloadRequest> sessionStatus = getDownloadRequestBySessionId(sessionId);
    if (!sessionStatus.isEmpty()) {
        globalDownloadRequestRegistry.removeAll(sessionStatus);
    } else {
        logger.debug("No download status objects found for this session: " + sessionId);
    }
}


public List<GeoCommonsExportRequest> getExportRequestBySessionId(String sessionId){
    List<GeoCommonsExportRequest> sessionStatus = new ArrayList<GeoCommonsExportRequest>();
    for (GeoCommonsExportRequest status : globalExportRequestRegistry) {
        if (status.getSessionId().equals(sessionId)) {
            sessionStatus.add(status);
        }
    }
    return sessionStatus;
}


public void removeExportRequestsBySessionId(String sessionId){
    List<GeoCommonsExportRequest> sessionStatus = getExportRequestBySessionId(sessionId);
    if (!sessionStatus.isEmpty()) {
        globalExportRequestRegistry.removeAll(sessionStatus);
    } else {
        logger.debug("No export status objects found for this session: " + sessionId);
    }
}


public void removeImageRequestsBySessionId(String sessionId){
    List<ImageRequest> sessionStatus = getImageRequestBySessionId(sessionId);
    if (!sessionStatus.isEmpty()) {
        globalImageRequestRegistry.removeAll(sessionStatus);
    } else {
        logger.debug("No image status objects found for this session: " + sessionId);
    }
}


@Override
public void addExportRequest(UUID requestId,String sessionId,GeoCommonsExportRequest exportRequest){
    logger.info("Adding export request status object...");
    exportRequest.setRequestId(requestId);
    exportRequest.setSessionId(sessionId);
    globalExportRequestRegistry.add(exportRequest);
}


}