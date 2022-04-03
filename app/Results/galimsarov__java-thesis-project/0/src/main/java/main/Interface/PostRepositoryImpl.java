package main.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import main.Interface.PostRepository;
public class PostRepositoryImpl implements PostRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public int getCountOfPostsForModeration(int moderatorId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCountOfPostsForModeration"))
    .queryParam("moderatorId",moderatorId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}