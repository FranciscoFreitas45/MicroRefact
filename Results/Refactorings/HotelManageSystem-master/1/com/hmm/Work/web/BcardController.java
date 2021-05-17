import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hmm.Work.entity.Bcard;
import com.hmm.Work.entity.BcardDTO;
import com.hmm.Work.entity.BcardEmpDTO;
import com.hmm.Work.entity.BcardQueryDTO;
import com.hmm.Work.service.BcardService;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.activiti.util.WorkflowVariable;
import com.hmm.common.SessionUtil;
import com.hmm.common.beans.BeanUtils;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.service.EmployeeService;
import com.hmm.leave.entity.Leave;
import com.hmm.leave.entity.LeaveDTO;
import com.hmm.leave.entity.LeaveEmpDTO;
import com.hmm.leave.entity.LeaveQueryDTO;
import com.hmm.leave.service.ILeaveService;
@RestController
@RequestMapping(value = "/bcard")
public class BcardController {

@Autowired
 private  BcardService bcardServiceImpl;

@Autowired
 private  EmployeeService employServiceimpl;

@Autowired
 private  IdentityService identityService;


@GetMapping
public Page<BcardEmpDTO> findLeaveByUserId(BcardQueryDTO leaveQueryDTO,HttpSession session,ExtjsPageRequest pageable){
    Page<BcardEmpDTO> page = null;
    String userId = SessionUtil.getUserName(session);
    if (null != userId) {
        List<Group> groupListuser = identityService.createGroupQuery().groupMember(userId).list();
        String[] groupNames = new String[groupListuser.size()];
        for (int i = 0; i < groupNames.length; i++) {
            groupNames[i] = groupListuser.get(i).getId();
        }
        String groupUserList = ArrayUtils.toString(groupNames);
        if (groupUserList.indexOf("Manager") != -1 || groupUserList.indexOf("Admin") != -1) {
            return page = bcardServiceImpl.findAll(BcardQueryDTO.getWhereClause(leaveQueryDTO), pageable.getPageable());
        } else {
            leaveQueryDTO.setUserName(userId);
            return page = bcardServiceImpl.findAll(BcardQueryDTO.getWhereClause(leaveQueryDTO), pageable.getPageable());
        }
    } else {
        return null;
    }
}


@PostMapping
@ResponseBody
public ExtAjaxResponse save(HttpSession session,BcardEmpDTO dto){
    try {
        String userId = SessionUtil.getUserName(session);
        if (userId != null) {
            Bcard leave = new Bcard();
            BcardEmpDTO.dtoToEntity(dto, leave);
            Employee employ = employServiceimpl.findByUserName(userId);
            leave.setEmploy(employ);
            leave.setUserId(userId);
            leave.setProcessStatus(ProcessStatus.NEW);
            bcardServiceImpl.save(leave);
        }
        return new ExtAjaxResponse(true, "操作成功!");
    } catch (Exception e) {
        e.printStackTrace();
        return new ExtAjaxResponse(false, "操作失败!");
    }
}


@PostMapping("/deletes")
public ExtAjaxResponse deleteRows(Long[] ids){
    try {
        if (ids != null) {
            bcardServiceImpl.deleteAll(ids);
        }
        return new ExtAjaxResponse(true, "批量删除成功！");
    } catch (Exception e) {
        return new ExtAjaxResponse(true, "批量删除失败！");
    }
}


@RequestMapping(value = "/start")
@ResponseBody
public ExtAjaxResponse start(Long leaveId,HttpSession session){
    try {
        String userId = SessionUtil.getUserName(session);
        List<Group> groupListuser = identityService.createGroupQuery().groupMember(userId).list();
        String[] groupNames = new String[groupListuser.size()];
        for (int i = 0; i < groupNames.length; i++) {
            groupNames[i] = groupListuser.get(i).getId();
        }
        String groupUserList = ArrayUtils.toString(groupNames);
        // System.out.println(groupUserList);
        if (groupUserList.indexOf("Manager") != -1) {
            Map<String, Object> variables1 = new HashMap<String, Object>();
            variables1.put("deptLeader", "Admin");
            variables1.put("applyUserId", userId);
            bcardServiceImpl.startWorkflow(userId, leaveId, variables1);
            return new ExtAjaxResponse(true, "操作成功!");
        } else {
            Employee employ = employServiceimpl.findByUserName(userId);
            String managerNo = employ.getDepartmentes().getManagerNo();
            if (null != managerNo) {
                String ManagerUserId = employServiceimpl.findByEmpNo(managerNo).getUserName();
                List<Group> groupList = identityService.createGroupQuery().groupMember(ManagerUserId).list();
                Map<String, Object> variables = new HashMap<String, Object>();
                for (Group group : groupList) {
                    String groupName = group.getId();
                    if (groupName.indexOf("Manager") != -1) {
                        variables.put("deptLeader", groupName);
                        variables.put("applyUserId", userId);
                        bcardServiceImpl.startWorkflow(userId, leaveId, variables);
                    }
                }
                return new ExtAjaxResponse(true, "操作成功!");
            } else {
                return new ExtAjaxResponse(true, "错误!");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return new ExtAjaxResponse(false, "操作失败!");
    }
}


@PutMapping(value = "{bCardid}")
@ResponseBody
public ExtAjaxResponse update(Long id,BcardEmpDTO dto){
    try {
        Bcard leave2 = bcardServiceImpl.findOne(id);
        if (leave2 != null) {
            // 使用自定义的BeanUtils
            BeanUtils.copyProperties(dto, leave2);
            bcardServiceImpl.save(leave2);
        }
        return new ExtAjaxResponse(true, "操作成功!");
    } catch (Exception e) {
        e.printStackTrace();
        return new ExtAjaxResponse(false, "操作失败!");
    }
}


@RequestMapping(value = "claim/{id}")
@ResponseBody
public ExtAjaxResponse claim(String taskId,HttpSession session){
    try {
        bcardServiceImpl.claim(taskId, SessionUtil.getUserName(session));
        return new ExtAjaxResponse(true, "任务签收成功!");
    } catch (Exception e) {
        e.printStackTrace();
        return new ExtAjaxResponse(false, "任务签收失败!");
    }
}


@RequestMapping(value = "/tasks")
@ResponseBody
public Page<BcardDTO> findTodoTasks(HttpSession session,ExtjsPageRequest pageable){
    Page<BcardDTO> page = new PageImpl<BcardDTO>(new ArrayList<BcardDTO>(), pageable.getPageable(), 0);
    try {
        page = bcardServiceImpl.findTodoTasks(SessionUtil.getUserName(session), pageable.getPageable());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return page;
}


@RequestMapping(value = "complete/{id}")
@ResponseBody
public ExtAjaxResponse complete(String taskId,WorkflowVariable var){
    try {
        Map<String, Object> variables = var.getVariableMap();
        bcardServiceImpl.complete(taskId, variables);
        return new ExtAjaxResponse(true, "任务签收成功!");
    } catch (Exception e) {
        e.printStackTrace();
        return new ExtAjaxResponse(false, "任务签收失败!");
    }
}


@DeleteMapping(value = "{bCardid}")
@ResponseBody
public ExtAjaxResponse delete(Long id){
    try {
        Bcard leave = bcardServiceImpl.findOne(id);
        if (leave != null) {
            bcardServiceImpl.delete(id);
        }
        return new ExtAjaxResponse(true, "操作成功!");
    } catch (Exception e) {
        e.printStackTrace();
        return new ExtAjaxResponse(false, "操作失败!");
    }
}


}