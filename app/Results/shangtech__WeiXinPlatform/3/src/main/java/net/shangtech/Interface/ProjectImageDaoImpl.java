package net.shangtech.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import net.shangtech.Interface.ProjectImageDao;
public class ProjectImageDaoImpl implements ProjectImageDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Object insert(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insert"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object find(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}