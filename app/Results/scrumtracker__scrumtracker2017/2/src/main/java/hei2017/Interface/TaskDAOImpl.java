package hei2017.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.Interface.TaskDAO;
public class TaskDAOImpl implements TaskDAO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Set<Task> findByTaskUsersId(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByTaskUsersId"))
    .queryParam("id",id)
;  Set<Task> aux = restTemplate.getForObject(builder.toUriString(), Set<Task>.class);

 return aux;
}


}