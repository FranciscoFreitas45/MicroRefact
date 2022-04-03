package com.fzshuai.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.DTO.Blog;
import com.fzshuai.Request.BlogRequest;
public class BlogRequestImpl implements BlogRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setBlogs(List<Blog> blogs,Long id){
 restTemplate.put("http://7/Type/{id}/Blog/setBlogs",blogs,id);
 return ;
}


public List<Blog> getBlogs(Long id){
 List<Blog> aux = restTemplate.getForObject("http://7/Type/{id}/Blog/getBlogs",List<Blog>.class,id);
return aux;
}


}