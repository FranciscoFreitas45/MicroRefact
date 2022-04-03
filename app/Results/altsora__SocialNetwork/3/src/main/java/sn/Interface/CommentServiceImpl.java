package sn.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import sn.Interface.CommentService;
public class CommentServiceImpl implements CommentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<CommentResponse> getCommentsByPostId(long postId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCommentsByPostId"))
    .queryParam("postId",postId)
;  List<CommentResponse> aux = restTemplate.getForObject(builder.toUriString(), List<CommentResponse>.class);

 return aux;
}


}