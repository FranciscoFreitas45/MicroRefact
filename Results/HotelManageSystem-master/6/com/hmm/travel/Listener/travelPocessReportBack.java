import java.util.Date;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.travel.entity.Travel;
import com.hmm.travel.service.TravelService;
@Component
@Transactional
public class travelPocessReportBack implements TaskListener{

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
    Travel travel = travelserviceimpl.findById(new Long(processInstance.getBusinessKey())).get();
    Object realityStartTime = delegateTask.getVariable("realityStartTime");
    travel.setRealityStartTime((Date) realityStartTime);
    Object realityEndTime = delegateTask.getVariable("realityEndTime");
    travel.setRealityEndTime((Date) realityEndTime);
    Object allowance = delegateTask.getVariable("allowance");
    travel.setAllowance((Float) allowance);
    travel.setProcessStatus(ProcessStatus.COMPLETE);
    travelserviceimpl.save(travel);
}


}