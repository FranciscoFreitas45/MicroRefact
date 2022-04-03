package com.gp.cricket.entity;
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
@Entity
@Table(name = "tournament_club_player")
public class TournamentClubPlayer {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "tournament_club_player_id")
 private  Integer tournamentClubPlayerId;

@NotNull
@ManyToOne
@JoinColumn(name = "tournament_club_id", referencedColumnName = "tournament_club_id")
 private  TournamentClub tournamentClubId;

@Transient
 private  Player playerId;

@Column(name = "playerIdv2")
 private Integer playerIdv2;

@Transient
 private PlayerRequest playerrequest = new PlayerRequestImpl();;

public TournamentClubPlayer() {
}public TournamentClubPlayer(Integer tournamentClubPlayerId, @NotNull TournamentClub tournamentClubId, @NotNull Player playerId) {
    super();
    this.tournamentClubPlayerId = tournamentClubPlayerId;
    this.tournamentClubId = tournamentClubId;
    this.playerId = playerId;
}
public void setTournamentClubId(TournamentClub tournamentClubId){
    this.tournamentClubId = tournamentClubId;
}


public void setPlayerId(Player playerId){
 playerrequest.setPlayerId(playerId,this.playerIdv2);
}



@Override
public String toString(){
    return "TournamentClubPlayer [tournamentClubPlayerId=" + tournamentClubPlayerId + ", tournamentClubId=" + tournamentClubId + ", playerId=" + playerId + "]";
}


public TournamentClub getTournamentClubId(){
    return tournamentClubId;
}


public Integer getTournamentClubPlayerId(){
    return tournamentClubPlayerId;
}


public void setTournamentClubPlayerId(Integer tournamentClubPlayerId){
    this.tournamentClubPlayerId = tournamentClubPlayerId;
}


public Player getPlayerId(){
  this.playerId = playerrequest.getPlayerId(this.playerIdv2);
return this.playerId;
}


}