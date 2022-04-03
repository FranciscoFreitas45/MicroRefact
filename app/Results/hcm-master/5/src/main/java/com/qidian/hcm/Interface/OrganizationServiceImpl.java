package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.OrganizationService;
public class OrganizationServiceImpl implements OrganizationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Map<Long,String> getIdNameMap(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getIdNameMap"))
;  Map<Long,String> aux = restTemplate.getForObject(builder.toUriString(), Map<Long,String>.class);

 return aux;
}


public List<String> listOrganizationPathByIds(List<Long> ids){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listOrganizationPathByIds"))
    .queryParam("ids",ids)
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


public void createOrganizationPath(OrganizationEntity organization){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createOrganizationPath"))
    .queryParam("organization",organization)
;
  restTemplate.put(builder.toUriString(), null);
}


}