package net.shangtech.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import net.shangtech.Interface.NewsTypeDao;
public class NewsTypeDaoImpl implements NewsTypeDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Object find(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
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


}