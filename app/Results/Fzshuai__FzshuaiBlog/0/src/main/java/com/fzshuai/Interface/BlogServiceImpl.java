package com.fzshuai.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.Interface.BlogService;
public class BlogServiceImpl implements BlogService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Blog getBlog(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBlog"))
    .queryParam("id",id)
;  Blog aux = restTemplate.getForObject(builder.toUriString(), Blog.class);

 return aux;
}


}