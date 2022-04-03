package com.gp.cricket.DTO;
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
public class BallerRecord {

 private  Integer ballerRecordId;

 private  Double overs;

 private  Integer numberOfRunsAgainst;

 private  Integer wickets;

 private  Integer hatTriks;

 private  SelectedPlayer selectedPlayerId;

 private  Double ballingPoints;

 private  Integer numberOfWides;

 private  Integer numberOfNos;

 private  Double avgSpeed;

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
public Double getBallingPoints(){
    return ballingPoints;
}


public Integer getNumberOfRunsAgainst(){
    return numberOfRunsAgainst;
}


public void setSelectedPlayerId(SelectedPlayer selectedPlayerId){
    this.selectedPlayerId = selectedPlayerId;
}


public Integer getWickets(){
    return wickets;
}


public SelectedPlayer getSelectedPlayerId(){
    return selectedPlayerId;
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


public void setAvgSpeed(Double avgSpeed){
    this.avgSpeed = avgSpeed;
}


public Integer getNumberOfWides(){
    return numberOfWides;
}


public void setNumberOfNos(Integer numberOfNos){
    this.numberOfNos = numberOfNos;
}


public Double getAvgSpeed(){
    return avgSpeed;
}


}