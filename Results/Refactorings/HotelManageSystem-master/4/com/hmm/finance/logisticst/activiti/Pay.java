import java.util.Date;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.activiti.service.IWorkflowService;
import com.hmm.finance.financeReportDaily.service.IFinanceReportDailyService;
import com.hmm.finance.logisticst.domain.InStorage;
import com.hmm.finance.logisticst.repository.InStorageRepository;
import com.hmm.finance.logisticst.util.MailUtil;
@Service(value = "pay")
@Transactional
public class Pay implements TaskListener{

@Autowired
 private  RuntimeService runtimeService;

@Autowired
 private  HistoryService historyService;

@Autowired
 private  InStorageRepository inStorageRepository;


@Override
public void notify(DelegateTask delegateTask){
    // 1.先设置状态[出纳付款]审批中
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(delegateTask.getProcessInstanceId()).singleResult();
    InStorage inStorage = inStorageRepository.findById(processInstance.getBusinessKey()).get();
    // 已签收，设为[审批中]状态
    inStorage.setProcessStatus(ProcessStatus.APPROVAL);
    MailUtil mail = new MailUtil("1844365296@qq.com", delegateTask.getId());
    mail.sentEMail();
}


}