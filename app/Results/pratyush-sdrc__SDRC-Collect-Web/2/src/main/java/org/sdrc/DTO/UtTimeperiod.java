package org.sdrc.DTO;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class UtTimeperiod implements Serializable{

 private  long serialVersionUID;

 private  int timePeriod_NId;

 private  Date endDate;

 private  String periodicity;

 private  Date startDate;

 private  String timePeriod;

public UtTimeperiod() {
}
public Date getStartDate(){
    return this.startDate;
}


public void setTimePeriod_NId(int timePeriod_NId){
    this.timePeriod_NId = timePeriod_NId;
}


public String getTimePeriod(){
    return this.timePeriod;
}


public Date getEndDate(){
    return this.endDate;
}


public String getPeriodicity(){
    return this.periodicity;
}


public int getTimePeriod_NId(){
    return this.timePeriod_NId;
}


}