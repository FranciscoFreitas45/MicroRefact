package com.hmm.activiti.service;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.activiti.domain.WorkflowDTO;
@Service
@Transactional
public class WorkflowService implements IWorkflowService{

@Autowired
 private  IdentityService identityService;

@Autowired
 private  RuntimeService runtimeService;

@Autowired
 private  TaskService taskService;

@Autowired
 private  RepositoryService repositoryService;

@Autowired
 private  HistoryService historyService;


@Override
public void addUser(String userId,String password,String groupId){
    // TODO Auto-generated method stub
    User user = identityService.newUser(userId);
    user.setPassword(password);
    identityService.saveUser(user);
    identityService.createMembership(userId, groupId);
}


@Override
public void deleteUser2(String name){
    // TODO Auto-generated method stub
    identityService.deleteUser(name);
}


public List<WorkflowDTO> findTodoTasks(String employeeId){
    List<WorkflowDTO> results = null;
    // 根据act_ru_identitylink中对应的用户 查找act_ru_task表中对应的任务
    TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(employeeId);
    List<Task> tasks = taskQuery.list();
    if (tasks != null) {
        results = new ArrayList<WorkflowDTO>();
        for (Task task : tasks) {
            String processInstanceId = task.getProcessInstanceId();
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
            // 获得入库申请的id
            String businessKey = processInstance.getBusinessKey();
            if (businessKey == null) {
                continue;
            }
            WorkflowDTO dto = new WorkflowDTO();
            dto.setProcessInstanceId(processInstanceId);
            dto.setBusinessKey(processInstance.getBusinessKey());
            dto.setTaskId(task.getId());
            dto.setTaskName(task.getName());
            dto.setTaskCreateTime(task.getCreateTime());
            dto.setAssignee(task.getAssignee());
            dto.setTaskDefinitionKey(task.getTaskDefinitionKey());
            dto.setSuspended(processInstance.isSuspended());
            ProcessDefinition processDefinition = getProcessDefinition(processInstance.getProcessDefinitionId());
            dto.setProcessDefinitionId(processDefinition.getId());
            dto.setVersion(processDefinition.getVersion());
            results.add(dto);
        }
    }
    return results;
}


@SuppressWarnings("unchecked")
@Override
public ProcessInstance startWorkflow(String authenticatedUserId,String processDefinitionKey,String businessKey,Map variables){
    // 1.声明流程实例
    ProcessInstance processInstance = null;
    try {
        // 2.授权
        identityService.setAuthenticatedUserId(authenticatedUserId);
        // 3.启动流程实例:processDefinitionKey, businessKey, variables
        processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // 4.取消授权
        identityService.setAuthenticatedUserId(null);
    }
    return processInstance;
}


@Override
public void editGUserMembership(String name,String password,String groupName){
// TODO Auto-generated method stub
}


@Override
public void addUser2(String name,String password){
    // TODO Auto-generated method stub
    User user = identityService.newUser(name);
    user.setPassword(password);
    identityService.saveUser(user);
}


public ProcessInstance getProcessInstanceByTaskId(String taskId){
    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
    return processInstance;
}


public ProcessDefinition getProcessDefinition(String processDefinitionId){
    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
    return processDefinition;
}


public Map getTaskVariables(String taskId){
    Map<String, Object> variables = taskService.getVariables(taskId);
    return variables;
}


@Override
public void deleteUser(String name,String groupName){
    // TODO Auto-generated method stub
    identityService.deleteUser(name);
    identityService.deleteMembership(name, groupName);
}


public List<WorkflowDTO> findOwnerTasks(String employeeId){
    List<WorkflowDTO> results = null;
    // 根据用户id查询其所有用户实例
    ProcessInstanceQuery instanceQuery = runtimeService.createProcessInstanceQuery().startedBy(employeeId);
    List<ProcessInstance> processInstances = instanceQuery.list();
    if (processInstances != null) {
        results = new ArrayList<WorkflowDTO>();
        for (ProcessInstance processInstance : processInstances) {
            String processInstanceId = processInstance.getProcessInstanceId();
            // 获得入库申请的id
            String businessKey = processInstance.getBusinessKey();
            if (businessKey == null) {
                continue;
            }
            WorkflowDTO dto = new WorkflowDTO();
            // 为了生成[流程跟踪图]
            dto.setProcessInstanceId(processInstanceId);
            // 根据id在service获取入库类
            dto.setBusinessKey(processInstance.getBusinessKey());
            // 如果查找结果为null，则该流程实例已经走完，如果不为空，则查出来的activity 就是流程实例的下一环节。
            // List<HistoricActivityInstance> task = historyService.createHistoricActivityInstanceQuery()
            // .processInstanceId(processInstanceId)
            // .unfinished().list();
            HistoricTaskInstance task = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).unfinished().singleResult();
            // for(HistoricTaskInstance a : task){
            // System.out.println(a.getName());
            // }
            // dto.setTaskId(task.getId());
            // 显示[审批状态]
            dto.setTaskName(task.getName());
            // dto.setTaskCreateTime(task.getCreateTime());
            // 判断操作的按钮
            dto.setAssignee(task.getAssignee());
            // dto.setTaskDefinitionKey(task.getTaskDefinitionKey());
            // dto.setSuspended(processInstance.isSuspended());
            // ProcessDefinition processDefinition = getProcessDefinition(processInstance.getProcessDefinitionId());
            // dto.setProcessDefinitionId(processDefinition.getId());
            // dto.setVersion(processDefinition.getVersion());
            results.add(dto);
        }
    }
    return results;
}


public void claim(String taskId,String userId){
    taskService.claim(taskId, userId);
}


public List<WorkflowDTO> findTodoTasks2(String employeeId){
    List<WorkflowDTO> results = null;
    // 根据act_ru_identitylink中对应的用户 查找act_ru_task表中对应的任务
    TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(employeeId);
    List<Task> tasks = taskQuery.list();
    if (tasks != null) {
        results = new ArrayList<WorkflowDTO>();
        for (Task task : tasks) {
            String processInstanceId = task.getProcessDefinitionId();
            List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().processDefinitionId(processInstanceId).active().list();
            for (ProcessInstance processInstance : processInstances) {
                // 获得入库申请的id
                String businessKey = processInstance.getBusinessKey();
                if (businessKey == null) {
                    continue;
                }
                WorkflowDTO dto = new WorkflowDTO();
                dto.setProcessInstanceId(processInstanceId);
                dto.setBusinessKey(processInstance.getBusinessKey());
                dto.setTaskId(task.getId());
                dto.setTaskName(task.getName());
                dto.setTaskCreateTime(task.getCreateTime());
                dto.setAssignee(task.getAssignee());
                dto.setTaskDefinitionKey(task.getTaskDefinitionKey());
                dto.setSuspended(processInstance.isSuspended());
                ProcessDefinition processDefinition = getProcessDefinition(processInstance.getProcessDefinitionId());
                dto.setProcessDefinitionId(processDefinition.getId());
                dto.setVersion(processDefinition.getVersion());
                results.add(dto);
            }
        }
    }
    return results;
}


@Override
public void findGUser(String name,String groupName){
// TODO Auto-generated method stub
}


@Override
public void deleteGroup(String id){
    // TODO Auto-generated method stub
    identityService.deleteGroup(id);
}


public ProcessInstance getProcessInstanceById(String processInstanceId){
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    return processInstance;
}


@Override
public void complete(String taskId,Map variables){
    taskService.complete(taskId, variables);
}


@Override
public void addGroup(String id,String name,String type){
    // TODO Auto-generated method stub
    Group group = identityService.newGroup(id);
    group.setName(name);
    group.setType(type);
    identityService.saveGroup(group);
}


}