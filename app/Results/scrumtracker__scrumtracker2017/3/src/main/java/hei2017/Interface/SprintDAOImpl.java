package hei2017.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.Interface.SprintDAO;
public class SprintDAOImpl implements SprintDAO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Set<Sprint> findBySprintProjectId(Long idProject){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBySprintProjectId"))
    .queryParam("idProject",idProject)
;  Set<Sprint> aux = restTemplate.getForObject(builder.toUriString(), Set<Sprint>.class);

 return aux;
}


}