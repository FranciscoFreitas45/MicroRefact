package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.MediaDao;
public class MediaDaoImpl implements MediaDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public void getMedia(List<Drop> drops){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMedia"))
    .queryParam("drops",drops)
;
  restTemplate.put(builder.toUriString(), null);
}


}