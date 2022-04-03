package com.ITBlog.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ITBlog.Interface.BlogService;
public class BlogServiceImpl implements BlogService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public int deleteBlog(long blogId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteBlog"))
    .queryParam("blogId",blogId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}