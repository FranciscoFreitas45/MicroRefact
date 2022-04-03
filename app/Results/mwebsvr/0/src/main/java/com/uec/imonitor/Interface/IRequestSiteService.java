package com.uec.imonitor.Interface;
public interface IRequestSiteService {

   public List<RequestSiteEntity> findByRequestId(Integer requestId);
}