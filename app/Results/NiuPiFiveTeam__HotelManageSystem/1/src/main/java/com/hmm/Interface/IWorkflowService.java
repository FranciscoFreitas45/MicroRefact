package com.hmm.Interface;
public interface IWorkflowService {

   public ProcessInstance startWorkflow(String authenticatedUserId,String processDefinitionKey,String businessKey,Map variables);
   public List<WorkflowDTO> findTodoTasks(String employeeId);
   public void claim(String taskId,String userId);
   public void complete(String taskId,Map variables);
}