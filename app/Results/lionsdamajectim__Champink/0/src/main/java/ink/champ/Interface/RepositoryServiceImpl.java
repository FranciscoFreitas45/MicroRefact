package ink.champ.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import ink.champ.Interface.RepositoryService;
public class RepositoryServiceImpl implements RepositoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Champ getChampById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getChampById"))
    .queryParam("id",id)
;  Champ aux = restTemplate.getForObject(builder.toUriString(), Champ.class);

 return aux;
}


public List<Sport> getSports(String search){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSports"))
    .queryParam("search",search)
;  List<Sport> aux = restTemplate.getForObject(builder.toUriString(), List<Sport>.class);

 return aux;
}


public void addNewChampRole(ChampRole role){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addNewChampRole"))
    .queryParam("role",role)
;
  restTemplate.put(builder.toUriString(), null);
}


public void saveChampRole(ChampRole role){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveChampRole"))
    .queryParam("role",role)
;
  restTemplate.put(builder.toUriString(), null);
}


public ChampRole getChampRoleById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getChampRoleById"))
    .queryParam("id",id)
;  ChampRole aux = restTemplate.getForObject(builder.toUriString(), ChampRole.class);

 return aux;
}


public List<Team> getUserTeamsNotInChamp(User user,Champ champ){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserTeamsNotInChamp"))
    .queryParam("user",user)
    .queryParam("champ",champ)
;  List<Team> aux = restTemplate.getForObject(builder.toUriString(), List<Team>.class);

 return aux;
}


public ChampEvent getChampEventById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getChampEventById"))
    .queryParam("id",id)
;  ChampEvent aux = restTemplate.getForObject(builder.toUriString(), ChampEvent.class);

 return aux;
}


public ChampTeam getChampTeamById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getChampTeamById"))
    .queryParam("id",id)
;  ChampTeam aux = restTemplate.getForObject(builder.toUriString(), ChampTeam.class);

 return aux;
}


public void deleteChampTeam(ChampTeam champTeam){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteChampTeam"))
    .queryParam("champTeam",champTeam)
;
  restTemplate.put(builder.toUriString(), null);
}


public void deleteChampEvent(ChampEvent champEvent){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteChampEvent"))
    .queryParam("champEvent",champEvent)
;
  restTemplate.put(builder.toUriString(), null);
}


public void deleteChamp(Champ champ){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteChamp"))
    .queryParam("champ",champ)
;
  restTemplate.put(builder.toUriString(), null);
}


public Sport getSportById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSportById"))
    .queryParam("id",id)
;  Sport aux = restTemplate.getForObject(builder.toUriString(), Sport.class);

 return aux;
}


public void addNewChamp(Champ champ){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addNewChamp"))
    .queryParam("champ",champ)
;
  restTemplate.put(builder.toUriString(), null);
}


public void addNewChampTeam(ChampTeam champTeam){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addNewChampTeam"))
    .queryParam("champTeam",champTeam)
;
  restTemplate.put(builder.toUriString(), null);
}


public void addNewChampEvent(ChampEvent event){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addNewChampEvent"))
    .queryParam("event",event)
;
  restTemplate.put(builder.toUriString(), null);
}


public void saveChampEvent(ChampEvent champEvent){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveChampEvent"))
    .queryParam("champEvent",champEvent)
;
  restTemplate.put(builder.toUriString(), null);
}


public void saveChamp(Champ champ){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveChamp"))
    .queryParam("champ",champ)
;
  restTemplate.put(builder.toUriString(), null);
}


}