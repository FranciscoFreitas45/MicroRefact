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
@Table(name = "batman_record")
public class BatmanRecord {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "batman_record_id")
 private  Integer batmanRecordId;

@Column(name = "runs")
 private  Integer battingRuns;

@Column(name = "face_ball")
 private  Integer facedBalls;

@Column(name = "four")
 private  Integer fours;

@Column(name = "six")
 private  Integer sixes;

@Column(name = "not_out")
 private  Byte notOut;

@Transient
 private  SelectedPlayer selectedPlayerId;

@Column(name = "half_sentury")
 private  Byte halfentury;

@Column(name = "sentury")
 private  Byte sentury;

@Column(name = "double_sentury")
 private  Byte doubleSentury;

@Column(name = "triple_sentury")
 private  Byte tripleSentury;

@Column(name = "fourble_sentury")
 private  Byte foubleSentury;

@Column(name = "fiveble_sentury")
 private  Byte fivebleSentury;

@Column(name = "batting_points")
 private  Double battingPoints;

@Column(name = "strike_rate")
 private  Double strikeRate;

@Column(name = "selectedPlayerIdv2")
 private Integer selectedPlayerIdv2;

@Transient
 private SelectedPlayerRequest selectedplayerrequest = new SelectedPlayerRequestImpl();;

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


public void setSelectedPlayerId(SelectedPlayer selectedPlayerId){
 selectedplayerrequest.setSelectedPlayerId(selectedPlayerId,this.selectedPlayerIdv2);
}



public void setStrikeRate(Double strikeRate){
    this.strikeRate = strikeRate;
}


public SelectedPlayer getSelectedPlayerId(){
  this.selectedPlayerId = selectedplayerrequest.getSelectedPlayerId(this.selectedPlayerIdv2);
return this.selectedPlayerId;
}


public void setDoubleSentury(Byte doubleSentury){
    this.doubleSentury = doubleSentury;
}


public Double getBattingPoints(){
    return battingPoints;
}


public Byte getFivebleSentury(){
    return fivebleSentury;
}


public void setBattingPoints(Double battingPoints){
    this.battingPoints = battingPoints;
}


public void setFacedBalls(Integer facedBalls){
    this.facedBalls = facedBalls;
}


public void setFoubleSentury(Byte foubleSentury){
    this.foubleSentury = foubleSentury;
}


public void setFivebleSentury(Byte fivebleSentury){
    this.fivebleSentury = fivebleSentury;
}


public void setHalfentury(Byte halfentury){
    this.halfentury = halfentury;
}


public Byte getFoubleSentury(){
    return foubleSentury;
}


public Double getStrikeRate(){
    return strikeRate;
}


public void setSentury(Byte sentury){
    this.sentury = sentury;
}


public Integer getFacedBalls(){
    return facedBalls;
}


public Byte getDoubleSentury(){
    return doubleSentury;
}


public void setNotOut(Byte notOut){
    this.notOut = notOut;
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


public void setBattingRuns(Integer battingRuns){
    this.battingRuns = battingRuns;
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


public void setTripleSentury(Byte tripleSentury){
    this.tripleSentury = tripleSentury;
}


public void setSixes(Integer sixes){
    this.sixes = sixes;
}


public void setBatmanRecordId(Integer batmanRecordId){
    this.batmanRecordId = batmanRecordId;
}


}