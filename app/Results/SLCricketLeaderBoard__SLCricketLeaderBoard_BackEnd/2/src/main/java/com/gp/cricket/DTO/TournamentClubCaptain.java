package com.gp.cricket.DTO;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.sun.istack.NotNull;
public class TournamentClubCaptain {

 private  Integer tournamentClubCaptainId;

 private  Integer captainId;

 private  Integer viceCaptainId;

 private  TournamentClub tournamentClubId;

public TournamentClubCaptain() {
    super();
// TODO Auto-generated constructor stub
}public TournamentClubCaptain(Integer tournamentClubCaptainId, Integer captainId, Integer viceCaptainId, TournamentClub tournamentClubId) {
    super();
    this.tournamentClubCaptainId = tournamentClubCaptainId;
    this.captainId = captainId;
    this.viceCaptainId = viceCaptainId;
    this.tournamentClubId = tournamentClubId;
}
public Integer getCaptainId(){
    return captainId;
}


public Integer getTournamentClubCaptainId(){
    return tournamentClubCaptainId;
}


public TournamentClub getTournamentClubId(){
    return tournamentClubId;
}


public Integer getViceCaptainId(){
    return viceCaptainId;
}


}