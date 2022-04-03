package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_system_updatecon")
@org.hibernate.annotations.Proxy(lazy = false)
public class SystemUpdatecon {

 private  long serialVersionUID;

 private  String id;

 private  boolean schedule;

 private  String orgi;

 private  Date upgradetime;

 private  int scheduletimes;

 private  Date updatetime;


public void setSchedule(boolean schedule){
    this.schedule = schedule;
}


public Date getUpgradetime(){
    return upgradetime;
}


public void setScheduletimes(int scheduletimes){
    this.scheduletimes = scheduletimes;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getOrgi(){
    return orgi;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setId(String id){
    this.id = id;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public int getScheduletimes(){
    return scheduletimes;
}


public boolean isSchedule(){
    return schedule;
}


public void setUpgradetime(Date upgradetime){
    this.upgradetime = upgradetime;
}


}