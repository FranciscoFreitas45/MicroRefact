package ink.champ.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RepositoryServiceController {

 private RepositoryService repositoryservice;


@GetMapping
("/getUserByUsername")
public User getUserByUsername(@RequestParam(name = "username") String username){
  return repositoryservice.getUserByUsername(username);
}


@GetMapping
("/getChampById")
public Champ getChampById(@RequestParam(name = "id") Long id){
  return repositoryservice.getChampById(id);
}


@GetMapping
("/getSports")
public List<Sport> getSports(@RequestParam(name = "search") String search){
  return repositoryservice.getSports(search);
}


@PutMapping
("/addNewChampRole")
public void addNewChampRole(@RequestParam(name = "role") ChampRole role){
repositoryservice.addNewChampRole(role);
}


@PutMapping
("/saveChampRole")
public void saveChampRole(@RequestParam(name = "role") ChampRole role){
repositoryservice.saveChampRole(role);
}


@GetMapping
("/getChampRoleById")
public ChampRole getChampRoleById(@RequestParam(name = "id") Long id){
  return repositoryservice.getChampRoleById(id);
}


@GetMapping
("/getUserTeamsNotInChamp")
public List<Team> getUserTeamsNotInChamp(@RequestParam(name = "user") User user,@RequestParam(name = "champ") Champ champ){
  return repositoryservice.getUserTeamsNotInChamp(user,champ);
}


@GetMapping
("/getChampEventById")
public ChampEvent getChampEventById(@RequestParam(name = "id") Long id){
  return repositoryservice.getChampEventById(id);
}


@GetMapping
("/getChampTeamById")
public ChampTeam getChampTeamById(@RequestParam(name = "id") Long id){
  return repositoryservice.getChampTeamById(id);
}


@PutMapping
("/deleteChampTeam")
public void deleteChampTeam(@RequestParam(name = "champTeam") ChampTeam champTeam){
repositoryservice.deleteChampTeam(champTeam);
}


@PutMapping
("/deleteChampEvent")
public void deleteChampEvent(@RequestParam(name = "champEvent") ChampEvent champEvent){
repositoryservice.deleteChampEvent(champEvent);
}


@PutMapping
("/deleteChamp")
public void deleteChamp(@RequestParam(name = "champ") Champ champ){
repositoryservice.deleteChamp(champ);
}


@GetMapping
("/getSportById")
public Sport getSportById(@RequestParam(name = "id") Long id){
  return repositoryservice.getSportById(id);
}


@PutMapping
("/addNewChamp")
public void addNewChamp(@RequestParam(name = "champ") Champ champ){
repositoryservice.addNewChamp(champ);
}


@PutMapping
("/addNewChampTeam")
public void addNewChampTeam(@RequestParam(name = "champTeam") ChampTeam champTeam){
repositoryservice.addNewChampTeam(champTeam);
}


@PutMapping
("/addNewChampEvent")
public void addNewChampEvent(@RequestParam(name = "event") ChampEvent event){
repositoryservice.addNewChampEvent(event);
}


@PutMapping
("/saveChampEvent")
public void saveChampEvent(@RequestParam(name = "champEvent") ChampEvent champEvent){
repositoryservice.saveChampEvent(champEvent);
}


@PutMapping
("/saveChamp")
public void saveChamp(@RequestParam(name = "champ") Champ champ){
repositoryservice.saveChamp(champ);
}


@GetMapping
("/getUserById")
public User getUserById(@RequestParam(name = "id") Long id){
  return repositoryservice.getUserById(id);
}


@PutMapping
("/deleteUser")
public void deleteUser(@RequestParam(name = "user") User user){
repositoryservice.deleteUser(user);
}


@PutMapping
("/saveUser")
public void saveUser(@RequestParam(name = "user") User user){
repositoryservice.saveUser(user);
}


@PutMapping
("/addNewUser")
public void addNewUser(@RequestParam(name = "user") User user){
repositoryservice.addNewUser(user);
}


@GetMapping
("/getTeamById")
public Team getTeamById(@RequestParam(name = "id") Long id){
  return repositoryservice.getTeamById(id);
}


@PutMapping
("/addNewTeamRole")
public void addNewTeamRole(@RequestParam(name = "role") TeamRole role){
repositoryservice.addNewTeamRole(role);
}


@PutMapping
("/saveTeamRole")
public void saveTeamRole(@RequestParam(name = "role") TeamRole role){
repositoryservice.saveTeamRole(role);
}


@GetMapping
("/getTeamRoleById")
public TeamRole getTeamRoleById(@RequestParam(name = "id") Long id){
  return repositoryservice.getTeamRoleById(id);
}


@GetMapping
("/getUserPlayersNotInTeam")
public List<Player> getUserPlayersNotInTeam(@RequestParam(name = "user") User user,@RequestParam(name = "team") Team team){
  return repositoryservice.getUserPlayersNotInTeam(user,team);
}


@GetMapping
("/getUserChampsTeamNotIn")
public List<Champ> getUserChampsTeamNotIn(@RequestParam(name = "user") User user,@RequestParam(name = "team") Team team){
  return repositoryservice.getUserChampsTeamNotIn(user,team);
}


@GetMapping
("/getTeamPlayerById")
public TeamPlayer getTeamPlayerById(@RequestParam(name = "id") Long id){
  return repositoryservice.getTeamPlayerById(id);
}


@PutMapping
("/deleteTeamPlayer")
public void deleteTeamPlayer(@RequestParam(name = "teamPlayer") TeamPlayer teamPlayer){
repositoryservice.deleteTeamPlayer(teamPlayer);
}


@PutMapping
("/deleteTeam")
public void deleteTeam(@RequestParam(name = "team") Team team){
repositoryservice.deleteTeam(team);
}


@PutMapping
("/addNewTeam")
public void addNewTeam(@RequestParam(name = "team") Team team){
repositoryservice.addNewTeam(team);
}


@PutMapping
("/addNewTeamPlayer")
public void addNewTeamPlayer(@RequestParam(name = "teamPlayer") TeamPlayer teamPlayer){
repositoryservice.addNewTeamPlayer(teamPlayer);
}


@PutMapping
("/saveTeam")
public void saveTeam(@RequestParam(name = "team") Team team){
repositoryservice.saveTeam(team);
}


@GetMapping
("/getPlayerById")
public Player getPlayerById(@RequestParam(name = "id") Long id){
  return repositoryservice.getPlayerById(id);
}


@PutMapping
("/addNewPlayerRole")
public void addNewPlayerRole(@RequestParam(name = "role") PlayerRole role){
repositoryservice.addNewPlayerRole(role);
}


@PutMapping
("/savePlayerRole")
public void savePlayerRole(@RequestParam(name = "role") PlayerRole role){
repositoryservice.savePlayerRole(role);
}


@GetMapping
("/getPlayerRoleById")
public PlayerRole getPlayerRoleById(@RequestParam(name = "id") Long id){
  return repositoryservice.getPlayerRoleById(id);
}


@GetMapping
("/getUserTeamsPlayerNotIn")
public List<Team> getUserTeamsPlayerNotIn(@RequestParam(name = "user") User user,@RequestParam(name = "player") Player player){
  return repositoryservice.getUserTeamsPlayerNotIn(user,player);
}


@PutMapping
("/deletePlayer")
public void deletePlayer(@RequestParam(name = "player") Player player){
repositoryservice.deletePlayer(player);
}


@PutMapping
("/addNewPlayer")
public void addNewPlayer(@RequestParam(name = "player") Player player){
repositoryservice.addNewPlayer(player);
}


@PutMapping
("/savePlayer")
public void savePlayer(@RequestParam(name = "player") Player player){
repositoryservice.savePlayer(player);
}


}