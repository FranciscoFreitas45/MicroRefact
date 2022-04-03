package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersListPatentService;
public class ICreepersListPatentServiceImpl implements ICreepersListPatentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://13";


public Page<CreepersListPatentDTO> queryListPatentList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryListPatentList"))
    .queryParam("searchParams",searchParams)
    .queryParam("pageNumber",pageNumber)
    .queryParam("pageSize",pageSize)
    .queryParam("sortType",sortType)
;  Page<CreepersListPatentDTO> aux = restTemplate.getForObject(builder.toUriString(), Page<CreepersListPatentDTO>.class);

 return aux;
}


}