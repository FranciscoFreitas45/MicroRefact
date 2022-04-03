package com.netease.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netease.domain.Check_Record;
import com.netease.repositories.Check_RecordRepository;
@Service
public class Check_RecordService {

@Autowired
 private Check_RecordRepository check_RecordRepository;


public List<Check_Record> findLoanApproval(Integer reportId){
    return check_RecordRepository.findByReportIdAndQueryReason(reportId, "贷款审批");
}


public List<Check_Record> findPersonageQuery(Integer reportId){
    return check_RecordRepository.findByReportIdAndQueryReasonStartingWith(reportId, "本人");
}


public List<Check_Record> findCreditCardApproval(Integer reportId){
    return check_RecordRepository.findByReportIdAndQueryReason(reportId, "信用卡审批");
}


public List<Check_Record> findLoanAfterManager(Integer reportId){
    return check_RecordRepository.findByReportIdAndQueryReason(reportId, "贷后管理");
}


}