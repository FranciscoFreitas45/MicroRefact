package com.dtdhehe.ptulife.repository;
 import com.dtdhehe.ptulife.entity.Approval;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface ApprovalRepository extends JpaRepository<Approval, String>{


@Query(value = "select t from Approval t where t.userId=?1 and t.approvalType like %?2%")
public Page<Approval> findByUserId(String userId,String approvalType,Pageable pageable)
;

@Query(value = "select t from Approval t where t.verifyEmail=?1 and t.approvalType like %?2%")
public Page<Approval> findByEmail(String email,String approvalType,Pageable pageable)
;

}