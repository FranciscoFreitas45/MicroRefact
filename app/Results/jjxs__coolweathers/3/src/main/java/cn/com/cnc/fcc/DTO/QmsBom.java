package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsBom implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer vehicleId;

 private  Integer materielId;

 private  Integer parentMaterielID;

 private  Integer rootMaterielId;

 private  Integer sequence;

 private  Integer quantity;

 private  String isMust;

 private  String supplyType;

 private  String flagStatus;

 private  String compPkid;

 private  String remark;

 private  String reserveFirst;

 private  String reserveSecond;

 private  String reserveThird;

 private  String makeUser;

 private  ZonedDateTime makeTime;

 private  String modifyUser;

 private  ZonedDateTime modifyTime;


public QmsBom parentMaterielID(Integer parentMaterielID){
    this.parentMaterielID = parentMaterielID;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
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


public Integer getVehicleId(){
    return vehicleId;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public Integer getRootMaterielId(){
    return rootMaterielId;
}


public Integer getParentMaterielID(){
    return parentMaterielID;
}


public void setIsMust(String isMust){
    this.isMust = isMust;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsBom makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsBom flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public QmsBom compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setSequence(Integer sequence){
    this.sequence = sequence;
}


public Integer getQuantity(){
    return quantity;
}


public Integer getMaterielId(){
    return materielId;
}


public Long getId(){
    return id;
}


public QmsBom reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public QmsBom reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


public String getReserveThird(){
    return reserveThird;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public void setVehicleId(Integer vehicleId){
    this.vehicleId = vehicleId;
}


public String getReserveFirst(){
    return reserveFirst;
}


public QmsBom vehicleId(Integer vehicleId){
    this.vehicleId = vehicleId;
    return this;
}


public String getSupplyType(){
    return supplyType;
}


public String getReserveSecond(){
    return reserveSecond;
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


public String getIsMust(){
    return isMust;
}


public void setQuantity(Integer quantity){
    this.quantity = quantity;
}


@Override
public String toString(){
    return "QmsBom{" + "id=" + getId() + ", vehicleId=" + getVehicleId() + ", materielId=" + getMaterielId() + ", parentMaterielID=" + getParentMaterielID() + ", rootMaterielId=" + getRootMaterielId() + ", sequence=" + getSequence() + ", quantity=" + getQuantity() + ", isMust='" + getIsMust() + "'" + ", supplyType='" + getSupplyType() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public Integer getSequence(){
    return sequence;
}


public String getFlagStatus(){
    return flagStatus;
}


}