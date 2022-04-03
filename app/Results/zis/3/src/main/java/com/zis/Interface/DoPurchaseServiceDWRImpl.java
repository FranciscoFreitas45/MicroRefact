package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.DoPurchaseServiceDWR;
public class DoPurchaseServiceDWRImpl implements DoPurchaseServiceDWR{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public String addBlackList(Integer bookId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addBlackList"))
    .queryParam("bookId",bookId)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}