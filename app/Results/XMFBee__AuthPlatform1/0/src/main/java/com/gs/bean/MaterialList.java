package com.gs.bean;
 import java.util.Date;
import java.util.Map;
import com.gs.Interface.MaintainRecord;
import com.gs.Interface.Accessories;
public class MaterialList {

 private  String materialId;

 private  String maintainRecordId;

 private  String accId;

 private  Integer materialCount;

 private  Date materialCreatedTime;

 private  String materialStatus;

 private  Map other;

 private  MaintainRecord record;

 private  Accessories accessories;

 private  MaterialUse materialUse;

 private  MaterialReturn materialReturn;


public void setRecord(MaintainRecord record){
    this.record = record;
}


public void setMaintainRecordId(String maintainRecordId){
    this.maintainRecordId = maintainRecordId;
}


public String getAccId(){
    return accId;
}


public Integer getMaterialCount(){
    return materialCount;
}


public void setMaterialCreatedTime(Date materialCreatedTime){
    this.materialCreatedTime = materialCreatedTime;
}


public Map getOther(){
    return other;
}


public void setAccId(String accId){
    this.accId = accId;
}


public String getMaintainRecordId(){
    return maintainRecordId;
}


public void setMaterialCount(Integer materialCount){
    this.materialCount = materialCount;
}


public void setOther(Map other){
    this.other = other;
}


public void setMaterialReturn(MaterialReturn materialReturn){
    this.materialReturn = materialReturn;
}


public MaterialUse getMaterialUse(){
    return materialUse;
}


public String getMaterialId(){
    return materialId;
}


public void setMaterialId(String materialId){
    this.materialId = materialId;
}


public Accessories getAccessories(){
    return accessories;
}


public Date getMaterialCreatedTime(){
    return materialCreatedTime;
}


@Override
public String toString(){
    return "MaterialList{" + "materialId='" + materialId + '\'' + ", maintainRecordId='" + maintainRecordId + '\'' + ", accId='" + accId + '\'' + ", materialCount=" + materialCount + ", materialCreatedTime=" + materialCreatedTime + ", materialStatus='" + materialStatus + '\'' + '}';
}


public MaintainRecord getRecord(){
    return record;
}


public void setMaterialUse(MaterialUse materialUse){
    this.materialUse = materialUse;
}


public void setMaterialStatus(String materialStatus){
    this.materialStatus = materialStatus;
}


public MaterialReturn getMaterialReturn(){
    return materialReturn;
}


public void setAccessories(Accessories accessories){
    this.accessories = accessories;
}


public String getMaterialStatus(){
    return materialStatus;
}


}