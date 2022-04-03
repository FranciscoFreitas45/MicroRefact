package com.dtdhehe.ptulife.service.impl;
 import com.dtdhehe.ptulife.entity.Approval;
import com.dtdhehe.ptulife.repository.ApprovalRepository;
import com.dtdhehe.ptulife.service.ApprovalService;
import com.dtdhehe.ptulife.util.DateUtils;
import com.dtdhehe.ptulife.util.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class ApprovalServiceImpl implements ApprovalService{

@Autowired
 private  ApprovalRepository approvalRepository;


@Override
public Approval save(Approval approval){
    if (approval == null) {
        approval = new Approval();
    }
    approval.setId(KeyUtils.getUniqueKey());
    approval.setCreateTime(DateUtils.getCurrentDateTime());
    approval.setApprovalTime(DateUtils.viewType2Date(approval.getApprovalTime()));
    approval.setStatus("0");
    return approvalRepository.save(approval);
}


@Override
public Page<Approval> queryApprovalByUserId(String userId,String approvalType,Pageable pageable){
    return approvalRepository.findByUserId(userId, approvalType, pageable);
}


@Override
public void doRefuse(String id){
    Approval approval = approvalRepository.findById(id).get();
    approval.setStatus("2");
    approvalRepository.save(approval);
}


@Override
public void doPass(String id){
    Approval approval = approvalRepository.findById(id).get();
    approval.setStatus("1");
    approvalRepository.save(approval);
}


@Override
public Page<Approval> queryApprovalByEmail(String email,String approvalType,Pageable pageable){
    return approvalRepository.findByEmail(email, approvalType, pageable);
}


}