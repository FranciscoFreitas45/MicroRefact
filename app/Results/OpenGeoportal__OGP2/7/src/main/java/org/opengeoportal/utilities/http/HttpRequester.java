package org.opengeoportal.utilities.http;
 import java.io.IOException;
import java.io.InputStream;
public interface HttpRequester {


public String getContentType()
;

public int getStatus()
;

public InputStream sendRequest(String serviceURL,String requestString,String requestMethod,String contentType)
;

public String getHeaderValue(String headerName)
;

}