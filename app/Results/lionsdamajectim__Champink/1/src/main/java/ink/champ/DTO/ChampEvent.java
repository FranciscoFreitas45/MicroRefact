package ink.champ.DTO;
 import javax.persistence;
import java.util.Date;
import ink.champ.Interface.Team;
import ink.champ.Interface.Team;
public class ChampEvent {

 private  Long id;

 private  Champ champ;

 private  Team team1;

 private  Team team2;

 private  Date timestamp;

 private  int gol1;

 private  int gol2;

 private  int pen1;

 private  int pen2;

/**
 * Конструктор события чемпионата
 */
public ChampEvent() {
}/**
 * Конструктор события чемпионата
 * @param champ Чемпионат
 * @param team1 Команда 1
 * @param team2 Команда 2
 * @param timestamp Дата и время
 */
public ChampEvent(Champ champ, Team team1, Team team2, Date timestamp) {
    this.champ = champ;
    this.team1 = team1;
    this.team2 = team2;
    this.timestamp = timestamp;
}
public void setGol1(int gol1){
    this.gol1 = gol1;
}


public Champ getChamp(){
    return champ;
}


public Long getId(){
    return id;
}


public int getPen2(){
    return pen2;
}


public int getPen1(){
    return pen1;
}


public void setTeam2(Team team2){
    this.team2 = team2;
}


public void setTeam1(Team team1){
    this.team1 = team1;
}


public Team getTeam2(){
    return team2;
}


public Team getTeam1(){
    return team1;
}


public void setChamp(Champ champ){
    this.champ = champ;
}


public void setPen1(int pen1){
    this.pen1 = pen1;
}


public void setPen2(int pen2){
    this.pen2 = pen2;
}


public Date getTimestamp(){
    return timestamp;
}


public void setId(Long id){
    this.id = id;
}


public void setGol2(int gol2){
    this.gol2 = gol2;
}


public int getGol2(){
    return gol2;
}


public int getGol1(){
    return gol1;
}


public void setTimestamp(Date timestamp){
    this.timestamp = timestamp;
}


public void setScore(int gol1,int gol2,int pen1,int pen2){
    this.gol1 = gol1;
    this.gol2 = gol2;
    this.pen1 = pen1;
    this.pen2 = pen2;
}


}