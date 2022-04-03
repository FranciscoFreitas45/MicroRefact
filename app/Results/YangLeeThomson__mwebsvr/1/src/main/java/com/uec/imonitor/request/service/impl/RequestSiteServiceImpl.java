package com.uec.imonitor.request.service.impl;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.request.bean.RequestSiteEntity;
import com.uec.imonitor.request.dao.IRequestSiteJPARepository;
import com.uec.imonitor.request.service.IRequestSiteService;
@Service("requestSiteService")
@Transactional(value = "transactionManager")
public class RequestSiteServiceImpl extends BaseServiceimplements IRequestSiteService{

@Autowired
 private  IRequestSiteJPARepository requestSiteRepository;


@Override
public RequestSiteEntity add(RequestSiteEntity request){
    if (null == request) {
        throw new RequestParamException(new String[] { "request" });
    }
    RequestSiteEntity entity = requestSiteRepository.save(request);
    return entity;
}


@Override
public RequestSiteEntity findById(int id){
    if (0 == id) {
        throw new RequestParamException(new String[] { "id" });
    }
    RequestSiteEntity entity = requestSiteRepository.findOne(id);
    return entity;
}


@Override
public RequestSiteEntity update(RequestSiteEntity request){
    if (null == request) {
        throw new RequestParamException(new String[] { "request" });
    }
    RequestSiteEntity entity = requestSiteRepository.save(request);
    return entity;
}


@Override
public List<RequestSiteEntity> findByRequestId(Integer requestId){
    if (null == requestId) {
        throw new RequestParamException(new String[] { "requestId" });
    }
    List<RequestSiteEntity> list = requestSiteRepository.findByRequestId(requestId);
    return list;
}


@Override
public boolean delete(Integer id){
    if (null == id) {
        throw new RequestParamException(new String[] { "id" });
    }
    requestSiteRepository.delete(id);
    return true;
}


}