package com.uec.imonitor.request.service;
 import java.util.List;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.request.bean.TenantRequestEntity;
public interface ITenantRequestService {


public TenantRequestEntity add(TenantRequestEntity request)
;

public List<TenantRequestEntity> findByTenantId(Integer tenantId)
;

public List<TenantRequestEntity> findByRequestName(String requestName)
;

public TenantRequestEntity findById(int id)
;

public TenantRequestEntity update(TenantRequestEntity request)
;

public List<TenantRequestEntity> listAll()
;

public boolean delete(Integer id)
;

}