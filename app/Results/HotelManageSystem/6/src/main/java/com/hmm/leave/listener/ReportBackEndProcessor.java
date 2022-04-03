package com.hmm.leave.listener;
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
import com.hmm.Interface.EmployeeService;
@Component
@Transactional
public class ReportBackEndProcessor implements TaskListener{

 private  long serialVersionUID;

@Autowired
 private  ILeaveService leaveService;

@Autowired
 private  RuntimeService runtimeService;

@Autowired
 private  IdentityService identityService;

@Autowired
 private  EmployeeService employService;


public void notify(DelegateTask delegateTask){
    String processInstanceId = delegateTask.getProcessInstanceId();
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    Leave leave = leaveService.findOne(new Long(processInstance.getBusinessKey()));
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
    Object realityStartTime = delegateTask.getVariable("realityStartTime");
    leave.setRealityStartTime((Date) realityStartTime);
    Object realityEndTime = delegateTask.getVariable("realityEndTime");
    leave.setRealityEndTime((Date) realityEndTime);
    leave.setProcessStatus(ProcessStatus.COMPLETE);
    leaveService.save(leave);
}


}