package com.gp.cricket.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.gp.cricket.Request.PlayerRequest;
import com.gp.cricket.Request.Impl.PlayerRequestImpl;
import com.gp.cricket.DTO.Player;
public class TournamentClubPlayer {

 private  Integer tournamentClubPlayerId;

 private  TournamentClub tournamentClubId;

 private  Player playerId;

public TournamentClubPlayer() {
}public TournamentClubPlayer(Integer tournamentClubPlayerId, @NotNull TournamentClub tournamentClubId, @NotNull Player playerId) {
    super();
    this.tournamentClubPlayerId = tournamentClubPlayerId;
    this.tournamentClubId = tournamentClubId;
    this.playerId = playerId;
}
public void setPlayerId(Player playerId){
    this.playerId = playerId;
}


public TournamentClub getTournamentClubId(){
    return tournamentClubId;
}


public Integer getTournamentClubPlayerId(){
    return tournamentClubPlayerId;
}


public Player getPlayerId(){
    return playerId;
}


}