package com.hmm.Work.listener;
 import java.util.Date;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.Work.entity.Bcard;
import com.hmm.Work.service.BcardService;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.travel.entity.Travel;
@Component(value = "reportBackAttenceEndProcessor")
@Transactional
public class ReportBackAttenceEndProcessor implements TaskListener{

 private  long serialVersionUID;

@Autowired
 private  RuntimeService runtimeService;

@Autowired
 private  BcardService bcardServiceImpl;


@Override
public void notify(DelegateTask delegateTask){
    // TODO Auto-generated method stub
    String processInstanceId = delegateTask.getProcessInstanceId();
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    Bcard bcard = bcardServiceImpl.findOne(new Long(processInstance.getBusinessKey()));
    Object realityStartTime = delegateTask.getVariable("realityStartTime");
    bcard.setRealityStartTime((Date) realityStartTime);
    Object realityEndTime = delegateTask.getVariable("realityEndTime");
    bcard.setRealityEndTime((Date) realityEndTime);
    Object allowance = delegateTask.getVariable("allowance");
    // bcard.setAllowance((Float)allowance);
    bcard.setProcessStatus(ProcessStatus.COMPLETE);
    bcardServiceImpl.save(bcard);
}


}