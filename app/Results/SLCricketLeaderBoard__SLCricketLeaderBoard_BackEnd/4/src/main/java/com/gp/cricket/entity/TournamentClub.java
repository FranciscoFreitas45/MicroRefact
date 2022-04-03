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
import com.gp.cricket.Request.ClubRequest;
import com.gp.cricket.Request.Impl.ClubRequestImpl;
import com.gp.cricket.DTO.Club;
import com.gp.cricket.Request.TournamentRequest;
import com.gp.cricket.Request.Impl.TournamentRequestImpl;
import com.gp.cricket.DTO.Tournament;
@Entity
@Table(name = "tournament_club")
public class TournamentClub {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "tournament_club_id")
 private  Integer tournamentClubId;

@NotNull
 private  Byte status;

@Transient
 private  Club clubId;

@Transient
 private  Tournament tournamentId;

@Column(name = "clubIdv2")
 private Integer clubIdv2;

@Transient
 private ClubRequest clubrequest = new ClubRequestImpl();;

@Column(name = "tournamentIdv2")
 private Integer tournamentIdv2;

@Transient
 private TournamentRequest tournamentrequest = new TournamentRequestImpl();;

public TournamentClub() {
}public TournamentClub(Integer tournamentClubId, @NotNull Byte status, @NotNull Club clubId, @NotNull Tournament tournamentId) {
    super();
    this.tournamentClubId = tournamentClubId;
    this.status = status;
    this.clubId = clubId;
    this.tournamentId = tournamentId;
}
public void setTournamentClubId(Integer tournamentClubId){
    this.tournamentClubId = tournamentClubId;
}


public void setClubId(Club clubId){
 clubrequest.setClubId(clubId,this.clubIdv2);
}



public Club getClubId(){
  this.clubId = clubrequest.getClubId(this.clubIdv2);
return this.clubId;
}


public void setTournamentId(Tournament tournamentId){
 tournamentrequest.setTournamentId(tournamentId,this.tournamentIdv2);
}



@Override
public String toString(){
    return "TournamentClub [tournamentClubId=" + tournamentClubId + ", status=" + status + ", clubId=" + clubId + ", tournamentId=" + tournamentId + "]";
}


public Byte getStatus(){
    return status;
}


public Tournament getTournamentId(){
  this.tournamentId = tournamentrequest.getTournamentId(this.tournamentIdv2);
return this.tournamentId;
}


public Integer getTournamentClubId(){
    return tournamentClubId;
}


public void setStatus(Byte status){
    this.status = status;
}


}