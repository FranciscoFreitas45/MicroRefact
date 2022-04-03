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
@Table(name = "batman_score")
public class BatmanScore {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "batman_score_id")
 private  Integer batmanScoreId;

@Column(name = "match_count")
 private  Integer matchCount;

@Column(name = "not_out")
 private  Integer notOut;

@Column(name = "face_ball")
 private  Integer facedBalls;

@Column(name = "runs")
 private  Integer runs;

@Column(name = "four")
 private  Integer four;

@Column(name = "six")
 private  Integer six;

@Transient
 private  Player playerId;

@Transient
 private  MatchType matchTypeId;

@Column(name = "points")
 private  Double points;

@Column(name = "half_sentury")
 private  Integer halfCenturies;

@Column(name = "sentury")
 private  Integer centuries;

@Column(name = "double_sentury")
 private  Integer doubleCenturies;

@Column(name = "triple_sentury")
 private  Integer tripleCenturies;

@Column(name = "fourble_sentury")
 private  Integer fourbleCenturies;

@Column(name = "fiveble_sentury")
 private  Integer fivebleCenturies;

@Column(name = "strike_rate")
 private  Double strikeRate;

@Column(name = "playerIdv2")
 private Integer playerIdv2;

@Transient
 private PlayerRequest playerrequest = new PlayerRequestImpl();;

@Column(name = "matchTypeIdv2")
 private Integer matchTypeIdv2;

@Transient
 private MatchTypeRequest matchtyperequest = new MatchTypeRequestImpl();;

public BatmanScore() {
    super();
// TODO Auto-generated constructor stub
}public BatmanScore(Integer batmanScoreId, Integer matchCount, Integer notOut, Integer facedBalls, Integer runs, Integer four, Integer six, Player playerId, MatchType matchTypeId, Double points, Integer halfCenturies, Integer centuries, Integer doubleCenturies, Integer tripleCenturies, Integer fourbleCenturies, Integer fivebleCenturies, Double strikeRate) {
    super();
    this.batmanScoreId = batmanScoreId;
    this.matchCount = matchCount;
    this.notOut = notOut;
    this.facedBalls = facedBalls;
    this.runs = runs;
    this.four = four;
    this.six = six;
    this.playerId = playerId;
    this.matchTypeId = matchTypeId;
    this.points = points;
    this.halfCenturies = halfCenturies;
    this.centuries = centuries;
    this.doubleCenturies = doubleCenturies;
    this.tripleCenturies = tripleCenturies;
    this.fourbleCenturies = fourbleCenturies;
    this.fivebleCenturies = fivebleCenturies;
    this.strikeRate = strikeRate;
}
public void updatePoints(Double points){
    this.points = this.points + points;
}


public void updateFourbleCenturies(Integer fourbleCenturies){
    this.fourbleCenturies = this.fourbleCenturies + fourbleCenturies;
}


public void updateNotOut(Integer notOut){
    this.notOut = this.notOut + notOut;
}


public void setRuns(Integer runs){
    this.runs = runs;
}


public void updateCenturies(Integer centuries){
    this.centuries = this.centuries + centuries;
}


public void updateSix(Integer six){
    this.six = this.six + six;
}


public void setPlayerId(Player playerId){
 playerrequest.setPlayerId(playerId,this.playerIdv2);
}



public void setMatchCount(Integer matchCount){
    this.matchCount = matchCount;
}


public void updateFivebleCenturies(Integer fivebleCenturies){
    this.fivebleCenturies = this.fivebleCenturies + fivebleCenturies;
}


public void setBatmanScoreId(Integer batmanScoreId){
    this.batmanScoreId = batmanScoreId;
}


public Player getPlayerId(){
  this.playerId = playerrequest.getPlayerId(this.playerIdv2);
return this.playerId;
}


public void setFivebleCenturies(Integer fivebleCenturies){
    this.fivebleCenturies = fivebleCenturies;
}


public void setStrikeRate(Double strikeRate){
    this.strikeRate = strikeRate;
}


public void updateFour(Integer four){
    this.four = this.four + four;
}


public Double getPoints(){
    return points;
}


public void setFacedBalls(Integer facedBalls){
    this.facedBalls = facedBalls;
}


public void setMatchTypeId(MatchType matchTypeId){
 matchtyperequest.setMatchTypeId(matchTypeId,this.matchTypeIdv2);
}



public void updateFacedBalls(Integer facedBalls){
    this.facedBalls = this.facedBalls + facedBalls;
}


public void setPoints(Double points){
    this.points = points;
}


public void updateHalfCenturies(Integer halfCenturies){
    this.halfCenturies = this.halfCenturies + halfCenturies;
}


public Player player(){
  this.playerId = playerrequest.player(this.playerIdv2);
return this.playerId;
}


public Integer getBatmanScoreId(){
    return batmanScoreId;
}


public Integer getMatchCount(){
    return matchCount;
}


public void setFour(Integer four){
    this.four = four;
}


public void setSix(Integer six){
    this.six = six;
}


public Integer getFivebleCenturies(){
    return fivebleCenturies;
}


public Integer getFour(){
    return four;
}


public Integer getSix(){
    return six;
}


public void setCenturies(Integer centuries){
    this.centuries = centuries;
}


public Integer getHalfCenturies(){
    return halfCenturies;
}


public void updateTripleCenturies(Integer tripleCenturies){
    this.tripleCenturies = this.tripleCenturies + tripleCenturies;
}


public void setDoubleCenturies(Integer doubleCenturies){
    this.doubleCenturies = doubleCenturies;
}


public Double getStrikeRate(){
    return strikeRate;
}


public void updateRuns(Integer runs){
    this.runs = this.runs + runs;
}


public void setTripleCenturies(Integer tripleCenturies){
    this.tripleCenturies = tripleCenturies;
}


public Integer getFacedBalls(){
    return facedBalls;
}


public void updateMatchCount(Integer matchCount){
    this.matchCount = this.matchCount + matchCount;
}


public void setNotOut(Integer notOut){
    this.notOut = notOut;
}


public Integer getNotOut(){
    return notOut;
}


public Integer getDoubleCenturies(){
    return doubleCenturies;
}


public void setFourbleCenturies(Integer fourbleCenturies){
    this.fourbleCenturies = fourbleCenturies;
}


public void updateDoubleCenturies(Integer doubleCenturies){
    this.doubleCenturies = this.doubleCenturies + doubleCenturies;
}


public Integer getFourbleCenturies(){
    return fourbleCenturies;
}


public MatchType getMatchTypeId(){
  this.matchTypeId = matchtyperequest.getMatchTypeId(this.matchTypeIdv2);
return this.matchTypeId;
}


public void setHalfCenturies(Integer halfCenturies){
    this.halfCenturies = halfCenturies;
}


public Integer getRuns(){
    return runs;
}


public Integer getCenturies(){
    return centuries;
}


public Integer getTripleCenturies(){
    return tripleCenturies;
}


}