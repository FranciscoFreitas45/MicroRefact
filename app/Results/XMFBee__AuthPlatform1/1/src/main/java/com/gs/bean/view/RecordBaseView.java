package com.gs.bean.view;
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


public void setWorkInfo(WorkInfo workInfo){
    this.workInfo = workInfo;
}


public void setRecordId(String recordId){
    this.recordId = recordId;
}


public void setRecord(MaintainRecord record){
    this.record = record;
}


public String getCarplate(){
    return carplate;
}


public void setCarmodel(String carmodel){
    this.carmodel = carmodel;
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


public void setHours(int hours){
    this.hours = hours;
}


public MaintainRecord getRecord(){
    return record;
}


@Override
public String toString(){
    return "RecordBaseView{" + "record=" + record + ", hours=" + hours + ", carplate='" + carplate + '\'' + ", carmodel='" + carmodel + '\'' + '}';
}


public WorkInfo getWorkInfo(){
    return workInfo;
}


}