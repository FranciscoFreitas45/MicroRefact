package ink.champ.DTO;
 import javax.persistence;
public class TeamPlayer {

 private  Long id;

 private  Team team;

 private  Player player;

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