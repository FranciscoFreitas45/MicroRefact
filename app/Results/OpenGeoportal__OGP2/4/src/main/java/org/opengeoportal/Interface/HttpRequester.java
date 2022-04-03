package org.opengeoportal.Interface;
public interface HttpRequester {

   public InputStream sendRequest(String serviceURL,String requestString,String requestMethod,String contentType);
   public int getStatus();
   public String getContentType();
   public Object toLowerCase(Object Object);
   public String getHeaderValue(String headerName);
}