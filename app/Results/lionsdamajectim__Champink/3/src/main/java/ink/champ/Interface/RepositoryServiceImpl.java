package ink.champ.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import ink.champ.Interface.RepositoryService;
public class RepositoryServiceImpl implements RepositoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Team getTeamById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTeamById"))
    .queryParam("id",id)
;  Team aux = restTemplate.getForObject(builder.toUriString(), Team.class);

 return aux;
}


public void addNewTeamRole(TeamRole role){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addNewTeamRole"))
    .queryParam("role",role)
;
  restTemplate.put(builder.toUriString(), null);
}


public void saveTeamRole(TeamRole role){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveTeamRole"))
    .queryParam("role",role)
;
  restTemplate.put(builder.toUriString(), null);
}


public TeamRole getTeamRoleById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTeamRoleById"))
    .queryParam("id",id)
;  TeamRole aux = restTemplate.getForObject(builder.toUriString(), TeamRole.class);

 return aux;
}


public List<Player> getUserPlayersNotInTeam(User user,Team team){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserPlayersNotInTeam"))
    .queryParam("user",user)
    .queryParam("team",team)
;  List<Player> aux = restTemplate.getForObject(builder.toUriString(), List<Player>.class);

 return aux;
}


public List<Champ> getUserChampsTeamNotIn(User user,Team team){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserChampsTeamNotIn"))
    .queryParam("user",user)
    .queryParam("team",team)
;  List<Champ> aux = restTemplate.getForObject(builder.toUriString(), List<Champ>.class);

 return aux;
}


public TeamPlayer getTeamPlayerById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTeamPlayerById"))
    .queryParam("id",id)
;  TeamPlayer aux = restTemplate.getForObject(builder.toUriString(), TeamPlayer.class);

 return aux;
}


public void deleteTeamPlayer(TeamPlayer teamPlayer){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteTeamPlayer"))
    .queryParam("teamPlayer",teamPlayer)
;
  restTemplate.put(builder.toUriString(), null);
}


public void deleteTeam(Team team){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteTeam"))
    .queryParam("team",team)
;
  restTemplate.put(builder.toUriString(), null);
}


public void addNewTeam(Team team){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addNewTeam"))
    .queryParam("team",team)
;
  restTemplate.put(builder.toUriString(), null);
}


public void addNewTeamPlayer(TeamPlayer teamPlayer){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addNewTeamPlayer"))
    .queryParam("teamPlayer",teamPlayer)
;
  restTemplate.put(builder.toUriString(), null);
}


public void addNewChampTeam(ChampTeam champTeam){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addNewChampTeam"))
    .queryParam("champTeam",champTeam)
;
  restTemplate.put(builder.toUriString(), null);
}


public void saveTeam(Team team){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveTeam"))
    .queryParam("team",team)
;
  restTemplate.put(builder.toUriString(), null);
}


}