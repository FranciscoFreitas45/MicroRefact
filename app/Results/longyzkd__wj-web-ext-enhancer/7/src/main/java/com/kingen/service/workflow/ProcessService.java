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
    // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
    identityService.setAuthenticatedUserId(vacation.getUserId().toString());
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("entity", vacation);
    // 由userTask自动分配审批权限
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
    // 最后要设置null，就是这么做，还没研究为什么
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
    // 关联业务实体
    for (HistoricProcessInstance historicProcessInstance : list) {
        // 可以这样获取业务实体
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
    // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
    this.identityService.setAuthenticatedUserId(user.getUserId().toString());
    this.taskService.claim(taskId, user.getUserId().toString());
}


public InputStream getDiagram(String processInstanceId){
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
    List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
    // 不使用spring请使用下面的两行代码
    // ProcessEngineImpl defaultProcessEngine = (ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine();
    // Context.setProcessEngineConfiguration(defaultProcessEngine.getProcessEngineConfiguration());
    // 使用spring注入引擎请使用下面的这行代码
    processEngineConfiguration = processEngine.getProcessEngineConfiguration();
    Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
    // 通过引擎生成png图片，并标记当前节点,并把当前节点用红色边框标记出来，弊端和直接部署流程文件生成的图片问题一样-乱码！。
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
    // 没什么用
    vacation.setBusinessKey(businessKey);
    // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
    identityService.setAuthenticatedUserId(vacation.getUserId().toString());
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("entity", vacation);
    // 由userTask自动分配审批权限
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
    // 最后要设置null，就是这么做，还没研究为什么
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
            // 如果有挂起的流程则continue
            continue;
        }
        // 获取当前流程下的key为entity的variable
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
    // 查询一个任务所在流程的全部评论
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
    // taskCandidateOrAssigned查询某个人的待办任务，包含已签收、候选任务<候选人范围和候选组范围>
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
    // 所有流程定义的
    ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
    Integer totalSum = processInstanceQuery.list().size();
    // int[] pageParams = PaginationThreadUtils.setPage(totalSum);
    // Pagination pagination = PaginationThreadUtils.get();
    List<ProcessInstance> list = processInstanceQuery.orderByProcessInstanceId().desc().listPage(page.getFirstResult(), page.getLimit());
    List<Map> result = Lists.newArrayList();
    if (!CollectionUtils.isEmpty(list)) {
        for (ProcessInstance pi : list) {
            // String businessKey = pi.getBusinessKey();
            // 可以这样获取业务实体
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
            // 查询流程实例
            ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(vac.getProcessInstanceId()).singleResult();
            // 只有一个任务吗？
            Task task = this.taskService.createTaskQuery().processInstanceId(vac.getProcessInstanceId()).singleResult();
            if (pi != null) {
                // 查询流程参数
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
    // 根据任务查询流程实例
    String processInstanceId = task.getProcessInstanceId();
    ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    // 评论人的id  一定要写，不然查看的时候会报错，没有用户
    this.identityService.setAuthenticatedUserId(userid);
    // 添加评论
    if (content != null) {
        this.taskService.addComment(taskId, pi.getId(), content);
    }
    // 完成任务
    this.taskService.complete(taskId, variables);
}


}