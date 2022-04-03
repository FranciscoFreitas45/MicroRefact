package ink.champ.models;
 import javax.persistence;
import ink.champ.Interface.User;
@Entity(name = "team_roles")
public class TeamRole {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

 private  int role;

 private  int request;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "team_id")
 private  Team team;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "user_id")
 private  User user;

/**
 * Конструктор роли команды
 */
public TeamRole() {
}/**
 * Конструктор роли команды
 * @param team Команды
 * @param user Пользователь
 * @param role Роль
 */
public TeamRole(Team team, User user, int role) {
    this.team = team;
    this.user = user;
    this.role = role;
}
public void setRequest(int request){
    this.request = request;
}


public int getRequest(){
    return request;
}


public Team getTeam(){
    return team;
}


public void setRole(int role){
    this.role = role;
}


public User getUser(){
    return user;
}


public int getRole(){
    return role;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public void setUser(User user){
    this.user = user;
}


public void setTeam(Team team){
    this.team = team;
}


}