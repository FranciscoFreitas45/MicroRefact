package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.CarModelService;
public class CarModelServiceImpl implements CarModelService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


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


public Object queryByPager(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByPager"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<CarModel> queryByBrandId(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByBrandId"))
    .queryParam("id",id)
;  List<CarModel> aux = restTemplate.getForObject(builder.toUriString(), List<CarModel>.class);

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


public int querymodelName(String modelName,String modelId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/querymodelName"))
    .queryParam("modelName",modelName)
    .queryParam("modelId",modelId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

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


}