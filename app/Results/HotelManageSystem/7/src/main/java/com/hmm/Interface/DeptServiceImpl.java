package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.DeptService;
public class DeptServiceImpl implements DeptService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Department findByDeptName(String deptName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDeptName"))
    .queryParam("deptName",deptName)
;  Department aux = restTemplate.getForObject(builder.toUriString(), Department.class);

 return aux;
}


}