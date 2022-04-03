package org.opengeoportal.download.methods;
 import java.util.List;
import java.util.concurrent.Future;
import org.opengeoportal.download.types.LayerRequest;
public interface EmailDownloadMethod {


public String createDownloadRequest()
;

public Future<Boolean> sendEmail(List<LayerRequest> layerList)
;

public Boolean includesMetadata()
;

public Boolean hasRequiredInfo(LayerRequest layer)
;

}