package com.zammc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zammc.Interface.GoodsCateService;
public class GoodsCateServiceImpl implements GoodsCateService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public List<GoodsCateEntity> queryCateList(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryCateList"))
;  List<GoodsCateEntity> aux = restTemplate.getForObject(builder.toUriString(), List<GoodsCateEntity>.class);

 return aux;
}


}