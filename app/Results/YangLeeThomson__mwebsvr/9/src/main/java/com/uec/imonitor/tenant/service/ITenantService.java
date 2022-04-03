package com.uec.imonitor.tenant.service;
 import java.util.List;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.tenant.bean.TenantEntity;
public interface ITenantService {


public TenantEntity add(TenantEntity tenant)
;

public TenantEntity findById(int id)
;

public TenantEntity findByTenantMark(String tenantMark)
;

public TenantEntity update(TenantEntity tenant)
;

public List<TenantEntity> listAll()
;

public boolean delete(Integer id)
;

}