package com.gs.DTO;
 import com.gs.bean.MaintainRecord;
import com.gs.bean.WorkInfo;
import com.gs.Interface.WorkInfo;
public class RecordBaseView {

 private  String recordId;

 private  int hours;

 private  String carplate;

 private  String carmodel;

 private  MaintainRecord record;

 private  WorkInfo workInfo;


public void setRecordId(String recordId){
    this.recordId = recordId;
}


public String getCarplate(){
    return carplate;
}


public void setCarplate(String carplate){
    this.carplate = carplate;
}


public String getCarmodel(){
    return carmodel;
}


public int getHours(){
    return hours;
}


public String getRecordId(){
    return recordId;
}


public MaintainRecord getRecord(){
    return record;
}


public WorkInfo getWorkInfo(){
    return workInfo;
}


}