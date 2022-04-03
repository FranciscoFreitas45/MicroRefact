package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.EkmAudit;
public interface EkmAuditRepository extends JpaRepository<EkmAudit, String>{


public List<EkmAudit> findByPubstatusAndDatastatusAndAuditorAndOrgi(String pubstatus,boolean datastatus,String auditor,String orgi)
;

public EkmAudit findByKnowidAndDatastatusAndVersionAndOrgi(String knowid,boolean datastatus,int version,String orgi)
;

public List<EkmAudit> findByPubstatusAndOrganAndOrgi(String pubstatus,String organ,String orgi)
;

public EkmAudit findByKnowidAndOrgi(String knowid,String orgi)
;

public List<EkmAudit> findByOrgi(String orgi)
;

public EkmAudit findByIdAndDatastatusAndOrgi(String id,boolean datastatus,String orgi)
;

public Page<EkmAudit> findAll(Specification<EkmAudit> spec,Pageable pageable)
;

public EkmAudit findByIdAndOrgi(String id,String orgi)
;

public List<EkmAudit> findByPubstatusAndAuditorAndOrgi(String pubstatus,String auditor,String orgi)
;

}