package com.gs.bean;
 import java.util.Date;
import com.gs.Interface.MaintainRecord;
import com.gs.Interface.Accessories;
public class MaterialReturn {

 private  String materialReturnId;

 private  String matainRecordId;

 private  String accId;

 private  Integer accCount;

 private  Date mrCreatedDate;

 private  Date mrReturnDate;

 private  int count;

 private  int accIdle;

 private  MaintainRecord record;

 private  Accessories accessories;


public void setRecord(MaintainRecord record){
    this.record = record;
}


public String getAccId(){
    return accId;
}


public void setMrCreatedDate(Date mrCreatedDate){
    this.mrCreatedDate = mrCreatedDate;
}


public Date getMrCreatedDate(){
    return mrCreatedDate;
}


public void setAccId(String accId){
    this.accId = accId;
}


public Integer getAccCount(){
    return accCount;
}


public int getAccIdle(){
    return accIdle;
}


public void setAccCount(Integer accCount){
    this.accCount = accCount;
}


public void setMrReturnDate(Date mrReturnDate){
    this.mrReturnDate = mrReturnDate;
}


public Date getMrReturnDate(){
    return mrReturnDate;
}


public void setAccIdle(int accIdle){
    this.accIdle = accIdle;
}


public void setMatainRecordId(String matainRecordId){
    this.matainRecordId = matainRecordId;
}


public Accessories getAccessories(){
    return accessories;
}


public String getMatainRecordId(){
    return matainRecordId;
}


public String getMaterialReturnId(){
    return materialReturnId;
}


public void setMaterialReturnId(String materialReturnId){
    this.materialReturnId = materialReturnId;
}


public MaintainRecord getRecord(){
    return record;
}


public int getCount(){
    return count;
}


public void setAccessories(Accessories accessories){
    this.accessories = accessories;
}


public void setCount(int count){
    this.count = count;
}


}