package ink.champ.models;
 import javax.persistence;
import ink.champ.Interface.User;
@Entity(name = "player_roles")
public class PlayerRole {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

 private  int role;

 private  int request;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "player_id")
 private  Player player;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "user_id")
 private  User user;

/**
 * Конструктор роли игрока
 */
public PlayerRole() {
}/**
 * Конструктор роли игрока
 * @param player Игрока
 * @param user Пользователь
 * @param role Роль
 */
public PlayerRole(Player player, User user, int role) {
    this.player = player;
    this.user = user;
    this.role = role;
}
public void setRequest(int request){
    this.request = request;
}


public Player getPlayer(){
    return player;
}


public int getRequest(){
    return request;
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


public void setPlayer(Player player){
    this.player = player;
}


public Long getId(){
    return id;
}


public void setUser(User user){
    this.user = user;
}


}