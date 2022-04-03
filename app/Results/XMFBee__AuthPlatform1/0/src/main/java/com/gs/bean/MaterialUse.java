package com.gs.bean;
 import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import com.gs.Interface.MaintainRecord;
import com.gs.Interface.Accessories;
public class MaterialUse {

 private  String materialUseId;

 private  String matainRecordId;

 private  String accId;

 private  Integer accCount;

 private  Date muCreatedTime;

 private  Date muUseDate;

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


public Date getMuCreatedTime(){
    return muCreatedTime;
}


public void setMuUseDate(Date muUseDate){
    this.muUseDate = muUseDate;
}


public void setAccId(String accId){
    this.accId = accId;
}


public Integer getAccCount(){
    return accCount;
}


public void setMuCreatedTime(Date muCreatedTime){
    this.muCreatedTime = muCreatedTime;
}


public Date getMuUseDate(){
    return muUseDate;
}


public int getAccIdle(){
    return accIdle;
}


public String getMaterialUseId(){
    return materialUseId;
}


public void setAccCount(Integer accCount){
    this.accCount = accCount;
}


public void setAccIdle(int accIdle){
    this.accIdle = accIdle;
}


public void setMaterialUseId(String materialUseId){
    this.materialUseId = materialUseId;
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