package com.hmm.finance.logisticst.activiti;
 import java.util.Date;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.finance.logisticst.domain.InStorage;
import com.hmm.finance.logisticst.repository.InStorageRepository;
import com.hmm.finance.logisticst.util.MailUtil;
@Service(value = "confirmReceipt")
@Transactional
public class ConfirmReceipt implements TaskListener{

@Autowired
 private  RuntimeService runtimeService;

@Autowired
 private  HistoryService historyService;

@Autowired
 private  InStorageRepository inStorageRepository;


@Override
public void notify(DelegateTask delegateTask){
    // 1.先设置状态[出纳付款]审批中
    System.out.println("aa");
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(delegateTask.getProcessInstanceId()).singleResult();
    InStorage inStorage = inStorageRepository.findById(processInstance.getBusinessKey()).get();
    inStorage.setInStorageDate(new Date());
}


}