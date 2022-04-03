package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Tenant;
public interface TenantRepository extends JpaRepository<Tenant, String>{


public Tenant findById(String id)
;

public Tenant findByOrgidAndTenantname(String orgid,String tenantname)
;

public List<Tenant> findByOrgid(String orgid)
;

}