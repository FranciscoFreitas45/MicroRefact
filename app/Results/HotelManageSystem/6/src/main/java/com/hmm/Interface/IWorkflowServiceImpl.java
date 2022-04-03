package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.IWorkflowService;
public class IWorkflowServiceImpl implements IWorkflowService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public ProcessInstance startWorkflow(String authenticatedUserId,String processDefinitionKey,String businessKey,Map variables){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/startWorkflow"))
    .queryParam("authenticatedUserId",authenticatedUserId)
    .queryParam("processDefinitionKey",processDefinitionKey)
    .queryParam("businessKey",businessKey)
    .queryParam("variables",variables)
;  ProcessInstance aux = restTemplate.getForObject(builder.toUriString(), ProcessInstance.class);

 return aux;
}


public List<WorkflowDTO> findTodoTasks(String employeeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTodoTasks"))
    .queryParam("employeeId",employeeId)
;  List<WorkflowDTO> aux = restTemplate.getForObject(builder.toUriString(), List<WorkflowDTO>.class);

 return aux;
}


public void claim(String taskId,String userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/claim"))
    .queryParam("taskId",taskId)
    .queryParam("userId",userId)
;
  restTemplate.put(builder.toUriString(), null);
}


public void complete(String taskId,Map variables){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/complete"))
    .queryParam("taskId",taskId)
    .queryParam("variables",variables)
;
  restTemplate.put(builder.toUriString(), null);
}


}