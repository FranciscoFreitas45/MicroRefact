package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.CommentService;
public class CommentServiceImpl implements CommentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public List<CommentDetail> findALlCommentDetailByTime(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findALlCommentDetailByTime"))
;  List<CommentDetail> aux = restTemplate.getForObject(builder.toUriString(), List<CommentDetail>.class);

 return aux;
}


}