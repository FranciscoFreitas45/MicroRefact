package com.hmm.Work.listener;
 import java.util.Date;
import javax.transaction.Transactional;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hmm.Work.entity.Bcard;
import com.hmm.Work.service.BcardService;
import com.hmm.Work.service.BcardServiceImpl;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.travel.entity.Travel;
@Component(value = "afterAttenceModifyApplyContentProcessor")
@Transactional
public class AfterAttenceModifyApplyContentProcessor implements TaskListener{

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
    Long id = new Long(processInstance.getBusinessKey());
    Bcard bcard = bcardServiceImpl.findOne(id);
    // String userid = travel.getEmploy().getUserName();
    if (delegateTask.getVariable("attencereApply").toString() == "true") {
        bcard.setCalendar((String) delegateTask.getVariable("calendar"));
        bcard.setOntudytime((Date) delegateTask.getVariable("ontudytime"));
        bcard.setOffdutytime((Date) delegateTask.getVariable("offdutytime"));
        bcard.setWorkDate((Date) delegateTask.getVariable("workDate"));
        bcard.setReason((String) delegateTask.getVariable("reason"));
        bcard.setProcessStatus(ProcessStatus.APPROVAL);
    } else {
        bcard.setProcessStatus(ProcessStatus.CANCEL);
    }
    bcardServiceImpl.save(bcard);
}


}