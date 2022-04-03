package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.OrganizationService;
public class OrganizationServiceImpl implements OrganizationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Long> listSelfAndChildrenIds(List<Long> ids){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listSelfAndChildrenIds"))
    .queryParam("ids",ids)
;  List<Long> aux = restTemplate.getForObject(builder.toUriString(), List<Long>.class);

 return aux;
}


public Map<Long,OrganizationEntity> getIdEntityMap(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getIdEntityMap"))
;  Map<Long,OrganizationEntity> aux = restTemplate.getForObject(builder.toUriString(), Map<Long,OrganizationEntity>.class);

 return aux;
}


public List<Long> listAllPermissionOrgIds(PlatformType platformType,MenuCode menuCode,ActionType actionType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listAllPermissionOrgIds"))
    .queryParam("platformType",platformType)
    .queryParam("menuCode",menuCode)
    .queryParam("actionType",actionType)
;  List<Long> aux = restTemplate.getForObject(builder.toUriString(), List<Long>.class);

 return aux;
}


}