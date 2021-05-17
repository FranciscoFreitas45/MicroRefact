import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
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


}