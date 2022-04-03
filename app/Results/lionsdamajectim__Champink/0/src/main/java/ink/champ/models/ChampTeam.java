package ink.champ.models;
 import javax.persistence;
import ink.champ.Interface.Team;
@Entity(name = "champ_teams")
public class ChampTeam {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "champ_id")
 private  Champ champ;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "team_id")
 private  Team team;

/**
 * Конструктор команды чемпионата
 */
public ChampTeam() {
}/**
 * Конструктор команды чемпионата
 * @param champ Чемпионат
 * @param team Команда
 */
public ChampTeam(Champ champ, Team team) {
    this.champ = champ;
    this.team = team;
}
public void setChamp(Champ champ){
    this.champ = champ;
}


public Team getTeam(){
    return team;
}


public Champ getChamp(){
    return champ;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public void setTeam(Team team){
    this.team = team;
}


}