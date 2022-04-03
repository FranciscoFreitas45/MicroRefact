package com.gp.cricket.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.gp.cricket.Request.SelectedPlayerRequest;
import com.gp.cricket.Request.Impl.SelectedPlayerRequestImpl;
import com.gp.cricket.DTO.SelectedPlayer;
@Entity
@Table(name = "baller_record")
public class BallerRecord {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "baller_record_id")
 private  Integer ballerRecordId;

@Column(name = "overs")
 private  Double overs;

@Column(name = "num_runs_against")
 private  Integer numberOfRunsAgainst;

@Column(name = "wicket")
 private  Integer wickets;

@Column(name = "hat_trick")
 private  Integer hatTriks;

@Transient
 private  SelectedPlayer selectedPlayerId;

@Column(name = "balling_points")
 private  Double ballingPoints;

@Column(name = "num_of_wides")
 private  Integer numberOfWides;

@Column(name = "num_of_nos")
 private  Integer numberOfNos;

@Column(name = "avg_speed")
 private  Double avgSpeed;

@Column(name = "selectedPlayerIdv2")
 private Integer selectedPlayerIdv2;

@Transient
 private SelectedPlayerRequest selectedplayerrequest = new SelectedPlayerRequestImpl();;

public BallerRecord() {
    super();
// TODO Auto-generated constructor stub
}public BallerRecord(Integer ballerRecordId, Double overs, Integer numberOfRunsAgainst, Integer wickets, Integer hatTriks, SelectedPlayer selectedPlayerId, Double ballingPoints, Integer numberOfWides, Integer numberOfNos, Double avgSpeed) {
    super();
    this.ballerRecordId = ballerRecordId;
    this.overs = overs;
    this.numberOfRunsAgainst = numberOfRunsAgainst;
    this.wickets = wickets;
    this.hatTriks = hatTriks;
    this.selectedPlayerId = selectedPlayerId;
    this.ballingPoints = ballingPoints;
    this.numberOfWides = numberOfWides;
    this.numberOfNos = numberOfNos;
    this.avgSpeed = avgSpeed;
}
public void setOvers(Double overs){
    this.overs = overs;
}


public Double getBallingPoints(){
    return ballingPoints;
}


public void setBallerRecordId(Integer ballerRecordId){
    this.ballerRecordId = ballerRecordId;
}


public Integer getNumberOfRunsAgainst(){
    return numberOfRunsAgainst;
}


public void setHatTriks(Integer hatTriks){
    this.hatTriks = hatTriks;
}


public void setSelectedPlayerId(SelectedPlayer selectedPlayerId){
 selectedplayerrequest.setSelectedPlayerId(selectedPlayerId,this.selectedPlayerIdv2);
}



public void setBallingPoints(Double ballingPoints){
    this.ballingPoints = ballingPoints;
}


public Integer getWickets(){
    return wickets;
}


public void setNumberOfWides(Integer numberOfWides){
    this.numberOfWides = numberOfWides;
}


public SelectedPlayer getSelectedPlayerId(){
  this.selectedPlayerId = selectedplayerrequest.getSelectedPlayerId(this.selectedPlayerIdv2);
return this.selectedPlayerId;
}


public Integer getNumberOfNos(){
    return numberOfNos;
}


public Integer getBallerRecordId(){
    return ballerRecordId;
}


public Integer getHatTriks(){
    return hatTriks;
}


public Double getOvers(){
    return overs;
}


public void setNumberOfRunsAgainst(Integer numberOfRunsAgainst){
    this.numberOfRunsAgainst = numberOfRunsAgainst;
}


public void setAvgSpeed(Double avgSpeed){
    this.avgSpeed = avgSpeed;
}


public Integer getNumberOfWides(){
    return numberOfWides;
}


public void setWickets(Integer wickets){
    this.wickets = wickets;
}


public void setNumberOfNos(Integer numberOfNos){
    this.numberOfNos = numberOfNos;
}


public Double getAvgSpeed(){
    return avgSpeed;
}


}