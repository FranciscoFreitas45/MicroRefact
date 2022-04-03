package com.sprint2.model;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "scheduler_info1")
public class Scheduler {

@Id
@GeneratedValue
 private  Integer schedulerId;

 private  String schedulerName;

 private  String schedulerContact;

 private  String truckNumber;

public Scheduler() {
    super();
}public Scheduler(Integer schedulerId, String schedulerName, String schedulerContact, String truckNumber) {
    super();
    this.schedulerId = schedulerId;
    this.schedulerName = schedulerName;
    this.schedulerContact = schedulerContact;
    this.truckNumber = truckNumber;
}
public void setTruckNumber(String truckNumber){
    this.truckNumber = truckNumber;
}


public Integer getSchedulerId(){
    return schedulerId;
}


public void setSchedulerName(String schedulerName){
    this.schedulerName = schedulerName;
}


@Override
public String toString(){
    return "Scheduler [schedulerId=" + schedulerId + ", schedulerName=" + schedulerName + ", schedulerContact=" + schedulerContact + ", truckNumber=" + truckNumber + "]";
}


public String getSchedulerName(){
    return schedulerName;
}


public String getSchedulerContact(){
    return schedulerContact;
}


public void setSchedulerContact(String schedulerContact){
    this.schedulerContact = schedulerContact;
}


public String getTruckNumber(){
    return truckNumber;
}


public void setSchedulerId(Integer schedulerId){
    this.schedulerId = schedulerId;
}


}