package com.poseidon.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.poseidon.Interface.CustomerService;
public class CustomerServiceImpl implements CustomerService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public CustomerVO saveCustomer(CustomerVO currentCustomerVO){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveCustomer"))
    .queryParam("currentCustomerVO",currentCustomerVO)
;  CustomerVO aux = restTemplate.getForObject(builder.toUriString(), CustomerVO.class);

 return aux;
}


public Optional<CustomerVO> getCustomerFromId(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCustomerFromId"))
    .queryParam("id",id)
;  Optional<CustomerVO> aux = restTemplate.getForObject(builder.toUriString(), Optional<CustomerVO>.class);

 return aux;
}


}