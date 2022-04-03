package com.sobey.cmop.mvc.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.Audit;
import com.sobey.cmop.mvc.entity.AuditFlow;
public interface AuditDao extends PagingAndSortingRepository<Audit, Integer>, JpaSpecificationExecutor<Audit>{


public List<Audit> findByApplyId(Integer applyId)
;

public Audit findByApplyIdAndAuditFlowAndCreateTimeIsNull(Integer applyId,AuditFlow auditFlow)
;

public List<Audit> findByServiceTagIdAndStatus(Integer serviceTagId,Integer status)
;

public List<Audit> findByapplyIdAndStatus(Integer serviceTagId,Integer status)
;

public List<Audit> findByServiceTagId(Integer serviceTagId)
;

public Audit findByApplyIdAndStatusAndAuditFlow(Integer applyId,Integer status,AuditFlow auditFlow)
;

public Audit findByServiceTagIdAndStatusAndAuditFlow(Integer serviceTagId,Integer status,AuditFlow auditFlow)
;

public Audit findByServiceTagIdAndAuditFlowAndCreateTimeIsNull(Integer serviceTagId,AuditFlow auditFlow)
;

}