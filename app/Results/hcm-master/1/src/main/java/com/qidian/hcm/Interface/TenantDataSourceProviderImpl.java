package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.TenantDataSourceProvider;
public class TenantDataSourceProviderImpl implements TenantDataSourceProvider{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public ProcessEngine getProcessEngine(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getProcessEngine"))
;  ProcessEngine aux = restTemplate.getForObject(builder.toUriString(), ProcessEngine.class);

 return aux;
}


public Object getRepositoryService(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRepositoryService"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object createDeployment(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createDeployment"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object name(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/name"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object addClasspathResource(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addClasspathResource"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object deploy(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deploy"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object createProcessDefinitionQuery(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createProcessDefinitionQuery"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object orderByProcessDefinitionVersion(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/orderByProcessDefinitionVersion"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object asc(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/asc"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object list(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/list"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getRuntimeService(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRuntimeService"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object startProcessInstanceByKey(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/startProcessInstanceByKey"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}