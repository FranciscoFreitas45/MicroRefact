package com.gp.cricket.DTO;
 import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import com.gp.cricket.Request.TournamentRequest;
import com.gp.cricket.Request.Impl.TournamentRequestImpl;
import com.gp.cricket.DTO.Tournament;
import com.gp.cricket.Request.StadiumRequest;
import com.gp.cricket.Request.Impl.StadiumRequestImpl;
import com.gp.cricket.DTO.Stadium;
public class Match {

 private  Integer matchId;

 private  Integer clubOneId;

 private  Integer clubTwoId;

 private  Integer captainClubOne;

 private  Integer captainClubTwo;

 private  Integer clubOneMark;

 private  Integer clubTwoMark;

 private  Integer clubOneWicket;

 private  Integer clubTwoWicket;

 private  Integer tournementRound;

 private  LocalDate startDate;

 private  LocalDate finishDate;

 private  String startTime;

 private  Integer winTeamId;

 private  String sponsor;

 private  MatchType matchTypeId;

 private  Tournament tournamentId;

 private  Stadium stadiumId;

 private  Referee refereeId;

 private  Integer umpireOneId;

 private  Integer umpireTwoId;

 private  Integer umpireThreeId;

 private  Integer tossWinTeam;

 private  Integer clubOneViceCaptain;

 private  Integer clubTwoViceCaptain;

 private  Integer clubOneKeper;

 private  Integer clubTwoKeper;

 private  Integer manOfTheMatch;

 private  float clubOneOvers;

 private  float clubTwoOvers;

 private  Integer state;

 private  Integer testMatchId;

public Match() {
    super();
// TODO Auto-generated constructor stub
}public Match(Integer matchId, @NotNull(message = "Club1 id is mandatory") Integer clubOneId, @NotNull(message = "Club2 name is mandatory") Integer clubTwoId, @NotNull(message = "Club1 captain name is mandatory") Integer captainClubOne, @NotNull(message = "Club2 captain name is mandatory") Integer captainClubTwo, @NotNull(message = "Club1 mark is mandatory") Integer clubOneMark, @NotNull(message = "Club2 mark is mandatory") Integer clubTwoMark, @NotNull(message = "Club1 wicket is mandatory") Integer clubOneWicket, @NotNull(message = "Club2 wicket is mandatory") Integer clubTwoWicket, @NotNull(message = "tournement round is mandotory") Integer tournementRound, LocalDate startDate, LocalDate finishDate, String startTime, Integer winTeamId, String sponsor, MatchType matchTypeId, Tournament tournamentId, Stadium stadiumId, Referee refereeId, Integer umpireOneId, Integer umpireTwoId, Integer umpireThreeId, Integer tossWinTeam, Integer clubOneViceCaptain, Integer clubTwoViceCaptain, Integer clubOneKeper, Integer clubTwoKeper, Integer manOfTheMatch, float clubOneOvers, float clubTwoOvers, Integer state, Integer testMatchId) {
    super();
    this.matchId = matchId;
    this.clubOneId = clubOneId;
    this.clubTwoId = clubTwoId;
    this.captainClubOne = captainClubOne;
    this.captainClubTwo = captainClubTwo;
    this.clubOneMark = clubOneMark;
    this.clubTwoMark = clubTwoMark;
    this.clubOneWicket = clubOneWicket;
    this.clubTwoWicket = clubTwoWicket;
    this.tournementRound = tournementRound;
    this.startDate = startDate;
    this.finishDate = finishDate;
    this.startTime = startTime;
    this.winTeamId = winTeamId;
    this.sponsor = sponsor;
    this.matchTypeId = matchTypeId;
    this.tournamentId = tournamentId;
    this.stadiumId = stadiumId;
    this.refereeId = refereeId;
    this.umpireOneId = umpireOneId;
    this.umpireTwoId = umpireTwoId;
    this.umpireThreeId = umpireThreeId;
    this.tossWinTeam = tossWinTeam;
    this.clubOneViceCaptain = clubOneViceCaptain;
    this.clubTwoViceCaptain = clubTwoViceCaptain;
    this.clubOneKeper = clubOneKeper;
    this.clubTwoKeper = clubTwoKeper;
    this.manOfTheMatch = manOfTheMatch;
    this.clubOneOvers = clubOneOvers;
    this.clubTwoOvers = clubTwoOvers;
    this.state = state;
    this.testMatchId = testMatchId;
}
public LocalDate getStartDate(){
    return startDate;
}


public void setClubTwoOvers(float clubTwoOvers){
    this.clubTwoOvers = clubTwoOvers;
}


public Integer getWinTeamId(){
    return winTeamId;
}


public Integer getUmpireTwoId(){
    return umpireTwoId;
}


public LocalDate getFinishDate(){
    return finishDate;
}


public Integer getClubTwoId(){
    return clubTwoId;
}


public Integer getTestMatchId(){
    return testMatchId;
}


public String getStartTime(){
    return startTime;
}


public float getClubOneOvers(){
    return clubOneOvers;
}


public Integer getUmpireOneId(){
    return umpireOneId;
}


public void setStartDate(LocalDate startDate){
    this.startDate = startDate;
}


public Integer getUmpireThreeId(){
    return umpireThreeId;
}


public Integer getTournamentIdValue(){
    return this.tournamentId.getTournamentId();
}


public void setCaptainClubOne(Integer captainClubOne){
    this.captainClubOne = captainClubOne;
}


public void setClubTwoId(Integer clubTwoId){
    this.clubTwoId = clubTwoId;
}


public Integer getTournementRound(){
    return tournementRound;
}


public Integer getCaptainClubOne(){
    return captainClubOne;
}


public Integer getClubOneId(){
    return clubOneId;
}


public Integer getClubOneMark(){
    return clubOneMark;
}


public MatchType getMatchTypeId(){
    return matchTypeId;
}


public float getClubTwoOvers(){
    return clubTwoOvers;
}


public Tournament getTournamentId(){
    return tournamentId;
}


public void setUmpireThreeId(Integer umpireThreeId){
    this.umpireThreeId = umpireThreeId;
}


public Integer getClubOneViceCaptain(){
    return clubOneViceCaptain;
}


public Integer getCaptainClubTwo(){
    return captainClubTwo;
}


public void setTournamentId(Tournament tournamentId){
    this.tournamentId = tournamentId;
}


public String getSponsor(){
    return sponsor;
}


public void setStadiumId(Stadium stadiumId){
    this.stadiumId = stadiumId;
}


public Stadium getStadiumId(){
    return stadiumId;
}


public Integer getManOfTheMatch(){
    return manOfTheMatch;
}


public Integer getClubTwoViceCaptain(){
    return clubTwoViceCaptain;
}


public Integer getTossWinTeam(){
    return tossWinTeam;
}


public Integer getMatchId(){
    return matchId;
}


public Integer getClubOneWicket(){
    return clubOneWicket;
}


public Integer getClubTwoKeper(){
    return clubTwoKeper;
}


public Referee getRefereeId(){
    return refereeId;
}


public Integer getClubOneKeper(){
    return clubOneKeper;
}


public Integer getState(){
    return state;
}


public Integer getClubTwoMark(){
    return clubTwoMark;
}


public Integer getClubTwoWicket(){
    return clubTwoWicket;
}


public void setClubOneId(Integer clubOneId){
    this.clubOneId = clubOneId;
}


public void setWinTeamId(Integer winTeamId){
    this.winTeamId = winTeamId;
}


}