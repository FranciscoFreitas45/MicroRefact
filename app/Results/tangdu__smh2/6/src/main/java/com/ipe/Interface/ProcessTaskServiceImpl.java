package com.ipe.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ipe.Interface.ProcessTaskService;
public class ProcessTaskServiceImpl implements ProcessTaskService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public BodyWrapper userTaskList(String params,RestRequest rest){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/userTaskList"))
    .queryParam("params",params)
    .queryParam("rest",rest)
;  BodyWrapper aux = restTemplate.getForObject(builder.toUriString(), BodyWrapper.class);

 return aux;
}


public BodyWrapper getTaskList(String params,RestRequest rest){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTaskList"))
    .queryParam("params",params)
    .queryParam("rest",rest)
;  BodyWrapper aux = restTemplate.getForObject(builder.toUriString(), BodyWrapper.class);

 return aux;
}


public void delTask(String taskId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delTask"))
    .queryParam("taskId",taskId)
;
  restTemplate.put(builder.toUriString(), null);
}


public void clainTask(String taskId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/clainTask"))
    .queryParam("taskId",taskId)
;
  restTemplate.put(builder.toUriString(), null);
}


public void releaseTask(String taskId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/releaseTask"))
    .queryParam("taskId",taskId)
;
  restTemplate.put(builder.toUriString(), null);
}


public void taskProxy(String taskId,String userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/taskProxy"))
    .queryParam("taskId",taskId)
    .queryParam("userId",userId)
;
  restTemplate.put(builder.toUriString(), null);
}


public void taskDelegate(String taskId,String userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/taskDelegate"))
    .queryParam("taskId",taskId)
    .queryParam("userId",userId)
;
  restTemplate.put(builder.toUriString(), null);
}


}