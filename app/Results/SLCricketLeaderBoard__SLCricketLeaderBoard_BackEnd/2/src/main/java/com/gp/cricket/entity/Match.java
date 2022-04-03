package com.gp.cricket.entity;
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
@Entity
@Table(name = "match_record")
public class Match {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "match_id")
 private  Integer matchId;

@NotNull(message = "Club1 id is mandatory")
@Column(name = "club_one_id")
 private  Integer clubOneId;

@NotNull(message = "Club2 name is mandatory")
@Column(name = "club_two_id")
 private  Integer clubTwoId;

@NotNull(message = "Club1 captain name is mandatory")
@Column(name = "captain_club_one")
 private  Integer captainClubOne;

@NotNull(message = "Club2 captain name is mandatory")
@Column(name = "captain_club_two")
 private  Integer captainClubTwo;

@NotNull(message = "Club1 mark is mandatory")
@Column(name = "club_one_mark")
 private  Integer clubOneMark;

@NotNull(message = "Club2 mark is mandatory")
@Column(name = "club_two_mark")
 private  Integer clubTwoMark;

@NotNull(message = "Club1 wicket is mandatory")
@Column(name = "club_one_wicket")
 private  Integer clubOneWicket;

@NotNull(message = "Club2 wicket is mandatory")
@Column(name = "club_two_wicket")
 private  Integer clubTwoWicket;

@NotNull(message = "tournement round is mandotory")
@Column(name = "tournement_round")
 private  Integer tournementRound;

@Column(name = "start_date")
@DateTimeFormat(pattern = "MM-dd-YYYY")
 private  LocalDate startDate;

@Column(name = "finish_date")
@DateTimeFormat(pattern = "MM-dd-YYYY")
 private  LocalDate finishDate;

@Column(name = "start_time")
 private  String startTime;

@Column(name = "win_team_id")
 private  Integer winTeamId;

@Column(name = "sponsor")
 private  String sponsor;

@ManyToOne
@JoinColumn(name = "match_type_id", referencedColumnName = "match_type_id")
 private  MatchType matchTypeId;

@Transient
 private  Tournament tournamentId;

@Transient
 private  Stadium stadiumId;

@ManyToOne
@JoinColumn(name = "referee_id", referencedColumnName = "referee_id")
 private  Referee refereeId;

@Column(name = "umpire_one_id")
 private  Integer umpireOneId;

@Column(name = "umpire_two_id")
 private  Integer umpireTwoId;

@Column(name = "umpire_three_id")
 private  Integer umpireThreeId;

@Column(name = "toss_win_team")
 private  Integer tossWinTeam;

@Column(name = "club_one_vice_captain")
 private  Integer clubOneViceCaptain;

@Column(name = "club_two_vice_captain")
 private  Integer clubTwoViceCaptain;

@Column(name = "club_one_keper")
 private  Integer clubOneKeper;

@Column(name = "club_two_keper")
 private  Integer clubTwoKeper;

@Column(name = "man_ofthe_match")
 private  Integer manOfTheMatch;

@Column(name = "club_one_overs")
 private  float clubOneOvers;

@Column(name = "club_two_overs")
 private  float clubTwoOvers;

@Column(name = "state")
 private  Integer state;

@Column(name = "test_match_id")
 private  Integer testMatchId;

@Column(name = "tournamentIdv2")
 private Integer tournamentIdv2;

@Transient
 private TournamentRequest tournamentrequest = new TournamentRequestImpl();;

@Column(name = "stadiumIdv2")
 private Integer stadiumIdv2;

@Transient
 private StadiumRequest stadiumrequest = new StadiumRequestImpl();;

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
public void setClubOneWicket(Integer clubOneWicket){
    this.clubOneWicket = clubOneWicket;
}


public LocalDate getStartDate(){
    return startDate;
}


public void setUmpireOneId(Integer umpireOneId){
    this.umpireOneId = umpireOneId;
}


public void setClubTwoOvers(float clubTwoOvers){
    this.clubTwoOvers = clubTwoOvers;
}


public void setRefereeId(Referee refereeId){
    this.refereeId = refereeId;
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


public void setClubTwoViceCaptain(Integer clubTwoViceCaptain){
    this.clubTwoViceCaptain = clubTwoViceCaptain;
}


public String getStartTime(){
    return startTime;
}


public void setMatchTypeId(MatchType matchTypeId){
    this.matchTypeId = matchTypeId;
}


public float getClubOneOvers(){
    return clubOneOvers;
}


public void setClubTwoMark(Integer clubTwoMark){
    this.clubTwoMark = clubTwoMark;
}


public Integer getUmpireOneId(){
    return umpireOneId;
}


public void setClubTwoKeper(Integer clubTwoKeper){
    this.clubTwoKeper = clubTwoKeper;
}


public void setStartDate(LocalDate startDate){
    this.startDate = startDate;
}


public void setMatchId(Integer matchId){
    this.matchId = matchId;
}


public Integer getUmpireThreeId(){
    return umpireThreeId;
}


public void setClubOneViceCaptain(Integer clubOneViceCaptain){
    this.clubOneViceCaptain = clubOneViceCaptain;
}


public Integer getTournamentIdValue(){
    return this.tournamentId.getTournamentId();
}


public void setUmpireTwoId(Integer umpireTwoId){
    this.umpireTwoId = umpireTwoId;
}


public void setCaptainClubOne(Integer captainClubOne){
    this.captainClubOne = captainClubOne;
}


public void setClubTwoWicket(Integer clubTwoWicket){
    this.clubTwoWicket = clubTwoWicket;
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


public void setClubOneOvers(float clubOneOvers){
    this.clubOneOvers = clubOneOvers;
}


public Integer getClubOneId(){
    return clubOneId;
}


public void setManOfTheMatch(Integer manOfTheMatch){
    this.manOfTheMatch = manOfTheMatch;
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
  this.tournamentId = tournamentrequest.getTournamentId(this.tournamentIdv2);
return this.tournamentId;
}


public void setSponsor(String sponsor){
    this.sponsor = sponsor;
}


public void setUmpireThreeId(Integer umpireThreeId){
    this.umpireThreeId = umpireThreeId;
}


public void setTournementRound(Integer tournementRound){
    this.tournementRound = tournementRound;
}


public Integer getClubOneViceCaptain(){
    return clubOneViceCaptain;
}


public Integer getCaptainClubTwo(){
    return captainClubTwo;
}


public void setTossWinTeam(Integer tossWinTeam){
    this.tossWinTeam = tossWinTeam;
}


public void setTournamentId(Tournament tournamentId){
 tournamentrequest.setTournamentId(tournamentId,this.tournamentIdv2);
}



public String getSponsor(){
    return sponsor;
}


public void setCaptainClubTwo(Integer captainClubTwo){
    this.captainClubTwo = captainClubTwo;
}


public void setStadiumId(Stadium stadiumId){
 stadiumrequest.setStadiumId(stadiumId,this.stadiumIdv2);
}



public Stadium getStadiumId(){
  this.stadiumId = stadiumrequest.getStadiumId(this.stadiumIdv2);
return this.stadiumId;
}


public void setClubOneKeper(Integer clubOneKeper){
    this.clubOneKeper = clubOneKeper;
}


public Integer getManOfTheMatch(){
    return manOfTheMatch;
}


public Integer getClubTwoViceCaptain(){
    return clubTwoViceCaptain;
}


public void setStartTime(String startTime){
    this.startTime = startTime;
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


public void setFinishDate(LocalDate finishDate){
    this.finishDate = finishDate;
}


public Integer getClubTwoKeper(){
    return clubTwoKeper;
}


public void setClubOneMark(Integer clubOneMark){
    this.clubOneMark = clubOneMark;
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


public void setState(Integer state){
    this.state = state;
}


public void setClubOneId(Integer clubOneId){
    this.clubOneId = clubOneId;
}


public void setTestMatchId(Integer testMatchId){
    this.testMatchId = testMatchId;
}


public void setWinTeamId(Integer winTeamId){
    this.winTeamId = winTeamId;
}


}