package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.ICommissionService;
public class ICommissionServiceImpl implements ICommissionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public boolean increaseUserCommission(CommissionHistory commissionHistory){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/increaseUserCommission"))
    .queryParam("commissionHistory",commissionHistory)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}