package com.dtdhehe.ptulife.repository;
 import com.dtdhehe.ptulife.entity.OrgCode;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrgCodeRepository extends JpaRepository<OrgCode, String>{


public OrgCode findByOrOrgStatus(String orgStatus)
;

}