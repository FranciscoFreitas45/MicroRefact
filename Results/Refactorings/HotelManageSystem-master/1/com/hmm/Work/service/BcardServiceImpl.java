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
import com.hmm.Work.dao.BcardDao;
import com.hmm.Work.entity.Bcard;
import com.hmm.Work.entity.BcardDTO;
import com.hmm.Work.entity.BcardEmpDTO;
import com.hmm.Work.entity.Work;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.activiti.domain.WorkflowDTO;
import com.hmm.activiti.service.IWorkflowService;
import com.hmm.employee.entity.Employee;
@Service
public class BcardServiceImpl implements com.hmm.Work.service.BcardService,BcardService{

@Autowired
 private  BcardDao bcardRepository;

@Autowired
 private  IWorkflowService workflowService;


@Override
public void save(Bcard leave){
    // TODO Auto-generated method stub
    bcardRepository.save(leave);
}


@Override
public Bcard findOne(Long id){
    // TODO Auto-generated method stub
    return bcardRepository.findById(id).get();
}


@Override
public void deleteAll(Long[] ids){
    // TODO Auto-generated method stub
    List<Long> iList = new ArrayList<>(Arrays.asList(ids));
    List<Bcard> bcards = (List<Bcard>) bcardRepository.findAllById(iList);
    bcardRepository.deleteAll(bcards);
}


public void claim(String taskId,String userId){
    workflowService.claim(taskId, userId);
}


@Override
public Page<BcardDTO> findTodoTasks(String userId,Pageable pageable){
    List<BcardDTO> results = null;
    List<WorkflowDTO> workflowLists = workflowService.findTodoTasks(userId);
    // 根据流程的业务ID查询实体并关联
    if (null != workflowLists) {
        results = new ArrayList<BcardDTO>();
        for (WorkflowDTO workflow : workflowLists) {
            Long businessKey = new Long(workflow.getBusinessKey());
            // System.out.println(businessKey);
            if (workflow.getBusinessKey() == null) {
                continue;
            }
            System.out.println(businessKey);
            Bcard leave = bcardRepository.findById(businessKey).get();
            if (leave != null) {
                BcardDTO leaveDTO = new BcardDTO();
                BeanUtils.copyProperties(leave, leaveDTO);
                BeanUtils.copyProperties(workflow, leaveDTO);
                Employee employ = leave.getEmploy();
                if (null != workflow.getAssignee()) {
                    leave.setProcessStatus(ProcessStatus.APPROVAL);
                    bcardRepository.save(leave);
                }
                leaveDTO.setEmpName(employ.getEmpName());
                leaveDTO.setEmpNo(employ.getEmpNo());
                leaveDTO.setDeptName(employ.getDepartmentes().getDeptName());
                results.add(leaveDTO);
            }
        }
    }
    return new PageImpl<BcardDTO>(results, pageable, null != results ? results.size() : 0);
}


public void complete(String taskId,Map<String,Object> variables){
    workflowService.complete(taskId, variables);
}


@Override
public void delete(Long id){
    // TODO Auto-generated method stub
    bcardRepository.deleteById(id);
}


@Override
public Page<BcardEmpDTO> findAll(Specification<Bcard> whereClause,Pageable pageable){
    // TODO Auto-generated method stub
    Page<Bcard> leaves = bcardRepository.findAll(whereClause, pageable);
    List<BcardEmpDTO> empDTOs = null;
    if (null != leaves) {
        empDTOs = new ArrayList<>();
        for (Bcard leave : leaves) {
            BcardEmpDTO empDTO = new BcardEmpDTO();
            BcardEmpDTO.entityToDto(leave, empDTO);
            Employee employ = leave.getEmploy();
            empDTO.setEmpName(employ.getEmpName());
            empDTO.setEmpNo(employ.getEmpNo());
            empDTO.setDeptName(employ.getDepartmentes().getDeptName());
            empDTOs.add(empDTO);
        }
    }
    return new PageImpl<BcardEmpDTO>(empDTOs, pageable, null != leaves ? leaves.getTotalElements() : 0);
}


@Override
public void startWorkflow(String userId,Long workId,Map<String,Object> variables){
    // 1.声明流程实例
    ProcessInstance processInstance = null;
    // 2.获取创建好的请假实例
    Bcard work = bcardRepository.findById(workId).get();
    if (work != null) {
        try {
            processInstance = workflowService.startWorkflow(userId, "attence", work.getbCardid().toString(), variables);
            work.setProcessStatus(ProcessStatus.APPROVAL);
            work.setProcessInstanceId(processInstance.getId());
            work.setApplyTime(new Date());
            bcardRepository.save(work);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


}