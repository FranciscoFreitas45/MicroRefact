package com.uec.imonitor.tenant.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.uec.imonitor.tenant.bean.TenantEntity;
public interface ITenantJPARepository extends JpaRepository<TenantEntity, Integer>{


@Query("from TenantEntity a where a.tenantMark = :tenantMark")
public TenantEntity findByTenantMark(String tenantMark)
;

@Query("from TenantEntity a where a.tenantName = :tenantName")
public TenantEntity findByName(String tenantName)
;

}