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
public class BatmanRecord {

 private  Integer batmanRecordId;

 private  Integer battingRuns;

 private  Integer facedBalls;

 private  Integer fours;

 private  Integer sixes;

 private  Byte notOut;

 private  SelectedPlayer selectedPlayerId;

 private  Byte halfentury;

 private  Byte sentury;

 private  Byte doubleSentury;

 private  Byte tripleSentury;

 private  Byte foubleSentury;

 private  Byte fivebleSentury;

 private  Double battingPoints;

 private  Double strikeRate;

public BatmanRecord() {
    super();
// TODO Auto-generated constructor stub
}public BatmanRecord(Integer batmanRecordId, Integer battingRuns, Integer facedBalls, Integer fours, Integer sixes, Byte notOut, SelectedPlayer selectedPlayerId, Byte halfentury, Byte sentury, Byte doubleSentury, Byte tripleSentury, Byte foubleSentury, Byte fivebleSentury, Double battingPoints, Double strikeRate) {
    super();
    this.batmanRecordId = batmanRecordId;
    this.battingRuns = battingRuns;
    this.facedBalls = facedBalls;
    this.fours = fours;
    this.sixes = sixes;
    this.notOut = notOut;
    this.selectedPlayerId = selectedPlayerId;
    this.halfentury = halfentury;
    this.sentury = sentury;
    this.doubleSentury = doubleSentury;
    this.tripleSentury = tripleSentury;
    this.foubleSentury = foubleSentury;
    this.fivebleSentury = fivebleSentury;
    this.battingPoints = battingPoints;
    this.strikeRate = strikeRate;
}
public Integer getBattingRuns(){
    return battingRuns;
}


public Byte getHalfentury(){
    return halfentury;
}


public void setStrikeRate(Double strikeRate){
    this.strikeRate = strikeRate;
}


public SelectedPlayer getSelectedPlayerId(){
    return selectedPlayerId;
}


public Double getBattingPoints(){
    return battingPoints;
}


public Byte getFivebleSentury(){
    return fivebleSentury;
}


public void setFacedBalls(Integer facedBalls){
    this.facedBalls = facedBalls;
}


public void setFivebleSentury(Byte fivebleSentury){
    this.fivebleSentury = fivebleSentury;
}


public Byte getFoubleSentury(){
    return foubleSentury;
}


public Double getStrikeRate(){
    return strikeRate;
}


public Integer getFacedBalls(){
    return facedBalls;
}


public Byte getDoubleSentury(){
    return doubleSentury;
}


public Byte getNotOut(){
    return notOut;
}


public Byte getTripleSentury(){
    return tripleSentury;
}


public Integer getBatmanRecordId(){
    return batmanRecordId;
}


public void setFours(Integer fours){
    this.fours = fours;
}


public Integer getFours(){
    return fours;
}


public Integer getSixes(){
    return sixes;
}


public Byte getSentury(){
    return sentury;
}


public void setSixes(Integer sixes){
    this.sixes = sixes;
}


}