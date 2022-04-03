package ink.champ.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import ink.champ.Interface.PlayerRepository;
public class PlayerRepositoryImpl implements PlayerRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<Player> findPlayersByNameContainingIgnoreCase(String search,Sort sort){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPlayersByNameContainingIgnoreCase"))
    .queryParam("search",search)
    .queryParam("sort",sort)
;  List<Player> aux = restTemplate.getForObject(builder.toUriString(), List<Player>.class);

 return aux;
}


public List<Player> findPlayersByUserRoleAndNotInTeam(Team team,User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPlayersByUserRoleAndNotInTeam"))
    .queryParam("team",team)
    .queryParam("user",user)
;  List<Player> aux = restTemplate.getForObject(builder.toUriString(), List<Player>.class);

 return aux;
}


public List<Player> findPlayersByPrivatIsFalseAndNameContainingIgnoreCase(String search,Sort sort){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPlayersByPrivatIsFalseAndNameContainingIgnoreCase"))
    .queryParam("search",search)
    .queryParam("sort",sort)
;  List<Player> aux = restTemplate.getForObject(builder.toUriString(), List<Player>.class);

 return aux;
}


public List<Player> findPlayersByUserAll(User user,String search){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPlayersByUserAll"))
    .queryParam("user",user)
    .queryParam("search",search)
;  List<Player> aux = restTemplate.getForObject(builder.toUriString(), List<Player>.class);

 return aux;
}


public List<Player> findPlayersByUserRole(User user,int role,String search){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPlayersByUserRole"))
    .queryParam("user",user)
    .queryParam("role",role)
    .queryParam("search",search)
;  List<Player> aux = restTemplate.getForObject(builder.toUriString(), List<Player>.class);

 return aux;
}


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}