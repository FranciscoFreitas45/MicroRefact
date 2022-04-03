package com.uec.imonitor.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.uec.imonitor.Interface.IDataIndex;
public class IDataIndexImpl implements IDataIndex{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public boolean bulkUpdate(String indexName,String indexType,List<T> entityList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/bulkUpdate"))
    .queryParam("indexName",indexName)
    .queryParam("indexType",indexType)
    .queryParam("entityList",entityList)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public boolean delete(String indexName,String indexType,String primaryKey){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("indexName",indexName)
    .queryParam("indexType",indexType)
    .queryParam("primaryKey",primaryKey)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}