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
@Table(name = "fielding_record")
public class FieldingRecord {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "fielding_record_id")
 private  Integer fieldingRecordId;

@Column(name = "catch")
 private  Integer catches;

@Transient
 private  SelectedPlayer selectedPlayerId;

@Column(name = "fielding_points")
 private  Double fieldingPoints;

@Column(name = "selectedPlayerIdv2")
 private Integer selectedPlayerIdv2;

@Transient
 private SelectedPlayerRequest selectedplayerrequest = new SelectedPlayerRequestImpl();;

public FieldingRecord() {
    super();
// TODO Auto-generated constructor stub
}public FieldingRecord(Integer fieldingRecordId, Integer catches, SelectedPlayer selectedPlayerId, Double fieldingPoints) {
    super();
    this.fieldingRecordId = fieldingRecordId;
    this.catches = catches;
    this.selectedPlayerId = selectedPlayerId;
    this.fieldingPoints = fieldingPoints;
}
public SelectedPlayer getSelectedPlayerId(){
  this.selectedPlayerId = selectedplayerrequest.getSelectedPlayerId(this.selectedPlayerIdv2);
return this.selectedPlayerId;
}


public Integer getFieldingRecordId(){
    return fieldingRecordId;
}


public void setCatches(Integer catches){
    this.catches = catches;
}


public Double getFieldingPoints(){
    return fieldingPoints;
}


public void setSelectedPlayerId(SelectedPlayer selectedPlayerId){
 selectedplayerrequest.setSelectedPlayerId(selectedPlayerId,this.selectedPlayerIdv2);
}



public void setFieldingPoints(Double fieldingPoints){
    this.fieldingPoints = fieldingPoints;
}


public Integer getCatches(){
    return catches;
}


public void setFieldingRecordId(Integer fieldingRecordId){
    this.fieldingRecordId = fieldingRecordId;
}


}