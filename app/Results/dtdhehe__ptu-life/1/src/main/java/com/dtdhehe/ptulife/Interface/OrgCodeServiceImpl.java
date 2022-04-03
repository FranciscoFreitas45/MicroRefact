package com.dtdhehe.ptulife.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.dtdhehe.ptulife.Interface.OrgCodeService;
public class OrgCodeServiceImpl implements OrgCodeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public String getOrgNameByOrgStatus(String orgStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOrgNameByOrgStatus"))
    .queryParam("orgStatus",orgStatus)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}