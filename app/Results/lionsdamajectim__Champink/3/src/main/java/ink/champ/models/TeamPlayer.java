package ink.champ.models;
 import javax.persistence;
import ink.champ.Interface.Player;
@Entity(name = "team_players")
public class TeamPlayer {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "team_id")
 private  Team team;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "player_id")
 private  Player player;

@Column(length = 15)
 private  String position;

/**
 * Конструктор игрока команды
 */
public TeamPlayer() {
}/**
 * Конструктор игрока команды
 * @param team Команда
 * @param player Игрок
 * @param position Позиция
 */
public TeamPlayer(Team team, Player player, String position) {
    this.team = team;
    this.player = player;
    this.position = position;
}
public Player getPlayer(){
    return player;
}


public String getPosition(){
    return position;
}


public Team getTeam(){
    return team;
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


public void setPosition(String position){
    this.position = position;
}


public void setTeam(Team team){
    this.team = team;
}


}