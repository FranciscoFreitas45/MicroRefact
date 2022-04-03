package ink.champ.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import ink.champ.Interface.RepositoryService;
public class RepositoryServiceImpl implements RepositoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Player getPlayerById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPlayerById"))
    .queryParam("id",id)
;  Player aux = restTemplate.getForObject(builder.toUriString(), Player.class);

 return aux;
}


public void addNewPlayerRole(PlayerRole role){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addNewPlayerRole"))
    .queryParam("role",role)
;
  restTemplate.put(builder.toUriString(), null);
}


public void savePlayerRole(PlayerRole role){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/savePlayerRole"))
    .queryParam("role",role)
;
  restTemplate.put(builder.toUriString(), null);
}


public PlayerRole getPlayerRoleById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPlayerRoleById"))
    .queryParam("id",id)
;  PlayerRole aux = restTemplate.getForObject(builder.toUriString(), PlayerRole.class);

 return aux;
}


public List<Team> getUserTeamsPlayerNotIn(User user,Player player){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserTeamsPlayerNotIn"))
    .queryParam("user",user)
    .queryParam("player",player)
;  List<Team> aux = restTemplate.getForObject(builder.toUriString(), List<Team>.class);

 return aux;
}


public void deletePlayer(Player player){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deletePlayer"))
    .queryParam("player",player)
;
  restTemplate.put(builder.toUriString(), null);
}


public void addNewPlayer(Player player){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addNewPlayer"))
    .queryParam("player",player)
;
  restTemplate.put(builder.toUriString(), null);
}


public void addNewTeamPlayer(TeamPlayer teamPlayer){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addNewTeamPlayer"))
    .queryParam("teamPlayer",teamPlayer)
;
  restTemplate.put(builder.toUriString(), null);
}


public void savePlayer(Player player){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/savePlayer"))
    .queryParam("player",player)
;
  restTemplate.put(builder.toUriString(), null);
}


}