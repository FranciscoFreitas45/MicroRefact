package com.gp.cricket.DTO;
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
public class SelectedPlayer {

 private  Integer selectedPlayerId;

 private  Match matchId;

 private  Player playerId;

 private  Integer state;

 private Integer matchIdv2;

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


public Integer getState(){
    return state;
}


public Integer getSelectedPlayerId(){
    return selectedPlayerId;
}


public Player getPlayerId(){
    return playerId;
}


}