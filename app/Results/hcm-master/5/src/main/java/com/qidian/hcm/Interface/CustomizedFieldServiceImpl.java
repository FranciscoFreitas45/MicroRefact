package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.CustomizedFieldService;
public class CustomizedFieldServiceImpl implements CustomizedFieldService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public CustomizedField getById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getById"))
    .queryParam("id",id)
;  CustomizedField aux = restTemplate.getForObject(builder.toUriString(), CustomizedField.class);

 return aux;
}


}