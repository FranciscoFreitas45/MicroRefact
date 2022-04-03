package org.opengeoportal.Interface;
public interface HttpRequester {

   public InputStream sendRequest(String serviceURL,String requestString,String requestMethod,String contentType);
   public String getContentType();
}