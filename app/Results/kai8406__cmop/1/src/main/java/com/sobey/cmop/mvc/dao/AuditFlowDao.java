package com.sobey.cmop.mvc.dao;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.AuditFlow;
public interface AuditFlowDao extends JpaSpecificationExecutor<AuditFlow>, PagingAndSortingRepository<AuditFlow, Integer>{


public AuditFlow findByUserIdAndFlowType(Integer userId,Integer flowType)
;

public AuditFlow findByAuditOrderAndFlowType(Integer auditOrder,Integer flowType)
;

}