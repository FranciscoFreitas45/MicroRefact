package com.crontab;
 public class JsonGlobalHoliday {

 private  Integer globalHolidayId;

 private  String globalHolidayName;

 private  String globalHolidayDate;

 private  String comments;

 protected  String lastUpdatedById;

 protected  Long lastUpdatedDttm;


public void setGlobalHolidayDate(String globalHolidayDate){
    this.globalHolidayDate = globalHolidayDate;
}


public String getLastUpdatedById(){
    return lastUpdatedById;
}


public String getGlobalHolidayName(){
    return globalHolidayName;
}


public void setLastUpdatedDttm(Long lastUpdatedDttm){
    this.lastUpdatedDttm = lastUpdatedDttm;
}


public String getGlobalHolidayDate(){
    return globalHolidayDate;
}


public Integer getGlobalHolidayId(){
    return globalHolidayId;
}


public String getComments(){
    return comments;
}


public void setGlobalHolidayId(Integer globalHolidayId){
    this.globalHolidayId = globalHolidayId;
}


public void setGlobalHolidayName(String globalHolidayName){
    this.globalHolidayName = globalHolidayName;
}


public Long getLastUpdatedDttm(){
    return lastUpdatedDttm;
}


public void setComments(String comments){
    this.comments = comments;
}


public void setLastUpdatedById(String lastUpdatedById){
    this.lastUpdatedById = lastUpdatedById;
}


}