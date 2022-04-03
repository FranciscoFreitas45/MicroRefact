package com.fzshuai.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.Interface.BlogService;
public class BlogServiceImpl implements BlogService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Page<Blog> listBlog(String query,Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listBlog"))
    .queryParam("query",query)
    .queryParam("pageable",pageable)
;  Page<Blog> aux = restTemplate.getForObject(builder.toUriString(), Page<Blog>.class);

 return aux;
}


}