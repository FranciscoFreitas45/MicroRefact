package com.dtdhehe.ptulife.service;
 import com.dtdhehe.ptulife.entity.Approval;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface ApprovalService {


public Approval save(Approval approval)
;

public Page<Approval> queryApprovalByUserId(String userId,String approvalType,Pageable pageable)
;

public void doRefuse(String id)
;

public void doPass(String id)
;

public Page<Approval> queryApprovalByEmail(String email,String approvalType,Pageable pageable)
;

}