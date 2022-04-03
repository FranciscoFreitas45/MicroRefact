package com.gp.cricket.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.gp.cricket.Request.PlayerRequest;
import com.gp.cricket.Request.Impl.PlayerRequestImpl;
import com.gp.cricket.DTO.Player;
import com.gp.cricket.Request.MatchTypeRequest;
import com.gp.cricket.Request.Impl.MatchTypeRequestImpl;
import com.gp.cricket.DTO.MatchType;
@Entity
@Table(name = "baller_score")
public class BallerScore {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "baller_score_id")
 private  Integer ballerScoredId;

@Column(name = "match_count")
 private  Integer mathcCount;

@Column(name = "overs")
 private  Double overs;

@Column(name = "wicket")
 private  Integer wickets;

@Column(name = "avg_speed")
 private  Double averageSpeed;

@Column(name = "wide_ball")
 private  Integer wideBalls;

@Column(name = "no_ball")
 private  Integer noBalls;

@Column(name = "num_of_runs_aginst")
 private  Integer numberOfRunsAgainst;

@Column(name = "hat_trick")
 private  Integer hatricks;

@Transient
 private  Player playerId;

@Transient
 private  MatchType matchTypeId;

@Column(name = "points")
 private  Double points;

@Column(name = "playerIdv2")
 private Integer playerIdv2;

@Transient
 private PlayerRequest playerrequest = new PlayerRequestImpl();;

@Column(name = "matchTypeIdv2")
 private Integer matchTypeIdv2;

@Transient
 private MatchTypeRequest matchtyperequest = new MatchTypeRequestImpl();;

public BallerScore() {
    super();
// TODO Auto-generated constructor stub
}public BallerScore(Integer ballerScoredId, Integer mathcCount, Double overs, Integer wickets, Double averageSpeed, Integer wideBalls, Integer noBalls, Integer numberOfRunsAgainst, Integer hatricks, Player playerId, MatchType matchTypeId, Double points) {
    super();
    this.ballerScoredId = ballerScoredId;
    this.mathcCount = mathcCount;
    this.overs = overs;
    this.wickets = wickets;
    this.averageSpeed = averageSpeed;
    this.wideBalls = wideBalls;
    this.noBalls = noBalls;
    this.numberOfRunsAgainst = numberOfRunsAgainst;
    this.hatricks = hatricks;
    this.playerId = playerId;
    this.matchTypeId = matchTypeId;
    this.points = points;
}
public void updatePoints(Double points){
    this.points = this.points + points;
}


public void setHatricks(Integer hatricks){
    this.hatricks = hatricks;
}


public void setPlayerId(Player playerId){
 playerrequest.setPlayerId(playerId,this.playerIdv2);
}



public void setMatchCount(Integer mathcCount){
    this.mathcCount = mathcCount;
}


public void updateWickets(Integer wickets){
    this.wickets = this.wickets + wickets;
}


public Player getPlayerId(){
  this.playerId = playerrequest.getPlayerId(this.playerIdv2);
return this.playerId;
}


public Integer getWickets(){
    return wickets;
}


public void setAverageSpeed(Double averageSpeed){
    this.averageSpeed = averageSpeed;
}


public Double getAverageSpeed(){
    return averageSpeed;
}


public void updateNumberOfRunsAgainst(Integer numberOfRunsAgainst){
    this.numberOfRunsAgainst = this.numberOfRunsAgainst + numberOfRunsAgainst;
}


public Integer getHatricks(){
    return hatricks;
}


public Double getOvers(){
    return overs;
}


public void setNumberOfRunsAgainst(Integer numberOfRunsAgainst){
    this.numberOfRunsAgainst = numberOfRunsAgainst;
}


public Double getPoints(){
    return points;
}


public void setMatchTypeId(MatchType matchTypeId){
 matchtyperequest.setMatchTypeId(matchTypeId,this.matchTypeIdv2);
}



public void updateHatricks(Integer hatricks){
    this.hatricks = this.hatricks + hatricks;
}


public void setPoints(Double points){
    this.points = points;
}


public Integer getBallerScoredId(){
    return ballerScoredId;
}


public Integer getMatchCount(){
    return mathcCount;
}


public void setOvers(Double overs){
    this.overs = overs;
}


public void updateWideBalls(Integer wideBalls){
    this.wideBalls = this.wideBalls + wideBalls;
}


public void updateOvers(Double overs){
    this.overs = +this.overs + overs;
}


public Integer getNoBalls(){
    return noBalls;
}


public Integer getNumberOfRunsAgainst(){
    return numberOfRunsAgainst;
}


public void updateMatchCount(Integer mathcCount){
    this.mathcCount = this.mathcCount + mathcCount;
}


public void setNoBalls(Integer noBalls){
    this.noBalls = noBalls;
}


public void updateNoBalls(Integer noBalls){
    this.noBalls = this.noBalls + noBalls;
}


public MatchType getMatchTypeId(){
  this.matchTypeId = matchtyperequest.getMatchTypeId(this.matchTypeIdv2);
return this.matchTypeId;
}


public Integer getWideBalls(){
    return wideBalls;
}


public void setWideBalls(Integer wideBalls){
    this.wideBalls = wideBalls;
}


public void setBallerScoredId(Integer ballerScoredId){
    this.ballerScoredId = ballerScoredId;
}


public void setWickets(Integer wickets){
    this.wickets = wickets;
}


}