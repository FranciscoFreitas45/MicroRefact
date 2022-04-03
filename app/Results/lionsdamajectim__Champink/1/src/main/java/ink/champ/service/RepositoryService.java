package ink.champ.service;
 import ink.champ.models;
import ink.champ.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import ink.champ.Interface.ChampRepository;
import ink.champ.Interface.TeamRepository;
import ink.champ.Interface.PlayerRepository;
import ink.champ.Interface.UserRepository;
@Service
public class RepositoryService {

@Autowired
 private  SportRepository sports;

@Autowired
 private  ChampRepository champs;

@Autowired
 private  ChampTeamRepository champTeams;

@Autowired
 private  ChampEventRepository champEvents;

@Autowired
 private  ChampRoleRepository champRoles;

@Autowired
 private  TeamRepository teams;

@Autowired
 private  TeamPlayerRepository teamPlayers;

@Autowired
 private  TeamRoleRepository teamRoles;

@Autowired
 private  PlayerRepository players;

@Autowired
 private  PlayerRoleRepository playerRoles;

@Autowired
 private  UserRepository users;

 private  Sort sortDescId;


public void addNewTeam(Team team){
    teams.save(team);
}


public void deleteTeam(Team team){
    teams.delete(team);
}


public List<Player> getUserPlayersRole(User user,int role,String search){
    return players.findPlayersByUserRole(user, role, search);
}


public ChampTeam getChampTeamById(Long id){
    return champTeams.findById(id).orElse(null);
}


public List<Team> getUserTeamsRole(User user,int role,String search){
    return teams.findTeamsByUserRole(user, role, search);
}


public void saveTeam(Team team){
    teams.save(team);
}


public List<Team> getGlobalTeams(String search){
    return teams.findTeamsByPrivatIsFalseAndNameContainingIgnoreCase(search, sortDescId);
}


public void savePlayer(Player player){
    players.save(player);
}


public ChampRole getChampRoleById(Long id){
    return champRoles.findById(id).orElse(null);
}


public void addNewUser(User user){
    saveUser(user);
}


public List<User> getUsers(String search){
    return users.findUserByNameContainingIgnoreCaseOrUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(search, search, search, sortDescId);
}


public Team getTeamById(Long id){
    return teams.findById(id).orElse(null);
}


public void saveChampEvent(ChampEvent champEvent){
    champEvents.save(champEvent);
}


public List<Player> getPlayers(String search){
    return players.findPlayersByNameContainingIgnoreCase(search, sortDescId);
}


public Champ getChampById(Long id){
    return champs.findById(id).orElse(null);
}


public void saveUser(User user){
    users.save(user);
}


public void saveTeamRole(TeamRole role){
    teamRoles.save(role);
}


public TeamPlayer getTeamPlayerById(Long id){
    return teamPlayers.findById(id).orElse(null);
}


public List<Team> getUserTeamsAll(User user,String search){
    return teams.findTeamsByUserAll(user, search);
}


public void addNewTeamRole(TeamRole role){
    saveTeamRole(role);
}


public void saveChamp(Champ champ){
    champs.save(champ);
}


public List<Champ> getGlobalChamps(String search){
    return champs.findChampsByPrivatIsFalseAndNameContainingIgnoreCase(search, sortDescId);
}


public void deleteChampEvent(ChampEvent champEvent){
    champEvents.delete(champEvent);
}


public List<Team> getTeams(String search){
    return teams.findTeamsByNameContainingIgnoreCase(search, sortDescId);
}


public void addNewPlayer(Player player){
    players.save(player);
}


public ChampEvent getChampEventById(Long id){
    return champEvents.findById(id).orElse(null);
}


public void addNewChampRole(ChampRole role){
    saveChampRole(role);
}


public void deleteTeamPlayer(TeamPlayer teamPlayer){
    teamPlayers.delete(teamPlayer);
}


public List<Champ> getUserChampsTeamNotIn(User user,Team team){
    return champs.findChampsByUserRoleAndTeamNotIn(team, user);
}


public void addNewChamp(Champ champ){
    champs.save(champ);
}


public void deleteUser(User user){
    users.delete(user);
}


public void addNewTeamPlayer(TeamPlayer teamPlayer){
    teamPlayers.save(teamPlayer);
}


public List<Player> getUserPlayersAll(User user,String search){
    return players.findPlayersByUserAll(user, search);
}


public List<Player> getUserPlayersNotInTeam(User user,Team team){
    return players.findPlayersByUserRoleAndNotInTeam(team, user);
}


public Sport getSportById(Long id){
    return sports.findById(id).orElse(null);
}


public void addNewPlayerRole(PlayerRole role){
    savePlayerRole(role);
}


public User getUserById(Long id){
    return users.findById(id).orElse(null);
}


public void deleteChamp(Champ champ){
    champs.delete(champ);
}


public List<Sport> getSports(String search){
    return sports.findSportByNameContainingIgnoreCase(search, sortDescId);
}


public TeamRole getTeamRoleById(Long id){
    return teamRoles.findById(id).orElse(null);
}


public void deleteChampTeam(ChampTeam champTeam){
    champTeams.delete(champTeam);
}


public List<Champ> getUserChampsAll(User user,String search){
    return champs.findChampsByUserAll(user, search);
}


public List<Player> getGlobalPlayers(String search){
    return players.findPlayersByPrivatIsFalseAndNameContainingIgnoreCase(search, sortDescId);
}


public void addNewChampEvent(ChampEvent event){
    champEvents.save(event);
}


public void addNewChampTeam(ChampTeam champTeam){
    champTeams.save(champTeam);
}


public void saveChampRole(ChampRole role){
    champRoles.save(role);
}


public List<Champ> getChamps(String search){
    return champs.findChampsByNameContainingIgnoreCase(search, sortDescId);
}


public User getUserByUsername(String username){
    return users.findByUsername(username);
}


public Player getPlayerById(Long id){
    return players.findById(id).orElse(null);
}


public PlayerRole getPlayerRoleById(Long id){
    return playerRoles.findById(id).orElse(null);
}


public void savePlayerRole(PlayerRole role){
    playerRoles.save(role);
}


public void deletePlayer(Player player){
    players.delete(player);
}


public List<Team> getUserTeamsPlayerNotIn(User user,Player player){
    return teams.findTeamsByUserRoleAndPlayerNotIn(player, user);
}


public List<Team> getUserTeamsNotInChamp(User user,Champ champ){
    return teams.findTeamsByUserRoleAndNotInChamp(champ, user);
}


public List<Champ> getUserChampsRole(User user,int role,String search){
    return champs.findChampsByUserRole(user, role, search);
}


}