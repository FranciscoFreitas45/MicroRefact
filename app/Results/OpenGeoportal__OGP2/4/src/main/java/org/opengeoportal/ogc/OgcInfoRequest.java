package org.opengeoportal.ogc;
 import java.io.InputStream;
public interface OgcInfoRequest {


public String getVersion()
;

public String createRequest(String layerName)
;

public String getOgcProtocol()
;

public String getMethod()
;

public OwsInfo parseResponse(InputStream inputStream)
;

}