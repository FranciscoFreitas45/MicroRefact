package com.kingen.service.workflow;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.javax.el.ExpressionFactory;
import org.activiti.engine.impl.javax.el.ValueExpression;
import org.activiti.engine.impl.juel.ExpressionFactoryImpl;
import org.activiti.engine.impl.juel.SimpleContext;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.kingen.bean.User;
import com.kingen.service.account.AccountService;
import com.kingen.util.workflow.WorkflowUtils;
import DTO.AccountService;
import DTO.User;
@Component
public class WorkflowTraceService {

 protected  Logger logger;

@Autowired
 protected  RuntimeService runtimeService;

@Autowired
 protected  TaskService taskService;

@Autowired
 protected  RepositoryService repositoryService;

@Autowired
 protected  IdentityService identityService;

@Autowired
 private  AccountService userService;


public void setWidthAndHeight(ActivityImpl activity,Map<String,Object> activityInfo){
    activityInfo.put("width", activity.getWidth());
    activityInfo.put("height", activity.getHeight());
}


public List<Map<String,Object>> traceProcess(String processInstanceId){
    // 执行实例
    Execution execution = runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();
    Object property = PropertyUtils.getProperty(execution, "activityId");
    String activityId = "";
    if (property != null) {
        activityId = property.toString();
    }
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
    // 获得当前任务的所有节点
    List<ActivityImpl> activitiList = processDefinition.getActivities();
    List<Map<String, Object>> activityInfos = new ArrayList<Map<String, Object>>();
    for (ActivityImpl activity : activitiList) {
        boolean currentActiviti = false;
        String id = activity.getId();
        // 当前节点
        if (id.equals(activityId)) {
            currentActiviti = true;
        }
        Map<String, Object> activityImageInfo = packageSingleActivitiInfo(activity, processInstance, currentActiviti);
        activityInfos.add(activityImageInfo);
    }
    return activityInfos;
}


public String isCondition(String key,String el,String value){
    ExpressionFactory factory = new ExpressionFactoryImpl();
    SimpleContext context = new SimpleContext();
    context.setVariable(key, factory.createValueExpression(value, String.class));
    ValueExpression e = factory.createValueExpression(context, el, String.class);
    logger.info("el info key: " + key + " el: " + el + " value: " + value + " e: " + e + " e.value: " + e.getValue(context));
    return (String) e.getValue(context);
}


public void setCurrentTaskAssignee(Map<String,Object> vars,Task currentTask){
    String assignee = currentTask.getAssignee();
    logger.info("assignee: " + assignee);
    if (assignee != null) {
        User assigneeUser = this.userService.findUserByLoginName(assignee);
        if (!(assigneeUser == null)) {
            vars.put("当前处理人", assigneeUser.getUsername());
        } else {
            vars.put("当前处理人", "不存在！");
        }
        logger.info("当前处理人: " + assigneeUser);
    }
}


public Map<String,Object> packageSingleActivitiInfo(ActivityImpl activity,ProcessInstance processInstance,boolean currentActiviti){
    Map<String, Object> vars = new HashMap<String, Object>();
    Map<String, Object> activityInfo = new HashMap<String, Object>();
    activityInfo.put("currentActiviti", currentActiviti);
    setPosition(activity, activityInfo);
    setWidthAndHeight(activity, activityInfo);
    Map<String, Object> properties = activity.getProperties();
    vars.put("任务类型", WorkflowUtils.parseToZhType(properties.get("type").toString()));
    ActivityBehavior activityBehavior = activity.getActivityBehavior();
    logger.info("activityBehavior={}", activityBehavior);
    if (activityBehavior instanceof UserTaskActivityBehavior) {
        Task currentTask = null;
        /*
             * 当前节点的task
			 */
        if (currentActiviti) {
            currentTask = getCurrentTaskInfo(processInstance);
        }
        /*
			 * 当前任务的分配角色
			 */
        UserTaskActivityBehavior userTaskActivityBehavior = (UserTaskActivityBehavior) activityBehavior;
        TaskDefinition taskDefinition = userTaskActivityBehavior.getTaskDefinition();
        // 任务所属角色
        String taskDefinKey = taskDefinition.getKey();
        logger.info("taskDefinKey: " + taskDefinKey);
        if (taskDefinKey.startsWith("director")) {
            vars.put("任务所属角色", "总监组");
        } else if (taskDefinKey.startsWith("finance")) {
            vars.put("任务所属角色", "财务组");
        } else if (taskDefinKey.startsWith("hr")) {
            vars.put("任务所属角色", "人事组");
        } else if (taskDefinKey.startsWith("manager")) {
            vars.put("任务所属角色", "经理组");
        }
        // 当前处理人
        if (currentTask != null) {
            setCurrentTaskAssignee(vars, currentTask);
        }
    // Set<Expression> candidateGroupIdExpressions = taskDefinition.getCandidateGroupIdExpressions();
    // if (!candidateGroupIdExpressions.isEmpty()) {
    // 
    // // 任务的处理角色
    // setTaskGroup(vars, candidateGroupIdExpressions);
    // vars.put("任务所属角色", "-待完成-");
    // 
    // 
    // // 当前处理人
    // if (currentTask != null) {
    // setCurrentTaskAssignee(vars, currentTask);
    // }
    // }
    }
    vars.put("节点说明", properties.get("documentation"));
    logger.info("properties: " + properties);
    String description = activity.getProcessDefinition().getDescription();
    vars.put("描述", description);
    logger.info("trace variables: {}", vars);
    activityInfo.put("vars", vars);
    return activityInfo;
}


public Task getCurrentTaskInfo(ProcessInstance processInstance){
    Task currentTask = null;
    try {
        String activitiId = (String) PropertyUtils.getProperty(processInstance, "activityId");
        logger.info("current activity id: {}", activitiId);
        currentTask = taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey(activitiId).singleResult();
        logger.info("current task for processInstance: {}", ToStringBuilder.reflectionToString(currentTask));
    } catch (Exception e) {
        logger.error("can not get property activityId from processInstance: {}", processInstance);
    }
    return currentTask;
}


public void setPosition(ActivityImpl activity,Map<String,Object> activityInfo){
    activityInfo.put("x", activity.getX());
    activityInfo.put("y", activity.getY());
}


}