package ink.champ.DTO;
 import ink.champ.service.AppService;
import javax.persistence;
import java.util.Set;
import ink.champ.Interface.Sport;
import ink.champ.Interface.User;
public class Champ {

 private  Long id;

 private  String name;

 private  String format;

 private  boolean privat;

 private  Sport sport;

 private  User user;

 private  Set<ChampTeam> teams;

 private  Set<ChampRole> roles;

 private  Set<ChampEvent> events;

/**
 * Конструктор чемпионата
 */
public Champ() {
}/**
 * Конструктор чемпионата
 * @param name Название
 * @param format Формат
 * @param privat Приватность
 * @param sport Вид спорта
 * @param user Пользователь
 */
public Champ(String name, String format, boolean privat, Sport sport, User user) {
    this.name = name;
    this.format = format;
    this.privat = privat;
    this.sport = sport;
    this.user = user;
}
public void setName(String name){
    this.name = name;
}


public int getEventsCount(){
    return events.size();
}


public int getUserRole(User user){
    ChampRole role = getChampRole(user);
    return role == null ? AppService.Role.NONE : role.getRole();
}


public String getName(){
    return name;
}


public void setSport(Sport sport){
    this.sport = sport;
}


public User getUser(){
    return user;
}


public void setTeams(Set<ChampTeam> teams){
    this.teams = teams;
}


public int getUserRequest(User user){
    ChampRole role = getChampRole(user);
    return role == null ? AppService.Role.NONE : role.getRequest();
}


public Long getId(){
    return id;
}


public boolean isPrivate(){
    return privat;
}


public int getTeamsCount(){
    return teams.size();
}


public void setEvents(Set<ChampEvent> events){
    this.events = events;
}


public Set<ChampTeam> getTeams(){
    return teams;
}


public Set<ChampEvent> getEvents(){
    return events;
}


public ChampRole getChampRole(User user){
    if (user != null) {
        for (ChampRole role : roles) {
            if (role.getUser().getId().equals(user.getId()))
                return role;
        }
    }
    return null;
}


public void setFormat(String format){
    this.format = format;
}


public Sport getSport(){
    return sport;
}


public void setId(Long id){
    this.id = id;
}


public String getFormat(){
    return format;
}


public void setUser(User user){
    this.user = user;
}


public void setRoles(Set<ChampRole> roles){
    this.roles = roles;
}


public void setPrivate(boolean privat){
    this.privat = privat;
}


public Set<ChampRole> getRoles(){
    return roles;
}


}