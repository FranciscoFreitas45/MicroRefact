package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.EmployeePositionService;
public class EmployeePositionServiceImpl implements EmployeePositionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public EmployeePosition getCurrentEmployeePosition(Long employeeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCurrentEmployeePosition"))
    .queryParam("employeeId",employeeId)
;  EmployeePosition aux = restTemplate.getForObject(builder.toUriString(), EmployeePosition.class);

 return aux;
}


}