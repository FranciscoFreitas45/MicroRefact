package com.poseidon.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.poseidon.Interface.CompanyTermsService;
public class CompanyTermsServiceImpl implements CompanyTermsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Optional<CompanyTermsVO> listCompanyTerms(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listCompanyTerms"))
;  Optional<CompanyTermsVO> aux = restTemplate.getForObject(builder.toUriString(), Optional<CompanyTermsVO>.class);

 return aux;
}


}