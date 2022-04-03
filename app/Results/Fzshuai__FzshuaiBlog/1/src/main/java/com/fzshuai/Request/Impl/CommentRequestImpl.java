package com.fzshuai.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.DTO.Comment;
import com.fzshuai.Request.CommentRequest;
public class CommentRequestImpl implements CommentRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<Comment> getComments(Long id){
 List<Comment> aux = restTemplate.getForObject("http://0/Blog/{id}/Comment/getComments",List<Comment>.class,id);
return aux;
}


public void setComments(List<Comment> comments,Long id){
 restTemplate.put("http://0/Blog/{id}/Comment/setComments",comments,id);
 return ;
}


}