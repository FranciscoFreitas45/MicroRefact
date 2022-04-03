package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsProcess implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String processCd;

 private  String processName;

 private  String flagStatus;

 private  String compPkid;

 private  String remark;

 private  String makeUser;

 private  ZonedDateTime makeTime;

 private  String modifyUser;

 private  ZonedDateTime modifyTime;


public String getProcessName(){
    return processName;
}


public Long getId(){
    return id;
}


public String getMakeUser(){
    return makeUser;
}


public QmsProcess processCd(String processCd){
    this.processCd = processCd;
    return this;
}


public void setProcessName(String processName){
    this.processName = processName;
}


public QmsProcess processName(String processName){
    this.processName = processName;
    return this;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getProcessCd(){
    return processCd;
}


public String getRemark(){
    return remark;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public void setProcessCd(String processCd){
    this.processCd = processCd;
}


public String getModifyUser(){
    return modifyUser;
}


public QmsProcess makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsProcess flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


@Override
public String toString(){
    return "QmsProcess{" + "id=" + getId() + ", processCd='" + getProcessCd() + "'" + ", processName='" + getProcessName() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


}