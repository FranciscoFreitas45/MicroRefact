package com.hmm.Interface;
public interface IWorkflowService {

   public ProcessInstance startWorkflow(String authenticatedUserId,String processDefinitionKey,String businessKey,Map variables);
   public List<WorkflowDTO> findTodoTasks(String employeeId);
   public List<WorkflowDTO> findOwnerTasks(String employeeId);
   public void claim(String taskId,String userId);
   public ProcessInstance getProcessInstanceByTaskId(String taskId);
   public void complete(String taskId,Map variables);
   public ProcessInstance getProcessInstanceById(String processInstanceId);
}