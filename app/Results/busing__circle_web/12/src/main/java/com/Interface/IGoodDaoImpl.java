package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.IGoodDao;
public class IGoodDaoImpl implements IGoodDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Map<String,Object>> queryGoodList(Page page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryGoodList"))
    .queryParam("page",page)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


public List<Map<String,Object>> queryCurrentBatchGoodList(Page page,int batchId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryCurrentBatchGoodList"))
    .queryParam("page",page)
    .queryParam("batchId",batchId)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


public Map<String,Object> queryGood(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryGood"))
    .queryParam("id",id)
;  Map<String,Object> aux = restTemplate.getForObject(builder.toUriString(), Map<String,Object>.class);

 return aux;
}


public List<Map<String,Object>> queryGoodTypeArgValues(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryGoodTypeArgValues"))
    .queryParam("id",id)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


public List<Map<String,Object>> queryGoodTypeAttrValues(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryGoodTypeAttrValues"))
    .queryParam("id",id)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


public List<Map<String,Object>> queryImageList(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryImageList"))
    .queryParam("id",id)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


public List<Map<String,Object>> queryGoodComment(Page page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryGoodComment"))
    .queryParam("page",page)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


}