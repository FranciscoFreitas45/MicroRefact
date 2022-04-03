package com.gbcom.common.template.xml.guide;
 import java.io.Serializable;
import java.util.Date;
public class DeviceInfo implements Serializable{

 private  long serialVersionUID;

 protected  int id;

 protected  Date time;


public Date getTime(){
    return time;
}


public void setId(int iid){
    this.id = iid;
}


public int getId(){
    return id;
}


public void setTime(Date time){
    this.time = time;
}


}