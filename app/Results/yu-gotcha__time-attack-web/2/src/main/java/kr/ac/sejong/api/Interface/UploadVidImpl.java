package kr.ac.sejong.api.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import kr.ac.sejong.api.Interface.UploadVid;
public class UploadVidImpl implements UploadVid{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Object setUpVidName(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUpVidName"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object setUpVidSavedName(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUpVidSavedName"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object setUpVidPath(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUpVidPath"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object setVidUpUser(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setVidUpUser"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}