package com.empl.mgr.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.empl.mgr.Interface.EmployeesBasicDao;
public class EmployeesBasicDaoImpl implements EmployeesBasicDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void chearEmplPositionByPositionId(long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/chearEmplPositionByPositionId"))
    .queryParam("id",id)
;
  restTemplate.put(builder.toUriString(), null);
}


}