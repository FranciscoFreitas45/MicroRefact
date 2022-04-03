package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.EmployeeWorkExperienceService;
public class EmployeeWorkExperienceServiceImpl implements EmployeeWorkExperienceService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public void saveEmployeeWorkExperience(Long employeeId,List<EmployeeWorkExperienceDTO> employeeWorkExperienceDTOList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveEmployeeWorkExperience"))
    .queryParam("employeeId",employeeId)
    .queryParam("employeeWorkExperienceDTOList",employeeWorkExperienceDTOList)
;
  restTemplate.put(builder.toUriString(), null);
}


}