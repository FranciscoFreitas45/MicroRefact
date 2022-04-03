package org.opengeoportal.download.methods;
 import java.io.File;
import java.util.Set;
import java.util.concurrent.Future;
import org.opengeoportal.download.types.LayerRequest;
public interface MultiLayerDownloadMethod {


public String createDownloadRequest()
;

public Future<Set<File>> download(LayerRequest currentLayer)
;

public Boolean includesMetadata()
;

public Boolean hasRequiredInfo(LayerRequest layer)
;

}