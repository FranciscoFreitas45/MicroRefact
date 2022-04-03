package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.BucketDropDao;
public class BucketDropDaoImpl implements BucketDropDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Object populateMetadata(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/populateMetadata"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}