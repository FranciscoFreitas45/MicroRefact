package ink.champ.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import ink.champ.Interface.RepositoryService;
public class RepositoryServiceImpl implements RepositoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public User getUserById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserById"))
    .queryParam("id",id)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


public void deleteUser(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteUser"))
    .queryParam("user",user)
;
  restTemplate.put(builder.toUriString(), null);
}


public void saveUser(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveUser"))
    .queryParam("user",user)
;
  restTemplate.put(builder.toUriString(), null);
}


public void addNewUser(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addNewUser"))
    .queryParam("user",user)
;
  restTemplate.put(builder.toUriString(), null);
}


}