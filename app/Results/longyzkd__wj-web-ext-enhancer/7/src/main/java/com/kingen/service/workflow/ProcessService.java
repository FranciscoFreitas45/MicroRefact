package com.kingen.service.workflow;
 import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import com.google.common.collect.Lists;
import com.kingen.bean.User;
import com.kingen.bean.workflow.ActivitiAware;
import com.kingen.bean.workflow.BaseVO;
import com.kingen.bean.workflow.CommentVO;
import com.kingen.bean.workflow.Vacation;
import com.kingen.service.account.AccountService;
import com.kingen.service.oa.vocation.VacationService;
import com.kingen.util.ActivitiUtils;
import com.kingen.util.Page;
import com.kingen.util.PageUtil;
import com.kingen.web.workflow.Pagination;
import com.kingen.web.workflow.PaginationThreadUtils;
@Service
@Transactional
public class ProcessService {

 private  Logger logger;

@Autowired
 protected  RuntimeService runtimeService;

@Autowired
 protected  IdentityService identityService;

@Autowired
 protected  TaskService taskService;

@Autowired
 protected  RepositoryService repositoryService;

@Autowired
 protected  HistoryService historyService;

@Autowired
 protected  AccountService userService;

@Autowired
 private ProcessEngineFactoryBean processEngine;

@Autowired
 private ProcessEngineConfiguration processEngineConfiguration;

@Autowired
 protected  WorkflowTraceService traceService;

@Autowired
 private  VacationService vacationService;


public String startVacation(Vacation vacation){
    vacationService.add(vacation);
    String businessKey = vacation.getId().toString();
    vacation.setBusinessKey(businessKey);
    // ?????????????????????????????????ID???????????????????????????ID?????????activiti:initiator???
    identityService.setAuthenticatedUserId(vacation.getUserId().toString());
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("entity", vacation);
    // ???userTask????????????????????????
    // if(vacation.getDays() <= 3){
    // variables.put("auditGroup", "manager");
    // }else{
    // variables.put("auditGroup", "director");
    // }
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("com.kingen.oa.vacation", businessKey, variables);
    String processInstanceId = processInstance.getId();
    vacation.setProcessInstanceId(processInstanceId);
    this.vacationService.doUpdate(vacation);
    logger.info("processInstanceId: " + processInstanceId);
    // ???????????????null??????????????????????????????????????????
    this.identityService.setAuthenticatedUserId(null);
    return processInstanceId;
}


public Page findFinishedProcessInstances(Page page){
    // public List<BaseVO> findFinishedProcessInstances(Model model) {
    HistoricProcessInstanceQuery historQuery = historyService.createHistoricProcessInstanceQuery().finished();
    Integer totalSum = historQuery.list().size();
    // int[] pageParams = PaginationThreadUtils.setPage(totalSum);
    // Pagination pagination = PaginationThreadUtils.get();
    // List<HistoricProcessInstance> list = historQuery.orderByProcessInstanceEndTime().desc().listPage(pageParams[0], pageParams[1]);
    page.setTotal(totalSum);
    List<HistoricProcessInstance> list = historQuery.orderByProcessInstanceEndTime().desc().listPage(page.getFirstResult(), page.getLimit());
    List<BaseVO> processList = new ArrayList<BaseVO>();
    // ??????????????????
    for (HistoricProcessInstance historicProcessInstance : list) {
        // ??????????????????????????????
        // String businessKey = historicProcessInstance.getBusinessKey();
        // BaseVO leave = service.getLeave(new Long(businessKey));
        String processInstanceId = historicProcessInstance.getId();
        List<HistoricVariableInstance> listVar = this.historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
        for (HistoricVariableInstance var : listVar) {
            if ("serializable".equals(var.getVariableTypeName()) && "entity".equals(var.getVariableName())) {
                BaseVO base = (BaseVO) var.getValue();
                // base.setHistoricProcessInstance(historicProcessInstance);
                // base.setProcessDefinition(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()));
                base.setHistoricProcessInstance(ActivitiUtils.mapSetterHistoricProcessInstance(historicProcessInstance));
                base.setProcessDefinition(ActivitiUtils.mapSetter(getProcessDefinition(historicProcessInstance.getProcessDefinitionId())));
                processList.add(base);
                break;
            }
        }
    }
    // model.addAttribute("page", pagination.getPageStr());
    page.setDataList(processList);
    return page;
}


public void doClaim(User user,String taskId){
    // ?????????????????????????????????ID???????????????????????????ID?????????activiti:initiator???
    this.identityService.setAuthenticatedUserId(user.getUserId().toString());
    this.taskService.claim(taskId, user.getUserId().toString());
}


public InputStream getDiagram(String processInstanceId){
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
    List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
    // ?????????spring??????????????????????????????
    // ProcessEngineImpl defaultProcessEngine = (ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine();
    // Context.setProcessEngineConfiguration(defaultProcessEngine.getProcessEngineConfiguration());
    // ??????spring??????????????????????????????????????????
    processEngineConfiguration = processEngine.getProcessEngineConfiguration();
    Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
    // ??????????????????png??????????????????????????????,????????????????????????????????????????????????????????????????????????????????????????????????????????????-????????????
    ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
    InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds);
    return imageStream;
}


public Page<BaseVO> findFinishedTaskInstances(User user,Page<BaseVO> page){
    HistoricTaskInstanceQuery historQuery = historyService.createHistoricTaskInstanceQuery().taskAssignee(user.getUserId().toString()).finished();
    Integer totalSum = historQuery.list().size();
    // int[] pageParams = PaginationThreadUtils.setPage(totalSum);
    // Pagination pagination = PaginationThreadUtils.get();
    page.setTotal(totalSum);
    List<HistoricTaskInstance> list = historQuery.orderByHistoricTaskInstanceEndTime().desc().listPage(page.getFirstResult(), page.getLimit());
    List<BaseVO> taskList = new ArrayList<BaseVO>();
    for (HistoricTaskInstance historicTaskInstance : list) {
        String processInstanceId = historicTaskInstance.getProcessInstanceId();
        List<HistoricVariableInstance> listVar = this.historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
        for (HistoricVariableInstance var : listVar) {
            if ("serializable".equals(var.getVariableTypeName()) && "entity".equals(var.getVariableName())) {
                BaseVO base = (BaseVO) var.getValue();
                // base.setHistoricTaskInstance(historicTaskInstance);
                // base.setProcessDefinition(getProcessDefinition(historicTaskInstance.getProcessDefinitionId()));
                base.setHistoricProcessInstance(ActivitiUtils.mapSetterHistoricTaskInstance(historicTaskInstance));
                base.setProcessDefinition(ActivitiUtils.mapSetter(getProcessDefinition(historicTaskInstance.getProcessDefinitionId())));
                taskList.add(base);
                break;
            }
        }
    }
    // model.addAttribute("page", pagination.getPageStr());
    page.setDataList(taskList);
    return page;
}


public InputStream getDiagramByProDefinitionId_noTrace(String resourceType,String processDefinitionId){
    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
    String resourceName = "";
    if (resourceType.equals("png") || resourceType.equals("image")) {
        resourceName = processDefinition.getDiagramResourceName();
    } else if (resourceType.equals("xml")) {
        resourceName = processDefinition.getResourceName();
    }
    InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
    return resourceAsStream;
}


public ProcessDefinition getProcessDefinition(String processDefinitionId){
    ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
    logger.info(processDefinition.getVersion() + "");
    return processDefinition;
}


public String startOther(Vacation vacation){
    vacationService.add(vacation);
    String businessKey = vacation.getId().toString();
    // ????????????
    vacation.setBusinessKey(businessKey);
    // ?????????????????????????????????ID???????????????????????????ID?????????activiti:initiator???
    identityService.setAuthenticatedUserId(vacation.getUserId().toString());
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("entity", vacation);
    // ???userTask????????????????????????
    // if(vacation.getDays() <= 3){
    // variables.put("auditGroup", "manager");
    // }else{
    // variables.put("auditGroup", "director");
    // }
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(vacation.getBusinessType(), businessKey, variables);
    String processInstanceId = processInstance.getId();
    vacation.setProcessInstanceId(processInstanceId);
    this.vacationService.doUpdate(vacation);
    logger.info("processInstanceId: " + processInstanceId);
    // ???????????????null??????????????????????????????????????????
    this.identityService.setAuthenticatedUserId(null);
    return processInstanceId;
}


public void suspendProcessInstance(String processInstanceId){
    runtimeService.suspendProcessInstanceById(processInstanceId);
}


public void activateProcessInstance(String processInstanceId){
    runtimeService.activateProcessInstanceById(processInstanceId);
}


public List<BaseVO> getBaseVOList(List<Task> tasks){
    List<BaseVO> taskList = new ArrayList<BaseVO>();
    for (Task task : tasks) {
        String processInstanceId = task.getProcessInstanceId();
        ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
        if (processInstance == null) {
            // ???????????????????????????continue
            continue;
        }
        // ????????????????????????key???entity???variable
        BaseVO base = (BaseVO) this.runtimeService.getVariable(processInstance.getId(), "entity");
        base.setTask(ActivitiUtils.mapSetterTask(task));
        base.setProcessInstance(ActivitiUtils.mapSetterProcessInstance(processInstance));
        base.setProcessDefinition(ActivitiUtils.mapSetter(getProcessDefinition(processInstance.getProcessDefinitionId())));
        taskList.add(base);
    // 
    // Command<?> cmdTask= new GetIdentityLinksForTaskCmd(task.getId());
    // CommandContext cxt=processEngine.getProcessEngineConfiguration().getCommandContextFactory().createCommandContext(cmd);
    // Context.setCommandContext(cxt);
    // 
    // base.setTask(task);
    // 
    // base.setProcessInstance(processInstance);
    // 
    // 
    // 
    // Command<?> cmdPd= new GetIdentityLinksForProcessDefinitionCmd(processInstance.getProcessDefinitionId());
    // CommandContext cxtPd=processEngine.getProcessEngineConfiguration().getCommandContextFactory().createCommandContext(cmd);
    // Context.setCommandContext(cxt);
    // base.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
    }
    return taskList;
}


public List<CommentVO> getComments(String processInstanceId){
    // ?????????????????????????????????????????????
    List<Comment> comments = this.taskService.getProcessInstanceComments(processInstanceId);
    List<CommentVO> commnetList = new ArrayList<CommentVO>();
    for (Comment comment : comments) {
        User user = this.userService.unique((Serializable) (comment.getUserId()));
        CommentVO vo = new CommentVO();
        vo.setContent(comment.getFullMessage());
        vo.setTime(comment.getTime());
        vo.setUserName(user.getUsername());
        commnetList.add(vo);
    }
    return commnetList;
}


public Page<BaseVO> findTodoTask(User user,Page<BaseVO> page){
    // taskCandidateOrAssigned???????????????????????????????????????????????????????????????<?????????????????????????????????>
    TaskQuery taskQuery = this.taskService.createTaskQuery().taskCandidateOrAssigned(user.getUserId().toString());
    Integer totalSum = taskQuery.list().size();
    // int[] pageParams = PaginationThreadUtils.setPage(totalSum);
    // Pagination pagination = PaginationThreadUtils.get();
    // List<Task> tasks = taskQuery.orderByTaskCreateTime().desc().listPage(pageParams[0], pageParams[1]);
    page.setTotal(totalSum);
    List<Task> tasks = taskQuery.orderByTaskCreateTime().desc().listPage(page.getFirstResult(), page.getLimit());
    List<BaseVO> taskList = getBaseVOList(tasks);
    page.setDataList(taskList);
    return page;
}


public InputStream getDiagramByProInstanceId_noTrace(String resourceType,String processInstanceId){
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();
    String resourceName = "";
    if (resourceType.equals("png") || resourceType.equals("image")) {
        resourceName = processDefinition.getDiagramResourceName();
    } else if (resourceType.equals("xml")) {
        resourceName = processDefinition.getResourceName();
    }
    InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
    return resourceAsStream;
}


public Page<ProcessInstance> listRuningProcess(Page page){
    // ?????????????????????
    ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
    Integer totalSum = processInstanceQuery.list().size();
    // int[] pageParams = PaginationThreadUtils.setPage(totalSum);
    // Pagination pagination = PaginationThreadUtils.get();
    List<ProcessInstance> list = processInstanceQuery.orderByProcessInstanceId().desc().listPage(page.getFirstResult(), page.getLimit());
    List<Map> result = Lists.newArrayList();
    if (!CollectionUtils.isEmpty(list)) {
        for (ProcessInstance pi : list) {
            // String businessKey = pi.getBusinessKey();
            // ??????????????????????????????
            // String businessKey = processInstance.getBusinessKey();
            // if (businessKey == null) {
            // continue;
            // }
            // Leave leave = leaveManager.getLeave(new Long(businessKey));
            ActivitiAware a = (ActivitiAware) this.runtimeService.getVariable(pi.getId(), "entity");
            result.add(ActivitiUtils.mapSetterProcessInstanceWithEntity(pi, a));
        }
    }
    page.setTotal(totalSum);
    page.setDataList(result);
    // model.addAttribute("page", pagination.getPageStr());
    return page;
}


public List<BaseVO> listRuningVacation(User user){
    List<Vacation> listVacation = this.vacationService.findByStatus(user.getUserId(), BaseVO.PENDING);
    List<BaseVO> result = new ArrayList<BaseVO>();
    if (listVacation != null) {
        for (Vacation vac : listVacation) {
            if (vac.getProcessInstanceId() == null) {
                continue;
            }
            // ??????????????????
            ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(vac.getProcessInstanceId()).singleResult();
            // ????????????????????????
            Task task = this.taskService.createTaskQuery().processInstanceId(vac.getProcessInstanceId()).singleResult();
            if (pi != null) {
                // ??????????????????
                BaseVO base = (BaseVO) this.runtimeService.getVariable(pi.getId(), "entity");
                // base.setTask(task);
                // base.setProcessInstance(pi);
                // base.setProcessDefinition(getProcessDefinition(pi.getProcessDefinitionId()));
                // 
                base.setTask(ActivitiUtils.mapSetterTask(task));
                base.setProcessInstance(ActivitiUtils.mapSetterProcessInstance(pi));
                base.setProcessDefinition(ActivitiUtils.mapSetter(getProcessDefinition(pi.getProcessDefinitionId())));
                result.add(base);
            }
        }
    }
    return result;
}


public void complete(String taskId,String content,String userid,Map<String,Object> variables){
    Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
    // ??????????????????????????????
    String processInstanceId = task.getProcessInstanceId();
    ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    // ????????????id  ????????????????????????????????????????????????????????????
    this.identityService.setAuthenticatedUserId(userid);
    // ????????????
    if (content != null) {
        this.taskService.addComment(taskId, pi.getId(), content);
    }
    // ????????????
    this.taskService.complete(taskId, variables);
}


}