package com.fzshuai.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.DTO.Blog;
import com.fzshuai.Request.BlogRequest;
public class BlogRequestImpl implements BlogRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Blog getBlog(Long id){
 Blog aux = restTemplate.getForObject("http://1/Comment/{id}/Blog/getBlog",Blog.class,id);
return aux;
}


public void setBlog(Blog blog,Long id){
 restTemplate.put("http://1/Comment/{id}/Blog/setBlog",blog,id);
 return ;
}


}