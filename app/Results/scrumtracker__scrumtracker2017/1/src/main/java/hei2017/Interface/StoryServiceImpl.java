package hei2017.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.Interface.StoryService;
public class StoryServiceImpl implements StoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Story findOneByIdWithAll(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOneByIdWithAll"))
    .queryParam("id",id)
;  Story aux = restTemplate.getForObject(builder.toUriString(), Story.class);

 return aux;
}


public Story save(Story story){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("story",story)
;  Story aux = restTemplate.getForObject(builder.toUriString(), Story.class);

 return aux;
}


}