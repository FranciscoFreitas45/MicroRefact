package com.netease.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netease.domain.Loan_Record_Detail;
import com.netease.enums.AccountType;
import com.netease.enums.DetailType;
import com.netease.repositories.Loan_Record_DetailRepository;
@Service
public class Loan_Record_DetailService {

@Autowired
 private Loan_Record_DetailRepository loan_Record_DetailRepository;


public List<Loan_Record_Detail> findHouserLoadOverdue(Integer reportId){
    return loan_Record_DetailRepository.findByReportIdAndAccountTypeAndDetailType(reportId, AccountType.HOUSELOAN.getValue(), DetailType.OVERDUEACCOUNT.getValue());
}


public List<Loan_Record_Detail> findCreditCardOverdue(Integer reportId){
    return loan_Record_DetailRepository.findByReportIdAndAccountTypeAndDetailType(reportId, AccountType.CREDIT.getValue(), DetailType.OVERDUEDEBIT.getValue());
}


public List<Loan_Record_Detail> findOtherLoadOverdue(Integer reportId){
    return loan_Record_DetailRepository.findByReportIdAndAccountTypeAndDetailType(reportId, AccountType.OTHERLOAN.getValue(), DetailType.OVERDUEACCOUNT.getValue());
}


public List<Loan_Record_Detail> findCreditCardNoOverdue(Integer reportId){
    return loan_Record_DetailRepository.findByReportIdAndAccountTypeAndDetailType(reportId, AccountType.CREDIT.getValue(), DetailType.NEVEROVERDUEDEBIT.getValue());
}


public List<Loan_Record_Detail> findCreditCardOverdueSixty(Integer reportId){
    return loan_Record_DetailRepository.findByReportIdAndAccountTypeAndDetailType(reportId, AccountType.CREDIT.getValue(), DetailType.OVERDUESIXTYDEBIT.getValue());
}


public List<Loan_Record_Detail> findHouserLoadNoOverdue(Integer reportId){
    return loan_Record_DetailRepository.findByReportIdAndAccountTypeAndDetailType(reportId, AccountType.HOUSELOAN.getValue(), DetailType.NEVEROVERDUEACCOUNT.getValue());
}


public List<Loan_Record_Detail> findOtherLoadNoOverdue(Integer reportId){
    return loan_Record_DetailRepository.findByReportIdAndAccountTypeAndDetailType(reportId, AccountType.OTHERLOAN.getValue(), DetailType.NEVEROVERDUEACCOUNT.getValue());
}


}