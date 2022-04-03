package org.sdrc.childinfo.model;
 import java.sql.Timestamp;
public class MonitoringFormTran {

 private  int id;

 private  String formPath;

 private  String lastUpdatedBy;

 private  Timestamp lastUpdatedDate;

 private  MonitoringForm monitoringForm;

 private  String status;

 private  String remarks;

 private  boolean isActive;

 private  String timePeriod;


public MonitoringForm getMonitoringForm(){
    return monitoringForm;
}


public void setSamikshyaMonitoringForm(MonitoringForm monitoringForm){
    this.monitoringForm = monitoringForm;
}


public String getRemarks(){
    return remarks;
}


public void setFormPath(String formPath){
    this.formPath = formPath;
}


public void setLastUpdatedDate(Timestamp lastUpdatedDate){
    this.lastUpdatedDate = lastUpdatedDate;
}


public void setLastUpdatedBy(String lastUpdatedBy){
    this.lastUpdatedBy = lastUpdatedBy;
}


public int getId(){
    return id;
}


public void setTimePeriod(String timePeriod){
    this.timePeriod = timePeriod;
}


public String getFormPath(){
    return formPath;
}


public String getStatus(){
    return status;
}


public boolean isActive(){
    return isActive;
}


public void setStatus(String status){
    this.status = status;
}


public String getTimePeriod(){
    return timePeriod;
}


public void setActive(boolean isActive){
    this.isActive = isActive;
}


public void setId(int id){
    this.id = id;
}


public String getLastUpdatedBy(){
    return lastUpdatedBy;
}


public void setRemarks(String remarks){
    this.remarks = remarks;
}


public Timestamp getLastUpdatedDate(){
    return lastUpdatedDate;
}


}