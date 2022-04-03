package com.uec.imonitor.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.uec.imonitor.Interface.INewsStatusService;
public class INewsStatusServiceImpl implements INewsStatusService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<NewsStatusEntity> listTopNInsertRecordsByTableName(String tableName,int topN){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listTopNInsertRecordsByTableName"))
    .queryParam("tableName",tableName)
    .queryParam("topN",topN)
;  List<NewsStatusEntity> aux = restTemplate.getForObject(builder.toUriString(), List<NewsStatusEntity>.class);

 return aux;
}


public Boolean deleteEsStatusList(List<NewsStatusEntity> esStatusList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteEsStatusList"))
    .queryParam("esStatusList",esStatusList)
;  Boolean aux = restTemplate.getForObject(builder.toUriString(), Boolean.class);

 return aux;
}


public List<NewsStatusEntity> listTopNDeletedRecordsByTableName(String tableName,int topN){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listTopNDeletedRecordsByTableName"))
    .queryParam("tableName",tableName)
    .queryParam("topN",topN)
;  List<NewsStatusEntity> aux = restTemplate.getForObject(builder.toUriString(), List<NewsStatusEntity>.class);

 return aux;
}


public List<NewsStatusEntity> listTopNUpdateRecordsByTableName(String tableName,int topN){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listTopNUpdateRecordsByTableName"))
    .queryParam("tableName",tableName)
    .queryParam("topN",topN)
;  List<NewsStatusEntity> aux = restTemplate.getForObject(builder.toUriString(), List<NewsStatusEntity>.class);

 return aux;
}


}