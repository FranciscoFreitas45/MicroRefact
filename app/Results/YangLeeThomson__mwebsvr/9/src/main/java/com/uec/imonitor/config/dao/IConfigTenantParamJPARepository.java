package com.uec.imonitor.config.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.uec.imonitor.config.bean.ConfigTenantParamEntity;
public interface IConfigTenantParamJPARepository extends JpaRepository<ConfigTenantParamEntity, Integer>{


@Query("from ConfigTenantParamEntity a where a.tenantId = :tenantId ")
public List<ConfigTenantParamEntity> findByTenantId(Integer tenantId)
;

@Query("from ConfigTenantParamEntity a where a.tenantId = :tenantId and a.paramName = :paramName ")
public ConfigTenantParamEntity findByTenantIdAndParamName(Integer tenantId,String paramName)
;

@Query("from ConfigTenantParamEntity a where a.tenantId = :tenantId and a.paramId = :paramId ")
public ConfigTenantParamEntity findByTenantIdAndParamId(Integer tenantId,Integer paramId)
;

}