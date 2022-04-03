package hei2017.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.Interface.StoryDAO;
public class StoryDAOImpl implements StoryDAO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Set<Story> findByStorySprintId(Long idSprint){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByStorySprintId"))
    .queryParam("idSprint",idSprint)
;  Set<Story> aux = restTemplate.getForObject(builder.toUriString(), Set<Story>.class);

 return aux;
}


}