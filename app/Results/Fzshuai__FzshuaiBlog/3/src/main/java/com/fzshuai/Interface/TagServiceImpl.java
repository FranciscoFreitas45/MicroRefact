package com.fzshuai.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.Interface.TagService;
public class TagServiceImpl implements TagService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Tag> listTag(String ids){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listTag"))
    .queryParam("ids",ids)
;  List<Tag> aux = restTemplate.getForObject(builder.toUriString(), List<Tag>.class);

 return aux;
}


}