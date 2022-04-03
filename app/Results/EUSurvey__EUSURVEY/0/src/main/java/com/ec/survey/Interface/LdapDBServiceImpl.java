package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.LdapDBService;
public class LdapDBServiceImpl implements LdapDBService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public String[] getDepartments(String domain,String term,boolean prefix,boolean removeTerm){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDepartments"))
    .queryParam("domain",domain)
    .queryParam("term",term)
    .queryParam("prefix",prefix)
    .queryParam("removeTerm",removeTerm)
;  String[] aux = restTemplate.getForObject(builder.toUriString(), String[].class);

 return aux;
}


}