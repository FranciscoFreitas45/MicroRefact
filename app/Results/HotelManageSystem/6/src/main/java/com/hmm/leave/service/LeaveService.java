package com.hmm.leave.service;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.activiti.domain.WorkflowDTO;
import com.hmm.activiti.service.IWorkflowService;
import com.hmm.employee.entity.Employee;
import com.hmm.leave.dao.LeaveRepository;
import com.hmm.leave.entity.Leave;
import com.hmm.leave.entity.LeaveDTO;
import com.hmm.leave.entity.LeaveEmpDTO;
import com.hmm.Interface.IWorkflowService;
import com.hmm.DTO.Employee;
@Service
@Transactional
public class LeaveService implements ILeaveService{

@Autowired
 private  LeaveRepository leaveRepository;

@Autowired
 private  IWorkflowService workflowService;


@Override
public void save(Leave leave){
    leaveRepository.save(leave);
}


@Override
@Transactional(readOnly = true)
public Leave findOne(Long id){
    return leaveRepository.findById(id).get();
}


@Override
public void deleteAll(Long[] ids){
    // TODO Auto-generated method stub
    List<Long> iList = new ArrayList<>(Arrays.asList(ids));
    List<Leave> lists = (List<Leave>) leaveRepository.findAllById(iList);
    if (lists != null) {
        leaveRepository.deleteAll(lists);
    }
}


@Override
public List<Map<Object,Object>> findByyearAndOntudytimeleave(Integer year,String userName){
    // TODO Auto-generated method stub
    return leaveRepository.findByyearAndOntudytimeleave(year, userName);
}


@Override
public Page<LeaveDTO> findTodoTasks(String userId,Pageable pageable){
    List<LeaveDTO> results = null;
    List<WorkflowDTO> workflowLists = workflowService.findTodoTasks(userId);
    // 根据流程的业务ID查询实体并关联
    if (null != workflowLists) {
        results = new ArrayList<LeaveDTO>();
        for (WorkflowDTO workflow : workflowLists) {
            Long businessKey = new Long(workflow.getBusinessKey());
            // System.out.println(businessKey);
            if (workflow.getBusinessKey() == null) {
                continue;
            }
            Leave leave = leaveRepository.findById(businessKey).get();
            if (leave != null) {
                LeaveDTO leaveDTO = new LeaveDTO();
                BeanUtils.copyProperties(leave, leaveDTO);
                BeanUtils.copyProperties(workflow, leaveDTO);
                Employee employ = leave.getEmploy();
                if (null != workflow.getAssignee()) {
                    leave.setProcessStatus(ProcessStatus.APPROVAL);
                    leaveRepository.save(leave);
                }
                leaveDTO.setEmpName(employ.getEmpName());
                leaveDTO.setEmpNo(employ.getEmpNo());
                leaveDTO.setDeptName(employ.getDepartmentes().getDeptName());
                results.add(leaveDTO);
            }
        }
    }
    return new PageImpl<LeaveDTO>(results, pageable, null != results ? results.size() : 0);
}


@Override
public void delete(Long id){
    Leave leave = leaveRepository.findById(id).get();
    if (leave != null) {
        leaveRepository.delete(leave);
    }
}


@Override
public Page<LeaveEmpDTO> findAll(Specification<Leave> whereClause,Pageable pageable){
    // TODO Auto-generated method stub
    Page<Leave> leaves = leaveRepository.findAll(whereClause, pageable);
    List<LeaveEmpDTO> empDTOs = null;
    if (null != leaves) {
        empDTOs = new ArrayList<>();
        for (Leave leave : leaves) {
            LeaveEmpDTO empDTO = new LeaveEmpDTO();
            LeaveEmpDTO.entityToDto(leave, empDTO);
            Employee employ = leave.getEmploy();
            empDTO.setEmpName(employ.getEmpName());
            empDTO.setEmpNo(employ.getEmpNo());
            empDTO.setDeptName(employ.getDepartmentes().getDeptName());
            empDTOs.add(empDTO);
        }
    }
    return new PageImpl<LeaveEmpDTO>(empDTOs, pageable, null != leaves ? leaves.getTotalElements() : 0);
}


@Override
public List<Map<Object,Object>> findleave(Integer year){
    // TODO Auto-generated method stub
    return leaveRepository.findleave(year);
}


@Override
@Transactional(readOnly = true)
public Page<Leave> findLeave(String userId,Pageable pageable){
    return leaveRepository.findLeave(userId, pageable);
}


@Override
public void startWorkflow(String userId,Long leaveId,Map<String,Object> variables){
    // 1.声明流程实例
    ProcessInstance processInstance = null;
    // 2.获取创建好的请假实例
    Leave leave = leaveRepository.findById(leaveId).get();
    if (leave != null) {
        try {
            processInstance = workflowService.startWorkflow(userId, "leave", leave.getId().toString(), variables);
            leave.setProcessInstanceId(processInstance.getId());
            leave.setProcessStatus(ProcessStatus.APPROVAL);
            leave.setApplyTime(new Date());
            leaveRepository.save(leave);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


@Override
public float findTotalLeaveTimes(String userName){
    // TODO Auto-generated method stub
    Float leaveTimes = leaveRepository.findTotalLeaveTimes(userName);
    if (null != leaveTimes) {
        return leaveTimes;
    } else {
        return 0;
    }
}


public void claim(String taskId,String userId){
    workflowService.claim(taskId, userId);
}


public void complete(String taskId,Map<String,Object> variables){
    workflowService.complete(taskId, variables);
}


public int findTatalPersonLeave(){
    // TODO Auto-generated method stub
    Integer leaveTimes = leaveRepository.findTatalPersonLeave();
    if (null != leaveTimes) {
        return leaveTimes;
    } else {
        return 0;
    }
}


}