import java.util.Date;
import javax.transaction.Transactional;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.travel.entity.Travel;
import com.hmm.travel.service.TravelService;
@Component
@Transactional
public class afterEmployModifyApplyContentProcessor implements TaskListener{

 private  long serialVersionUID;

@Autowired
 private  TravelService travelserviceimpl;

@Autowired
 private  RuntimeService runtimeService;


@Override
public void notify(DelegateTask delegateTask){
    // TODO Auto-generated method stub
    String processInstanceId = delegateTask.getProcessInstanceId();
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    Long id = new Long(processInstance.getBusinessKey());
    Travel travel = travelserviceimpl.findById(id).get();
    // String userid = travel.getEmploy().getUserName();
    if (delegateTask.getVariable("LeaderModify").toString() == "true") {
        travel.setTraStartTime((Date) delegateTask.getVariable("traStartTime"));
        travel.setTraEndTime((Date) delegateTask.getVariable("traEndTime"));
        travel.setProcess((String) delegateTask.getVariable("process"));
        travel.setProcessStatus(ProcessStatus.APPROVAL);
    } else {
        travel.setProcessStatus(ProcessStatus.CANCEL);
    }
    travelserviceimpl.save(travel);
}


}