package hei2017.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.Interface.SprintDAO;
public class SprintDAOImpl implements SprintDAO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Sprint findBySprintStoriesId(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBySprintStoriesId"))
    .queryParam("id",id)
;  Sprint aux = restTemplate.getForObject(builder.toUriString(), Sprint.class);

 return aux;
}


}