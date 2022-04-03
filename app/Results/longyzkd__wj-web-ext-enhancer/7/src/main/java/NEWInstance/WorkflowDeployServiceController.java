package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WorkflowDeployServiceController {

 private WorkflowDeployService workflowdeployservice;


@GetMapping
("/deployModelWithStatus")
public Deployment deployModelWithStatus(@RequestParam(name = "modelData") Model modelData,@RequestParam(name = "modelNode") ObjectNode modelNode){
  return workflowdeployservice.deployModelWithStatus(modelData,modelNode);
}


}