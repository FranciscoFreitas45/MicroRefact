package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhResourceService;
public class PhResourceServiceImpl implements PhResourceService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://15";


public List<PhResource> findByAdminId(Long adminId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByAdminId"))
    .queryParam("adminId",adminId)
;  List<PhResource> aux = restTemplate.getForObject(builder.toUriString(), List<PhResource>.class);

 return aux;
}


}