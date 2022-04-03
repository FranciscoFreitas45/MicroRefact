package org.opengeoportal.Interface;
public interface OgcInfoRequest {

   public OwsInfo parseResponse(InputStream inputStream);
   public String createRequest(String layerName);
   public String getMethod();
   public String getOgcProtocol();
   public Object toLowerCase(Object Object);
}