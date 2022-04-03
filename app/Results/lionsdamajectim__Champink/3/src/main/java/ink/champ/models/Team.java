package ink.champ.models;
 import ink.champ.service.AppService;
import javax.persistence;
import java.util.Set;
import ink.champ.Interface.User;
import ink.champ.DTO.User;
@Entity(name = "teams")
public class Team {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Column(length = 25)
 private  String name;

 private  boolean privat;

@ManyToOne
@JoinColumn(name = "user_id")
 private  User user;

@OrderBy("position ASC")
@OneToMany(targetEntity = TeamPlayer.class, mappedBy = "team", orphanRemoval = true, fetch = FetchType.LAZY)
 private  Set<TeamPlayer> players;

@OneToMany(targetEntity = ChampTeam.class, mappedBy = "team", orphanRemoval = true, fetch = FetchType.LAZY)
@OrderBy("id ASC")
 private  Set<ChampTeam> champs;

@OneToMany(targetEntity = ChampEvent.class, mappedBy = "team1", orphanRemoval = true, fetch = FetchType.LAZY)
 private  Set<ChampEvent> champEventsH;

@OneToMany(targetEntity = ChampEvent.class, mappedBy = "team2", orphanRemoval = true, fetch = FetchType.LAZY)
 private  Set<ChampEvent> champEventsA;

@OneToMany(targetEntity = TeamRole.class, mappedBy = "team", orphanRemoval = true, fetch = FetchType.LAZY)
 private  Set<TeamRole> roles;

/**
 * Конструктор команды
 */
public Team() {
}/**
 * Конструктор команды
 * @param name Название
 * @param privat Приватность
 * @param user Пользователь
 */
public Team(String name, boolean privat, User user) {
    this.name = name;
    this.privat = privat;
    this.user = user;
}
public void setName(String name){
    this.name = name;
}


public int getUserRole(User user){
    TeamRole role = getTeamRole(user);
    return role == null ? AppService.Role.NONE : role.getRole();
}


public int getPlayersCount(){
    return players.size();
}


public int getChampsCount(){
    return champs.size();
}


public void setPlayers(Set<TeamPlayer> players){
    this.players = players;
}


public String getName(){
    return name;
}


public Set<ChampTeam> getChamps(){
    return champs;
}


public User getUser(){
    return user;
}


public int getUserRequest(User user){
    TeamRole role = getTeamRole(user);
    return role == null ? AppService.Role.NONE : role.getRequest();
}


public Long getId(){
    return id;
}


public Set<ChampEvent> getChampEventsA(){
    return champEventsA;
}


public boolean isPrivate(){
    return privat;
}


public void setChampEventsH(Set<ChampEvent> champEvents){
    this.champEventsH = champEvents;
}


public TeamRole getTeamRole(User user){
    if (user != null) {
        for (TeamRole role : roles) {
            if (role.getUser().getId().equals(user.getId()))
                return role;
        }
    }
    return null;
}


public Set<ChampEvent> getChampEventsH(){
    return champEventsH;
}


public void setChamps(Set<ChampTeam> champs){
    this.champs = champs;
}


public void setChampEventsA(Set<ChampEvent> champEvents){
    this.champEventsA = champEvents;
}


public void setId(Long id){
    this.id = id;
}


public void setUser(User user){
    this.user = user;
}


public void setRoles(Set<TeamRole> roles){
    this.roles = roles;
}


public Set<TeamPlayer> getPlayers(){
    return players;
}


public void setPrivate(boolean privat){
    this.privat = privat;
}


public Set<TeamRole> getRoles(){
    return roles;
}


}