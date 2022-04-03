package com.gp.cricket.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.gp.cricket.Request.MatchRequest;
import com.gp.cricket.Request.Impl.MatchRequestImpl;
import com.gp.cricket.DTO.Match;
@Entity
@Table(name = "selected_player")
public class SelectedPlayer {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "selected_player_id")
 private  Integer selectedPlayerId;

@Transient
 private  Match matchId;

@ManyToOne
@JoinColumn(name = "player_id", referencedColumnName = "player_id")
 private  Player playerId;

@Column(name = "state")
 private  Integer state;

@Column(name = "matchIdv2")
 private Integer matchIdv2;

@Transient
 private MatchRequest matchrequest = new MatchRequestImpl();;

public SelectedPlayer() {
    super();
// TODO Auto-generated constructor stub
}public SelectedPlayer(Integer selectedPlayerId, Match matchId, Player playerId, Integer state) {
    super();
    this.selectedPlayerId = selectedPlayerId;
    this.matchId = matchId;
    this.playerId = playerId;
    this.state = state;
}
public Match getMatchId(){
  this.matchId = matchrequest.getMatchId(this.matchIdv2);
return this.matchId;
}


public void setMatchId(Match matchId){
 matchrequest.setMatchId(matchId,this.matchIdv2);
}



public Integer getState(){
    return state;
}


public Integer getSelectedPlayerId(){
    return selectedPlayerId;
}


public void setPlayerId(Player playerId){
    this.playerId = playerId;
}


public void setState(Integer state){
    this.state = state;
}


public void setSelectedPlayerId(Integer selectedPlayerId){
    this.selectedPlayerId = selectedPlayerId;
}


public Player getPlayerId(){
    return playerId;
}


}