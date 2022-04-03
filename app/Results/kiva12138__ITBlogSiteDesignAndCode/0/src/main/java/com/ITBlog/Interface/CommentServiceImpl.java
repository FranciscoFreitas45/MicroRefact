package com.ITBlog.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ITBlog.Interface.CommentService;
public class CommentServiceImpl implements CommentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public int deleteComment(long commentId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteComment"))
    .queryParam("commentId",commentId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}