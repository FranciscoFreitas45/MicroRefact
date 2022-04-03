package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.IdentityDao;
public class IdentityDaoImpl implements IdentityDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public void getIdentities(List<Drop> drops){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getIdentities"))
    .queryParam("drops",drops)
;
  restTemplate.put(builder.toUriString(), null);
}


}