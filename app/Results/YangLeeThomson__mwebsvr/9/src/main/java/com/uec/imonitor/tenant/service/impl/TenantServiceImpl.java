package com.uec.imonitor.tenant.service.impl;
 import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.tenant.bean.TenantEntity;
import com.uec.imonitor.tenant.dao.ITenantJPARepository;
import com.uec.imonitor.tenant.service.ITenantService;
@Service("tenantService")
@Transactional(value = "transactionManager")
public class TenantServiceImpl extends BaseServiceimplements ITenantService{

@Autowired
 private  ITenantJPARepository tenantRepository;


@Override
public TenantEntity add(TenantEntity tenant){
    if (null == tenant) {
        throw new RequestParamException(new String[] { "tenant" });
    }
    TenantEntity entity = tenantRepository.save(tenant);
    return entity;
}


@Override
public TenantEntity findById(int id){
    if (0 == id) {
        throw new RequestParamException(new String[] { "id" });
    }
    TenantEntity entity = tenantRepository.findOne(id);
    return entity;
}


@Override
public TenantEntity findByTenantMark(String tenantMark){
    if (StringUtils.isBlank(tenantMark)) {
        throw new RequestParamException(new String[] { "tenantMark" });
    }
    TenantEntity entity = tenantRepository.findByTenantMark(tenantMark);
    return entity;
}


@Override
public TenantEntity update(TenantEntity tenant){
    if (null == tenant) {
        throw new RequestParamException(new String[] { "tenant" });
    }
    TenantEntity entity = tenantRepository.save(tenant);
    return entity;
}


@Override
public List<TenantEntity> listAll(){
    List<TenantEntity> list = tenantRepository.findAll();
    return list;
}


@Override
public boolean delete(Integer id){
    if (null == id) {
        throw new RequestParamException(new String[] { "id" });
    }
    tenantRepository.delete(id);
    return true;
}


}