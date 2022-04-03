package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.EmployeeFinancialRepository;
public class EmployeeFinancialRepositoryImpl implements EmployeeFinancialRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<EmployeeFinancial> findBySocialSecurityPlanId(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBySocialSecurityPlanId"))
    .queryParam("id",id)
;  List<EmployeeFinancial> aux = restTemplate.getForObject(builder.toUriString(), List<EmployeeFinancial>.class);

 return aux;
}


}