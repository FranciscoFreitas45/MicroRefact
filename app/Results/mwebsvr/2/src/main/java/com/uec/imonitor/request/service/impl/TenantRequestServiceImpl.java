package com.uec.imonitor.request.service.impl;
 import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.request.bean.TenantRequestEntity;
import com.uec.imonitor.request.dao.ITenantRequestJPARepository;
import com.uec.imonitor.request.service.ITenantRequestService;
@Service("tenantRequestService")
@Transactional(value = "transactionManager")
public class TenantRequestServiceImpl extends BaseServiceimplements ITenantRequestService{

@Autowired
 private  ITenantRequestJPARepository tenantRequestRepository;


@Override
public TenantRequestEntity add(TenantRequestEntity request){
    if (null == request) {
        throw new RequestParamException(new String[] { "request" });
    }
    TenantRequestEntity entity = tenantRequestRepository.save(request);
    return entity;
}


@Override
public List<TenantRequestEntity> findByTenantId(Integer tenantId){
    if (null == tenantId) {
        throw new RequestParamException(new String[] { "tenantId" });
    }
    List<TenantRequestEntity> list = tenantRequestRepository.findByTenantIdAndStatus(tenantId, 0);
    return list;
}


@Override
public List<TenantRequestEntity> findByRequestName(String requestName){
    if (StringUtils.isBlank(requestName)) {
        throw new RequestParamException(new String[] { "requestName" });
    }
    List<TenantRequestEntity> list = tenantRequestRepository.findByRequestName(requestName);
    return list;
}


@Override
public TenantRequestEntity findById(int id){
    if (0 == id) {
        throw new RequestParamException(new String[] { "id" });
    }
    TenantRequestEntity entity = tenantRequestRepository.findOne(id);
    return entity;
}


@Override
public TenantRequestEntity update(TenantRequestEntity request){
    if (null == request) {
        throw new RequestParamException(new String[] { "request" });
    }
    TenantRequestEntity entity = tenantRequestRepository.save(request);
    return entity;
}


@Override
public List<TenantRequestEntity> listAll(){
    List<TenantRequestEntity> list = tenantRequestRepository.findAll();
    return list;
}


@Override
public boolean delete(Integer id){
    if (null == id) {
        throw new RequestParamException(new String[] { "id" });
    }
    tenantRequestRepository.delete(id);
    return true;
}


}