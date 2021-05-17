import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.identity.Group;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.service.EmployeeService;
import com.hmm.leave.entity.Leave;
import com.hmm.leave.service.ILeaveService;
import java.util.Date;
import java.util.List;
@Component
@Transactional
public class AfterModifyApplyContentProcessor implements TaskListener{

 private  long serialVersionUID;

@Autowired
 private  ILeaveService leaveService;

@Autowired
 private  RuntimeService runtimeService;

@Autowired
 private  EmployeeService employService;

@Autowired
 private  IdentityService identityService;


public void notify(DelegateTask delegateTask){
    String processInstanceId = delegateTask.getProcessInstanceId();
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    Long id = new Long(processInstance.getBusinessKey());
    Leave leave = leaveService.findOne(id);
    String userid = leave.getUserId();
    List<Group> groupListuser = identityService.createGroupQuery().groupMember(userid).list();
    String[] groupNames = new String[groupListuser.size()];
    for (int i = 0; i < groupNames.length; i++) {
        groupNames[i] = groupListuser.get(i).getId();
    }
    String groupUserList = ArrayUtils.toString(groupNames);
    if (groupUserList.indexOf("Manager") != -1) {
        leave.setApproval("Admin");
    } else {
        String ManagerNo = leave.getEmploy().getDepartmentes().getManagerNo();
        Employee employ = employService.findByEmpNo(ManagerNo);
        leave.setApproval(employ.getEmpName());
    }
    if (delegateTask.getVariable("reApply").toString() == "true") {
        leave.setLeaveType((String) delegateTask.getVariable("leaveType"));
        leave.setStartTime((Date) delegateTask.getVariable("startTime"));
        leave.setEndTime((Date) delegateTask.getVariable("endTime"));
        leave.setReason((String) delegateTask.getVariable("reason"));
    } else {
        leave.setProcessStatus(ProcessStatus.CANCEL);
    }
    leaveService.save(leave);
}


}