package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.IncomingTypeService;
public class IncomingTypeServiceImpl implements IncomingTypeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public Object count(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object queryByPager(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByPager"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public int countByDisable(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countByDisable"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public List<IncomingType> queryByPagerDisable(Pager pager){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByPagerDisable"))
    .queryParam("pager",pager)
;  List<IncomingType> aux = restTemplate.getForObject(builder.toUriString(), List<IncomingType>.class);

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


public Object insert(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insert"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public IncomingType queryById(String inTypeName,String inTypeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryById"))
    .queryParam("inTypeName",inTypeName)
    .queryParam("inTypeId",inTypeId)
;  IncomingType aux = restTemplate.getForObject(builder.toUriString(), IncomingType.class);

 return aux;
}


public Object update(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object queryAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}