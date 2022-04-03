package hei2017.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.Interface.UserDAO;
public class UserDAOImpl implements UserDAO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Set<User> findByUserTasksId(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserTasksId"))
    .queryParam("id",id)
;  Set<User> aux = restTemplate.getForObject(builder.toUriString(), Set<User>.class);

 return aux;
}


}