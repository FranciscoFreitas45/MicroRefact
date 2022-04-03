package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.EmployeeEmergencyContactService;
public class EmployeeEmergencyContactServiceImpl implements EmployeeEmergencyContactService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public void saveEmployeeEmergencyContact(Long employeeId,List<EmployeeEmergencyContactDTO> employeeEmergencyContactDTOList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveEmployeeEmergencyContact"))
    .queryParam("employeeId",employeeId)
    .queryParam("employeeEmergencyContactDTOList",employeeEmergencyContactDTOList)
;
  restTemplate.put(builder.toUriString(), null);
}


}