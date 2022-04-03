package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.CompanyService;
public class CompanyServiceImpl implements CompanyService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<Company> queryByCompanyInfo(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByCompanyInfo"))
;  List<Company> aux = restTemplate.getForObject(builder.toUriString(), List<Company>.class);

 return aux;
}


}