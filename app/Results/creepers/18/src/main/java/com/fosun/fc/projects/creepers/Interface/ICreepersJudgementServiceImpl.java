package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersJudgementService;
public class ICreepersJudgementServiceImpl implements ICreepersJudgementService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Page<CreepersJudgementDTO> queryJudgementList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryJudgementList"))
    .queryParam("searchParams",searchParams)
    .queryParam("pageNumber",pageNumber)
    .queryParam("pageSize",pageSize)
    .queryParam("sortType",sortType)
;  Page<CreepersJudgementDTO> aux = restTemplate.getForObject(builder.toUriString(), Page<CreepersJudgementDTO>.class);

 return aux;
}


}