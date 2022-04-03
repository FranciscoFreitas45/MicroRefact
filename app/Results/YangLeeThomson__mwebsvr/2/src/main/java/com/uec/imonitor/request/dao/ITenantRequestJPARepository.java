package com.uec.imonitor.request.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.uec.imonitor.request.bean.TenantRequestEntity;
public interface ITenantRequestJPARepository extends JpaRepository<TenantRequestEntity, Integer>{


@Query("from TenantRequestEntity a where a.tenantId = :tenantId and isDeleted = 0")
public List<TenantRequestEntity> findByTenantId(Integer tenantId)
;

@Query("from TenantRequestEntity a where a.requestName = :requestName and isDeleted = 0")
public List<TenantRequestEntity> findByRequestName(String requestName)
;

@Query("from TenantRequestEntity a where a.tenantId = :tenantId and a.status = :status and isDeleted = 0")
public List<TenantRequestEntity> findByTenantIdAndStatus(Integer tenantId,Integer status)
;

@Query("from TenantRequestEntity a where a.requestName = :requestName and a.status = :status and isDeleted = 0")
public List<TenantRequestEntity> findByRequestNameAndStatus(String requestName,String status)
;

}