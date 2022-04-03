package com.gs.bean;
 public class CarPlate {

 private  String plateId;

 private  String plateName;

 private  String plateDes;

 private  String plateStatus;


public void setPlateDes(String plateDes){
    this.plateDes = plateDes;
}


public void setPlateStatus(String plateStatus){
    this.plateStatus = plateStatus;
}


public String getPlateId(){
    return plateId;
}


public String getPlateName(){
    return plateName;
}


public String getPlateDes(){
    return plateDes;
}


public void setPlateName(String plateName){
    this.plateName = plateName;
}


public String getPlateStatus(){
    return plateStatus;
}


public void setPlateId(String plateId){
    this.plateId = plateId;
}


}