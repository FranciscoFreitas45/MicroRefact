package org.opengeoportal.download;
 import java.util.UUID;
import org.opengeoportal.download.types.LayerRequest;
public interface LayerDownloader {


public Boolean hasRequiredInfo(LayerRequest layer)
;

public void downloadLayers(UUID requestId,MethodLevelDownloadRequest request)
;

}