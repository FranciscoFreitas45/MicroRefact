package com.cym.Interface;
public interface UpstreamService {

   public List<Upstream> getListByProxyType(Integer proxyType);
   public List<UpstreamServer> getUpstreamServers(String id);
}