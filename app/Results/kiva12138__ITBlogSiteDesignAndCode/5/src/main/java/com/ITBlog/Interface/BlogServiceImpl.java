package com.ITBlog.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ITBlog.Interface.BlogService;
public class BlogServiceImpl implements BlogService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Blog findBlogById(long blogId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBlogById"))
    .queryParam("blogId",blogId)
;  Blog aux = restTemplate.getForObject(builder.toUriString(), Blog.class);

 return aux;
}


}