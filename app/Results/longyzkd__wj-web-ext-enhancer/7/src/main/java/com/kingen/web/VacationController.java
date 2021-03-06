package com.kingen.web;
 import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.alibaba.fastjson.JSONObject;
import com.kingen.bean.User;
import com.kingen.bean.workflow.BaseVO;
import com.kingen.bean.workflow.CommentVO;
import com.kingen.bean.workflow.Vacation;
import com.kingen.service.account.AccountService;
import com.kingen.service.oa.vocation.VacationService;
import com.kingen.service.workflow.ProcessService;
import com.kingen.util.ActivitiUtils;
import com.kingen.util.JsonResultBuilder;
import com.kingen.web.workflow.Pagination;
import com.kingen.web.workflow.PaginationThreadUtils;
@Controller
@RequestMapping("/vacation")
public class VacationController extends CommonController{

 private  Logger logger;

@Autowired
 private  VacationService vacationService;

@Autowired
 protected  RuntimeService runtimeService;

@Autowired
 protected  IdentityService identityService;

@Autowired
 protected  TaskService taskService;

@Autowired
 private  AccountService userService;

@Autowired
 private  ProcessService processService;


@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
public ModelAndView toAdd(Model model){
    if (!model.containsAttribute("vacation")) {
        model.addAttribute("vacation", new Vacation());
    }
    return new ModelAndView("vacation/vacation-add").addObject(model);
}


@RequestMapping(value = "/doAdd", method = RequestMethod.POST)
public void doAdd(Vacation vacation,BindingResult results,RedirectAttributes redirectAttributes,HttpSession session,HttpServletResponse response,Model model){
    User user = getCurrentUser();
    /*     ????????????    */
    vacation.setUserId(user.getUserId());
    vacation.setUser_name(user.getUsername());
    vacation.setTitle(user.getUsername() + " ???????????????");
    // ???????????????????????????
    vacation.setBusinessType(BaseVO.VACATION);
    // ?????????
    vacation.setStatus(BaseVO.PENDING);
    vacation.setApplyDate(new Date());
    JSONObject jsonObject = new JSONObject();
    try {
        String processInstanceId = this.processService.startVacation(vacation);
        // redirectAttributes.addFlashAttribute("message", "????????????????????????ID???" + processInstanceId);
        jsonObject = JsonResultBuilder.success(true).msg("????????????????????????ID???" + processInstanceId).json();
        logger.info("processInstanceId: " + processInstanceId);
    } catch (ActivitiException e) {
        if (e.getMessage().indexOf("no processes deployed with key") != -1) {
            logger.warn("??????????????????!", e);
            // redirectAttributes.addFlashAttribute("error", "???????????????????????????[?????????]->[????????????]????????????<??????????????????>-?????????");
            jsonObject = JsonResultBuilder.success(false).msg("???????????????????????????[????????????]->[??????????????????]????????????<??????>").json();
        } else {
            logger.error("???????????????????????????", e);
            jsonObject = JsonResultBuilder.success(false).msg("?????????????????????").json();
        // redirectAttributes.addFlashAttribute("error", "?????????????????????");
        }
    } catch (Exception e) {
        logger.error("???????????????????????????", e);
        // redirectAttributes.addFlashAttribute("error", "?????????????????????");
        jsonObject = JsonResultBuilder.success(false).msg("?????????????????????").json();
    }
    // return "redirect:/vacationAction/toAdd";
    writeJson(response, jsonObject);
}


@RequiresPermissions("user:vacation:details")
@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
public String details(Integer id,Model model){
    Vacation vacation = this.vacationService.unique(id);
    model.addAttribute("vacation", vacation);
    return "/vacation/details_vacation";
}


@RequiresPermissions("user:vacation:list")
@RequestMapping("/toList_page")
public String toList(HttpSession session,Model model){
    User user = getCurrentUser();
    List<Vacation> list = this.vacationService.list(user.getUserId());
    // for(Vacation v : list){
    // if(BaseVO.APPROVAL_SUCCESS.equals(v.getStatus())){
    // Vacation vacation = (Vacation)this.historyService.createHistoricVariableInstanceQuery()
    // .processInstanceId(v.getProcessInstanceId()).variableName("entity");
    // 
    // }
    // }
    Pagination pagination = PaginationThreadUtils.get();
    model.addAttribute("page", pagination.getPageStr());
    model.addAttribute("vacationList", list);
    return "vacation/list_vacation";
}


@RequestMapping("/complete/{taskId}")
@ResponseBody
public JSONObject complete(Integer vacationId,String content,Boolean completeFlag,String taskId,RedirectAttributes redirectAttributes,HttpSession session){
    User user = getCurrentUser();
    String groupType = "";
    Vacation vacation = this.vacationService.findById(vacationId);
    Vacation baseVacation = (Vacation) this.runtimeService.getVariable(vacation.getProcessInstanceId(), "entity");
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("isPass", completeFlag);
    if (completeFlag) {
        // ???userTask????????????????????????
        // variables.put("auditGroup", "hr");
        if ("hr".equals(groupType)) {
            vacation.setStatus(BaseVO.APPROVAL_SUCCESS);
        }
    } else {
        baseVacation.setTitle(baseVacation.getUser_name() + " ?????????????????????,???????????????????????????");
        vacation.setStatus(BaseVO.APPROVAL_FAILED);
        variables.put("entity", baseVacation);
    }
    this.vacationService.doUpdate(vacation);
    // ????????????
    this.processService.complete(taskId, content, user.getUserId().toString(), variables);
    JSONObject jsonObject = JsonResultBuilder.success(true).msg("?????????????????????").json();
    // redirectAttributes.addFlashAttribute("message", "?????????????????????");
    // return "redirect:/processAction/todoTaskList_page";
    return jsonObject;
}


@RequestMapping("/toApproval/{taskId}")
public String toApproval(String taskId,Model model){
    Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
    // ??????????????????????????????
    String processInstanceId = task.getProcessInstanceId();
    ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    Vacation vacation = (Vacation) this.runtimeService.getVariable(pi.getId(), "entity");
    // vacation.setTask(task);
    vacation.setTask(ActivitiUtils.mapSetterTask(task));
    vacation.setProcessInstanceId(processInstanceId);
    // ?????????????????????
    List<CommentVO> commentList = this.processService.getComments(processInstanceId);
    // userTask ??? ID
    String taskDefinitionKey = task.getTaskDefinitionKey();
    logger.info("taskDefinitionKey: " + taskDefinitionKey);
    String result = null;
    if ("modifyApply".equals(taskDefinitionKey)) {
        result = "vacation/vacation-modify";
    } else {
        result = "vacation/vacation-audit";
    }
    model.addAttribute("vacation", vacation);
    model.addAttribute("commentList", commentList);
    return result;
}


@RequestMapping(value = "/modifyVacation/{taskId}", method = RequestMethod.POST)
@ResponseBody
public JSONObject modifyVacation(Vacation vacation,BindingResult results,String taskId,String processInstanceId,Boolean reApply,RedirectAttributes redirectAttributes,HttpSession session,Model model){
    // 
    // if(results.hasErrors()){
    // model.addAttribute("vacation", vacation);
    // return "vacation/modify_vacation";
    // }
    User user = getCurrentUser();
    Map<String, Object> variables = new HashMap<String, Object>();
    vacation.setUserId(user.getUserId());
    vacation.setUser_name(user.getUsername());
    vacation.setBusinessType(BaseVO.VACATION);
    vacation.setApplyDate(new Date());
    vacation.setBusinessKey(vacation.getId().toString());
    vacation.setProcessInstanceId(processInstanceId);
    JSONObject jsonObject = new JSONObject();
    if (reApply) {
        // ??????????????????
        vacation.setTitle(user.getUsername() + " ??????????????????");
        vacation.setStatus(BaseVO.PENDING);
        // ???userTask????????????????????????
        // redirectAttributes.addFlashAttribute("message", "???????????????????????????????????????????????????");
        jsonObject = JsonResultBuilder.success(true).msg("???????????????????????????????????????????????????").json();
    } else {
        vacation.setTitle(user.getUsername() + " ???????????????????????????");
        vacation.setStatus(BaseVO.APPROVAL_FAILED);
        // redirectAttributes.addFlashAttribute("message", "??????????????????????????????????????????????????????");
        jsonObject = JsonResultBuilder.success(true).msg("??????????????????????????????????????????????????????").json();
    }
    this.vacationService.doUpdate(vacation);
    variables.put("entity", vacation);
    variables.put("reApply", reApply);
    this.processService.complete(taskId, null, user.getUserId().toString(), variables);
    // return "redirect:/processAction/todoTaskList_page";
    return jsonObject;
}


}