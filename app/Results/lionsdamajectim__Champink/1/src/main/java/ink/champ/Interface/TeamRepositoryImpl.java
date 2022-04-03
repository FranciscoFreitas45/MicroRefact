package ink.champ.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import ink.champ.Interface.TeamRepository;
public class TeamRepositoryImpl implements TeamRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Team> findTeamsByNameContainingIgnoreCase(String search,Sort sort){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTeamsByNameContainingIgnoreCase"))
    .queryParam("search",search)
    .queryParam("sort",sort)
;  List<Team> aux = restTemplate.getForObject(builder.toUriString(), List<Team>.class);

 return aux;
}


public List<Team> findTeamsByUserRoleAndNotInChamp(Champ champ,User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTeamsByUserRoleAndNotInChamp"))
    .queryParam("champ",champ)
    .queryParam("user",user)
;  List<Team> aux = restTemplate.getForObject(builder.toUriString(), List<Team>.class);

 return aux;
}


public List<Team> findTeamsByUserRoleAndPlayerNotIn(Player player,User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTeamsByUserRoleAndPlayerNotIn"))
    .queryParam("player",player)
    .queryParam("user",user)
;  List<Team> aux = restTemplate.getForObject(builder.toUriString(), List<Team>.class);

 return aux;
}


public List<Team> findTeamsByPrivatIsFalseAndNameContainingIgnoreCase(String search,Sort sort){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTeamsByPrivatIsFalseAndNameContainingIgnoreCase"))
    .queryParam("search",search)
    .queryParam("sort",sort)
;  List<Team> aux = restTemplate.getForObject(builder.toUriString(), List<Team>.class);

 return aux;
}


public List<Team> findTeamsByUserAll(User user,String search){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTeamsByUserAll"))
    .queryParam("user",user)
    .queryParam("search",search)
;  List<Team> aux = restTemplate.getForObject(builder.toUriString(), List<Team>.class);

 return aux;
}


public List<Team> findTeamsByUserRole(User user,int role,String search){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTeamsByUserRole"))
    .queryParam("user",user)
    .queryParam("role",role)
    .queryParam("search",search)
;  List<Team> aux = restTemplate.getForObject(builder.toUriString(), List<Team>.class);

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