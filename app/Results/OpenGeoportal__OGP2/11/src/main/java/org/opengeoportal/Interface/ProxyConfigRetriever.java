package org.opengeoportal.Interface;
public interface ProxyConfigRetriever {

   public boolean hasProxy(String type,String repository,String accessLevel);
   public String getInternalProxyUrl(String type,String repository,String accessLevel);
}