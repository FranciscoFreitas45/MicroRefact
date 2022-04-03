package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.ActivityDao;
public class ActivityDaoImpl implements ActivityDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Object create(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Activity> find(long accountId,Integer count,Long lastId,Boolean newer,Boolean followers){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("accountId",accountId)
    .queryParam("count",count)
    .queryParam("lastId",lastId)
    .queryParam("newer",newer)
    .queryParam("followers",followers)
;  List<Activity> aux = restTemplate.getForObject(builder.toUriString(), List<Activity>.class);

 return aux;
}


}