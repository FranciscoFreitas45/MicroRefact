package com.uec.imonitor.request.service;
 import java.util.List;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.request.bean.RequestSiteEntity;
public interface IRequestSiteService {


public RequestSiteEntity add(RequestSiteEntity entity)
;

public RequestSiteEntity findById(int id)
;

public RequestSiteEntity update(RequestSiteEntity entity)
;

public List<RequestSiteEntity> findByRequestId(Integer requestId)
;

public boolean delete(Integer id)
;

}