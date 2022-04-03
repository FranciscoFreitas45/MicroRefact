package hei2017.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.Interface.StoryService;
public class StoryServiceImpl implements StoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<Story> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
;  List<Story> aux = restTemplate.getForObject(builder.toUriString(), List<Story>.class);

 return aux;
}


public List<Story> findAllWithoutSprint(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllWithoutSprint"))
;  List<Story> aux = restTemplate.getForObject(builder.toUriString(), List<Story>.class);

 return aux;
}


}