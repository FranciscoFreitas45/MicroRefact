import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.activiti.util.WorkflowVariable;
import com.hmm.common.SessionUtil;
import com.hmm.common.beans.BeanUtils;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.service.EmployeeService;
import com.hmm.travel.entity.Travel;
import com.hmm.travel.entity.TravelDTO;
import com.hmm.travel.entity.TravelEmpDTO;
import com.hmm.travel.entity.TravelQueryDTO;
import com.hmm.travel.service.TravelService;
@Controller
@RestController
@RequestMapping("/travel")
public class TravelController {

@Autowired
 private  TravelService travelServiceImpl;

@Autowired
 private  EmployeeService employServiceImpl;

@Autowired
 private  IdentityService identityService;

 private  Logger logger;


@GetMapping
public Page<TravelEmpDTO> findLeaveByUserId(TravelQueryDTO leaveQueryDTO,HttpSession session,ExtjsPageRequest pageable){
    Page<TravelEmpDTO> page;
    String userId = SessionUtil.getUserName(session);
    // String groupName = SessionUtil.getGroupNames(session);
    // System.out.println(groupName);
    if (userId != null) {
        List<Group> groupList = identityService.createGroupQuery().groupMember(userId).list();
        String[] groupNames = new String[groupList.size()];
        for (int i = 0; i < groupNames.length; i++) {
            groupNames[i] = groupList.get(i).getId();
        }
        String groupUserList = ArrayUtils.toString(groupNames);
        if (groupUserList.indexOf("Manager") != -1) {
            // leaveQueryDTO.setApproval(SessionUtil.getUserName(session));
            page = travelServiceImpl.findAllQueryDTO(TravelQueryDTO.getWhereClause(leaveQueryDTO), pageable.getPageable());
        } else if (groupUserList.indexOf("Admin") != -1) {
            // leaveQueryDTO.setApproval(SessionUtil.getUserName(session));
            page = travelServiceImpl.findAllQueryDTO(TravelQueryDTO.getWhereClause(leaveQueryDTO), pageable.getPageable());
        } else {
            leaveQueryDTO.setEmpNo(employServiceImpl.findByUserName(userId).getEmpNo());
            ;
            page = travelServiceImpl.findAllQueryDTO(TravelQueryDTO.getWhereClause(leaveQueryDTO), pageable.getPageable());
        }
    } else {
        page = null;
    }
    return page;
}


@PostMapping
@ResponseBody
public ExtAjaxResponse save(HttpSession session,TravelEmpDTO dto){
    try {
        String userId = SessionUtil.getUserName(session);
        if (null != userId) {
            Travel travel = new Travel();
            TravelEmpDTO.dtoToEntity(dto, travel);
            Employee employ = employServiceImpl.findByEmpNameAndEmpNo(dto.getEmpName(), dto.getEmpNo());
            if (null != employ) {
                // System.out.println(employ.getUserName());
                travel.setEmploy(employ);
                travel.setProcessStatus(ProcessStatus.NEW);
                // travel.setApplyTime(new Date());
                // travel.setApproval(userId);
                travelServiceImpl.save(travel);
                return new ExtAjaxResponse(true, "操作成功!");
            } else {
                return new ExtAjaxResponse(true, "员工不存在!");
            }
        } else {
            return new ExtAjaxResponse(true, "未登入!");
        }
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
        return new ExtAjaxResponse(false, "失败!");
    }
}


@PostMapping("/deletes")
public ExtAjaxResponse deleteRows(Long[] ids){
    try {
        if (ids != null) {
            travelServiceImpl.deleteAll(ids);
        }
        return new ExtAjaxResponse(true, "批量删除成功！");
    } catch (Exception e) {
        return new ExtAjaxResponse(true, "批量删除失败！");
    }
}


@RequestMapping(value = "/start")
@ResponseBody
public ExtAjaxResponse start(Long travelId,HttpSession session){
    try {
        String userId = SessionUtil.getUserName(session);
        List<Group> groupListuser = identityService.createGroupQuery().groupMember(userId).list();
        String[] groupNames = new String[groupListuser.size()];
        for (int i = 0; i < groupNames.length; i++) {
            groupNames[i] = groupListuser.get(i).getId();
        }
        String groupUserList = ArrayUtils.toString(groupNames);
        System.out.println(groupUserList);
        if (groupUserList.indexOf("Manager") != -1) {
            Travel travel = travelServiceImpl.findById(travelId).get();
            Map<String, Object> variables1 = new HashMap<String, Object>();
            variables1.put("deptEmployee", travel.getEmploy().getUserName());
            variables1.put("applyUserId", userId);
            travelServiceImpl.startWorkflow(userId, travelId, variables1);
            return new ExtAjaxResponse(true, "操作成功!");
        } else if (groupUserList.indexOf("Admin") != -1) {
            Travel travel = travelServiceImpl.findById(travelId).get();
            Map<String, Object> variables1 = new HashMap<String, Object>();
            variables1.put("deptEmployee", travel.getEmploy().getUserName());
            variables1.put("applyUserId", userId);
            travelServiceImpl.startWorkflow(userId, travelId, variables1);
            return new ExtAjaxResponse(true, "操作成功!");
        } else {
            return new ExtAjaxResponse(true, "无权限!");
        }
    } catch (Exception e) {
        // TODO: handle exception
        return new ExtAjaxResponse(true, "异常错误!");
    }
}


@PutMapping(value = "{travelId}")
@ResponseBody
public ExtAjaxResponse update(Long travelId,TravelEmpDTO dto){
    try {
        Travel travel = travelServiceImpl.findById(travelId).get();
        if (null != travel) {
            BeanUtils.copyProperties(dto, travel);
            travelServiceImpl.save(travel);
            return new ExtAjaxResponse(true, "操作成功!");
        } else {
            return new ExtAjaxResponse(true, "此纪录不存在!");
        }
    } catch (Exception e) {
        e.printStackTrace();
        return new ExtAjaxResponse(false, "操作失败!");
    }
}


@RequestMapping(value = "claim/{id}")
@ResponseBody
public ExtAjaxResponse claim(String taskId,HttpSession session){
    try {
        travelServiceImpl.claim(taskId, SessionUtil.getUserName(session));
        return new ExtAjaxResponse(true, "任务签收成功!");
    } catch (Exception e) {
        e.printStackTrace();
        return new ExtAjaxResponse(false, "任务签收失败!");
    }
}


@RequestMapping(value = "/tasks")
@ResponseBody
public Page<TravelDTO> findTodoTasks(HttpSession session,ExtjsPageRequest pageable){
    Page<TravelDTO> page = new PageImpl<TravelDTO>(new ArrayList<TravelDTO>(), pageable.getPageable(), 0);
    try {
        page = travelServiceImpl.findTodoTasks(SessionUtil.getUserName(session), pageable.getPageable());
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
        travelServiceImpl.complete(taskId, variables);
        return new ExtAjaxResponse(true, "任务签收成功!");
    } catch (Exception e) {
        e.printStackTrace();
        return new ExtAjaxResponse(false, "任务签收失败!");
    }
}


@DeleteMapping(value = "{travelId}")
@ResponseBody
public ExtAjaxResponse delete(Long travelId){
    try {
        Travel travel = travelServiceImpl.findById(travelId).get();
        if (null != travel) {
            travelServiceImpl.deleteById(travelId);
            return new ExtAjaxResponse(true, "操作成功!");
        } else {
            return new ExtAjaxResponse(true, "此纪录不存在!");
        }
    } catch (Exception e) {
        e.printStackTrace();
        return new ExtAjaxResponse(false, "操作失败!");
    }
}


}