package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsUnit implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String unitCd;

 private  String unitName;

 private  String flagStatus;

 private  String compPkid;

 private  String remark;

 private  String makeUser;

 private  ZonedDateTime makeTime;

 private  String modifyUser;

 private  ZonedDateTime modifyTime;


public String getUnitName(){
    return unitName;
}


public String getUnitCd(){
    return unitCd;
}


public Long getId(){
    return id;
}


public String getMakeUser(){
    return makeUser;
}


public QmsUnit modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setRemark(String remark){
    this.remark = remark;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getRemark(){
    return remark;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public QmsUnit unitName(String unitName){
    this.unitName = unitName;
    return this;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public String getModifyUser(){
    return modifyUser;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsUnit makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsUnit flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


@Override
public String toString(){
    return "QmsUnit{" + "id=" + getId() + ", unitCd='" + getUnitCd() + "'" + ", unitName='" + getUnitName() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


}