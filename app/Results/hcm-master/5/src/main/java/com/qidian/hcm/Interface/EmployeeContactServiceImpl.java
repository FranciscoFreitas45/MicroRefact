package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.EmployeeContactService;
public class EmployeeContactServiceImpl implements EmployeeContactService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public void saveEmployeeContact(Long employeeId,List<EmployeeContactDTO> employeeContactDTOS){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveEmployeeContact"))
    .queryParam("employeeId",employeeId)
    .queryParam("employeeContactDTOS",employeeContactDTOS)
;
  restTemplate.put(builder.toUriString(), null);
}


}