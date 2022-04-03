package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.OrganizationRepository;
public class OrganizationRepositoryImpl implements OrganizationRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<OrganizationEntity> findAllDisabledDepartmentByPositionIdIn(List<Long> ids){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllDisabledDepartmentByPositionIdIn"))
    .queryParam("ids",ids)
;  List<OrganizationEntity> aux = restTemplate.getForObject(builder.toUriString(), List<OrganizationEntity>.class);

 return aux;
}


}