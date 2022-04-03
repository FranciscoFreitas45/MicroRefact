package com.sobey.cmop.mvc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sobey.cmop.mvc.Interface.IpPoolDaoCustom;
public class IpPoolDaoCustomImpl implements IpPoolDaoCustom{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public int updateIpPoolByStatus(int status){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateIpPoolByStatus"))
    .queryParam("status",status)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}