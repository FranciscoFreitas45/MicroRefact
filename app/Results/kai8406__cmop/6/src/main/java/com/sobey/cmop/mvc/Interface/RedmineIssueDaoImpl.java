package com.sobey.cmop.mvc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sobey.cmop.mvc.Interface.RedmineIssueDao;
public class RedmineIssueDaoImpl implements RedmineIssueDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Object findOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public RedmineIssue findByIssueId(Integer issueId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIssueId"))
    .queryParam("issueId",issueId)
;  RedmineIssue aux = restTemplate.getForObject(builder.toUriString(), RedmineIssue.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}