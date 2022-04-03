package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsVehicleTypeInfo implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer vehicleClassId;

 private  String vehicleType;

 private  String vehicleTypeName;

 private  String remark;

 private  String flagStatus;

 private  String compPkid;

 private  String reserveFirst;

 private  String reserveSecond;

 private  String reserveThird;

 private  String makeUser;

 private  ZonedDateTime makeTime;

 private  String modifyUser;

 private  ZonedDateTime modifyTime;


public void setVehicleTypeName(String vehicleTypeName){
    this.vehicleTypeName = vehicleTypeName;
}


public Long getId(){
    return id;
}


public QmsVehicleTypeInfo reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public QmsVehicleTypeInfo reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


public QmsVehicleTypeInfo modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public QmsVehicleTypeInfo modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public String getReserveThird(){
    return reserveThird;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public void setVehicleType(String vehicleType){
    this.vehicleType = vehicleType;
}


public String getRemark(){
    return remark;
}


public String getReserveFirst(){
    return reserveFirst;
}


public QmsVehicleTypeInfo vehicleType(String vehicleType){
    this.vehicleType = vehicleType;
    return this;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public String getReserveSecond(){
    return reserveSecond;
}


public QmsVehicleTypeInfo reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
    return this;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public String getVehicleTypeName(){
    return vehicleTypeName;
}


public String getModifyUser(){
    return modifyUser;
}


public Integer getVehicleClassId(){
    return vehicleClassId;
}


public QmsVehicleTypeInfo makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsVehicleTypeInfo flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


@Override
public String toString(){
    return "QmsVehicleTypeInfo{" + "id=" + getId() + ", vehicleClassId=" + getVehicleClassId() + ", vehicleType='" + getVehicleType() + "'" + ", vehicleTypeName='" + getVehicleTypeName() + "'" + ", remark='" + getRemark() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


public String getVehicleType(){
    return vehicleType;
}


}