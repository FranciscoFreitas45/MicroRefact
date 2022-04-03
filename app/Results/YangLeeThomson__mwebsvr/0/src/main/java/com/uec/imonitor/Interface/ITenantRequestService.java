package com.uec.imonitor.Interface;
public interface ITenantRequestService {

   public List<TenantRequestEntity> findByTenantId(Integer tenantId);
}