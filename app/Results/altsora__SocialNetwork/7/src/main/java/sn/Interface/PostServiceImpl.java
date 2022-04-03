package sn.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import sn.Interface.PostService;
public class PostServiceImpl implements PostService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public void incLikesCount(long postId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/incLikesCount"))
    .queryParam("postId",postId)
;
  restTemplate.put(builder.toUriString(), null);
}


public void decLikesCount(long postId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/decLikesCount"))
    .queryParam("postId",postId)
;
  restTemplate.put(builder.toUriString(), null);
}


}