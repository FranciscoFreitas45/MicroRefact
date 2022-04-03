package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersPatentService;
public class ICreepersPatentServiceImpl implements ICreepersPatentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://13";


public Page<CreepersPatentDTO> queryPatentList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryPatentList"))
    .queryParam("searchParams",searchParams)
    .queryParam("pageNumber",pageNumber)
    .queryParam("pageSize",pageSize)
    .queryParam("sortType",sortType)
;  Page<CreepersPatentDTO> aux = restTemplate.getForObject(builder.toUriString(), Page<CreepersPatentDTO>.class);

 return aux;
}


}