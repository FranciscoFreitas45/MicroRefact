package com.hmm.finance.financeReportDaily.activiti;
 import java.util.Date;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.finance.financeReportDaily.service.IFinanceReportDailyService;
@Service(value = "createDailyReport")
@Transactional
public class CreateDailyReport implements JavaDelegate{

@Autowired
 private  IFinanceReportDailyService financeReportDailyService;


@Override
public void execute(DelegateExecution execution){
    Date date = new Date();
    financeReportDailyService.createDailyReport(date);
}


}