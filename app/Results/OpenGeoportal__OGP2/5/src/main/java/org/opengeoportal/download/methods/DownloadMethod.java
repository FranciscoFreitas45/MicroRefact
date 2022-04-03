package org.opengeoportal.download.methods;
 import org.opengeoportal.download.types.LayerRequest;
public interface DownloadMethod {


public String createDownloadRequest()
;

public Boolean includesMetadata()
;

public void validate(LayerRequest currentLayer)
;

}