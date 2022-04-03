package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IWorkflowServiceController {

 private IWorkflowService iworkflowservice;


@GetMapping
("/startWorkflow")
public ProcessInstance startWorkflow(@RequestParam(name = "authenticatedUserId") String authenticatedUserId,@RequestParam(name = "processDefinitionKey") String processDefinitionKey,@RequestParam(name = "businessKey") String businessKey,@RequestParam(name = "variables") Map variables){
  return iworkflowservice.startWorkflow(authenticatedUserId,processDefinitionKey,businessKey,variables);
}


@GetMapping
("/findTodoTasks")
public List<WorkflowDTO> findTodoTasks(@RequestParam(name = "employeeId") String employeeId){
  return iworkflowservice.findTodoTasks(employeeId);
}


@PutMapping
("/claim")
public void claim(@RequestParam(name = "taskId") String taskId,@RequestParam(name = "userId") String userId){
iworkflowservice.claim(taskId,userId);
}


@PutMapping
("/complete")
public void complete(@RequestParam(name = "taskId") String taskId,@RequestParam(name = "variables") Map variables){
iworkflowservice.complete(taskId,variables);
}


@PutMapping
("/addUser2")
public void addUser2(@RequestParam(name = "name") String name,@RequestParam(name = "password") String password){
iworkflowservice.addUser2(name,password);
}


@PutMapping
("/addUser")
public void addUser(@RequestParam(name = "name") String name,@RequestParam(name = "password") String password,@RequestParam(name = "groupName") String groupName){
iworkflowservice.addUser(name,password,groupName);
}


@PutMapping
("/deleteUser")
public void deleteUser(@RequestParam(name = "name") String name,@RequestParam(name = "groupName") String groupName){
iworkflowservice.deleteUser(name,groupName);
}


@PutMapping
("/deleteUser2")
public void deleteUser2(@RequestParam(name = "name") String name){
iworkflowservice.deleteUser2(name);
}


@PutMapping
("/addGroup")
public void addGroup(@RequestParam(name = "id") String id,@RequestParam(name = "name") String name,@RequestParam(name = "type") String type){
iworkflowservice.addGroup(id,name,type);
}


@PutMapping
("/deleteGroup")
public void deleteGroup(@RequestParam(name = "id") String id){
iworkflowservice.deleteGroup(id);
}


@GetMapping
("/findOwnerTasks")
public List<WorkflowDTO> findOwnerTasks(@RequestParam(name = "employeeId") String employeeId){
  return iworkflowservice.findOwnerTasks(employeeId);
}


@GetMapping
("/getProcessInstanceByTaskId")
public ProcessInstance getProcessInstanceByTaskId(@RequestParam(name = "taskId") String taskId){
  return iworkflowservice.getProcessInstanceByTaskId(taskId);
}


@GetMapping
("/getProcessInstanceById")
public ProcessInstance getProcessInstanceById(@RequestParam(name = "processInstanceId") String processInstanceId){
  return iworkflowservice.getProcessInstanceById(processInstanceId);
}


}