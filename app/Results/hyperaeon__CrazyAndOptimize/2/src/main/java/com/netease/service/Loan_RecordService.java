package com.netease.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netease.domain.Loan_Record;
import com.netease.repositories.Loan_RecordRepository;
@Service
public class Loan_RecordService {

@Autowired
 private Loan_RecordRepository loan_RecordRepository;


public List<Loan_Record> findByReportId(Integer reportId){
    return loan_RecordRepository.findByReportId(reportId);
}


}