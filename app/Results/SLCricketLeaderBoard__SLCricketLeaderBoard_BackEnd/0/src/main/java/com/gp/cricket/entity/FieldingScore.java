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
@Table(name = "fielding_score")
public class FieldingScore {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "fielding_id")
 private  Integer fieldingId;

@Column(name = "num_catches")
 private  Integer numberOfCatch;

@Column(name = "points")
 private  Double points;

@Transient
 private  Player playerId;

@Transient
 private  MatchType matchTypeId;

@Column(name = "playerIdv2")
 private Integer playerIdv2;

@Transient
 private PlayerRequest playerrequest = new PlayerRequestImpl();;

@Column(name = "matchTypeIdv2")
 private Integer matchTypeIdv2;

@Transient
 private MatchTypeRequest matchtyperequest = new MatchTypeRequestImpl();;

public FieldingScore() {
    super();
// TODO Auto-generated constructor stub
}public FieldingScore(Integer fieldingId, Integer numberOfCatch, Double fieldingPoints, Player playerId, MatchType matchTypeId) {
    super();
    this.fieldingId = fieldingId;
    this.numberOfCatch = numberOfCatch;
    this.points = fieldingPoints;
    this.playerId = playerId;
    this.matchTypeId = matchTypeId;
}
public void updateNumberOfCatch(Integer numberOfCatch){
    this.numberOfCatch = this.numberOfCatch + numberOfCatch;
}


public void setPlayerId(Player playerId){
 playerrequest.setPlayerId(playerId,this.playerIdv2);
}



public Player getPlayerId(){
  this.playerId = playerrequest.getPlayerId(this.playerIdv2);
return this.playerId;
}


public void setFieldingId(Integer fieldingId){
    this.fieldingId = fieldingId;
}


public Integer getFieldingId(){
    return fieldingId;
}


public void setNumberOfCatch(Integer numberOfCatch){
    this.numberOfCatch = numberOfCatch;
}


public MatchType getMatchTypeId(){
  this.matchTypeId = matchtyperequest.getMatchTypeId(this.matchTypeIdv2);
return this.matchTypeId;
}


public Double getFieldingPoints(){
    return points;
}


public Double getPoints(){
    return points;
}


public void setMatchTypeId(MatchType matchTypeId){
 matchtyperequest.setMatchTypeId(matchTypeId,this.matchTypeIdv2);
}



public void updateFieldingPoints(Double fieldingPoints2){
    this.points = this.points + fieldingPoints2;
// TODO Auto-generated method stub
}


public void setFieldingPoints(Double fieldingPoints){
    this.points = fieldingPoints;
}


public Integer getNumberOfCatch(){
    return numberOfCatch;
}


public void setPoints(Double points){
    this.points = points;
}


}