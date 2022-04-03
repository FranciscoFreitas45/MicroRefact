package com.gp.cricket.mapobject;
 import com.gp.cricket.entity.BallerRecord;
import com.gp.cricket.entity.BatmanRecord;
import com.gp.cricket.entity.FieldingRecord;
public class PlayerRecord {

 private  BatmanRecord batmanRecord;

 private  BallerRecord ballerRecord;

 private  FieldingRecord fieldingRecord;

public PlayerRecord() {
    super();
// TODO Auto-generated constructor stub
}public PlayerRecord(BatmanRecord batmanRecord, BallerRecord ballerRecord, FieldingRecord fieldingRecord) {
    super();
    this.batmanRecord = batmanRecord;
    this.ballerRecord = ballerRecord;
    this.fieldingRecord = fieldingRecord;
}
public void setBatmanRecord(BatmanRecord batmanRecord){
    this.batmanRecord = batmanRecord;
}


public void setFieldingRecord(FieldingRecord fieldingRecord){
    this.fieldingRecord = fieldingRecord;
}


public void setBallerRecord(BallerRecord ballerRecord){
    this.ballerRecord = ballerRecord;
}


public FieldingRecord getFieldingRecord(){
    return fieldingRecord;
}


public BatmanRecord getBatmanRecord(){
    return batmanRecord;
}


public BallerRecord getBallerRecord(){
    return ballerRecord;
}


}