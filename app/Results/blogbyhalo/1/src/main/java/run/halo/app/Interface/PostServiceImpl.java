package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.PostService;
public class PostServiceImpl implements PostService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Object countByStatus(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countByStatus"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public PostDetailVO createBy(Post post,Set<Integer> tagIds,Set<Integer> categoryIds,boolean autoSave){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createBy"))
    .queryParam("post",post)
    .queryParam("tagIds",tagIds)
    .queryParam("categoryIds",categoryIds)
    .queryParam("autoSave",autoSave)
;  PostDetailVO aux = restTemplate.getForObject(builder.toUriString(), PostDetailVO.class);

 return aux;
}


}