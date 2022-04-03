package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinService;
public class ICreepersShixinServiceImpl implements ICreepersShixinService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Page<CreepersShixinDTO> findList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findList"))
    .queryParam("searchParams",searchParams)
    .queryParam("pageNumber",pageNumber)
    .queryParam("pageSize",pageSize)
    .queryParam("sortType",sortType)
;  Page<CreepersShixinDTO> aux = restTemplate.getForObject(builder.toUriString(), Page<CreepersShixinDTO>.class);

 return aux;
}


}