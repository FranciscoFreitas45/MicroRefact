package com.hmm.activiti.service;
 import java.util.List;
import java.util.Map;
import org.activiti.engine.runtime.ProcessInstance;
import com.hmm.activiti.domain.WorkflowDTO;
public interface IWorkflowService {


public void addUser(String name,String password,String groupName)
;

public void deleteUser2(String name)
;

public List<WorkflowDTO> findTodoTasks(String employeeId)
;

public ProcessInstance startWorkflow(String authenticatedUserId,String processDefinitionKey,String businessKey,Map variables)
;

public void addUser2(String name,String password)
;

public void editGUserMembership(String name,String password,String groupName)
;

public ProcessInstance getProcessInstanceByTaskId(String taskId)
;

public Map getTaskVariables(String taskId)
;

public void deleteUser(String name,String groupName)
;

public List<WorkflowDTO> findOwnerTasks(String employeeId)
;

public void claim(String taskId,String userId)
;

public List<WorkflowDTO> findTodoTasks2(String employeeId)
;

public ProcessInstance getProcessInstanceById(String processInstanceId)
;

public void deleteGroup(String id)
;

public void findGUser(String name,String groupName)
;

public void complete(String taskId,Map variables)
;

public void addGroup(String id,String name,String type)
;

}