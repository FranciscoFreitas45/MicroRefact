package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.AccessoriesService;
public class AccessoriesServiceImpl implements AccessoriesService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Object queryAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object count(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Accessories> queryByIdPager(String id,Pager pager){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByIdPager"))
    .queryParam("id",id)
    .queryParam("pager",pager)
;  List<Accessories> aux = restTemplate.getForObject(builder.toUriString(), List<Accessories>.class);

 return aux;
}


public Object queryByPager(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByPager"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object insert(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insert"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object update(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object countByDisable(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countByDisable"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object queryByPagerDisable(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByPagerDisable"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object inactive(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/inactive"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object active(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/active"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object blurredQuery(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/blurredQuery"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object countByBlurred(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countByBlurred"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object queryById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Accessories> queryByCondition(String start,String end,String companyId,String accTypeId,String type){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByCondition"))
    .queryParam("start",start)
    .queryParam("end",end)
    .queryParam("companyId",companyId)
    .queryParam("accTypeId",accTypeId)
    .queryParam("type",type)
;  List<Accessories> aux = restTemplate.getForObject(builder.toUriString(), List<Accessories>.class);

 return aux;
}


}