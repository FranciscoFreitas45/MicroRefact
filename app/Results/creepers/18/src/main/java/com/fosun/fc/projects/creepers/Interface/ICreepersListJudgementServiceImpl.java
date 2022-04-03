package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersListJudgementService;
public class ICreepersListJudgementServiceImpl implements ICreepersListJudgementService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Page<CreepersListJudgementDTO> findListJudgementList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findListJudgementList"))
    .queryParam("searchParams",searchParams)
    .queryParam("pageNumber",pageNumber)
    .queryParam("pageSize",pageSize)
    .queryParam("sortType",sortType)
;  Page<CreepersListJudgementDTO> aux = restTemplate.getForObject(builder.toUriString(), Page<CreepersListJudgementDTO>.class);

 return aux;
}


}