package ink.champ.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import ink.champ.Interface.ChampRepository;
public class ChampRepositoryImpl implements ChampRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Champ> findChampsByNameContainingIgnoreCase(String search,Sort sort){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findChampsByNameContainingIgnoreCase"))
    .queryParam("search",search)
    .queryParam("sort",sort)
;  List<Champ> aux = restTemplate.getForObject(builder.toUriString(), List<Champ>.class);

 return aux;
}


public List<Champ> findChampsByUserRoleAndTeamNotIn(Team team,User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findChampsByUserRoleAndTeamNotIn"))
    .queryParam("team",team)
    .queryParam("user",user)
;  List<Champ> aux = restTemplate.getForObject(builder.toUriString(), List<Champ>.class);

 return aux;
}


public List<Champ> findChampsByPrivatIsFalseAndNameContainingIgnoreCase(String search,Sort sort){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findChampsByPrivatIsFalseAndNameContainingIgnoreCase"))
    .queryParam("search",search)
    .queryParam("sort",sort)
;  List<Champ> aux = restTemplate.getForObject(builder.toUriString(), List<Champ>.class);

 return aux;
}


public List<Champ> findChampsByUserAll(User user,String search){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findChampsByUserAll"))
    .queryParam("user",user)
    .queryParam("search",search)
;  List<Champ> aux = restTemplate.getForObject(builder.toUriString(), List<Champ>.class);

 return aux;
}


public List<Champ> findChampsByUserRole(User user,int role,String search){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findChampsByUserRole"))
    .queryParam("user",user)
    .queryParam("role",role)
    .queryParam("search",search)
;  List<Champ> aux = restTemplate.getForObject(builder.toUriString(), List<Champ>.class);

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