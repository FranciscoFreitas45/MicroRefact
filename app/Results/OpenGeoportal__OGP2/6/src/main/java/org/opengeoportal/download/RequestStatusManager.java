package org.opengeoportal.download;
 import java.util.UUID;
import org.opengeoportal.export.geocommons.GeoCommonsExportRequest;
import org.opengeoportal.proxy.controllers.ImageRequest;
public interface RequestStatusManager {


public ImageRequest getImageRequest(UUID fromString)
;

public void removeRequestsBySessionId(String sessionId)
;

public void addImageRequest(UUID requestId,String sessionId,ImageRequest imageRequest)
;

public GeoCommonsExportRequest getExportRequest(UUID requestId)
;

public void addExportRequest(UUID requestId,String sessionId,GeoCommonsExportRequest exportRequest)
;

public void addDownloadRequest(DownloadRequest downloadRequest)
;

public DownloadRequest getDownloadRequest(UUID requestId)
;

}