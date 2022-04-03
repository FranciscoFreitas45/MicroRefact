package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsMaterielSupplier implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer materielId;

 private  Integer supplierId;

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


public Integer getMaterielId(){
    return materielId;
}


public Long getId(){
    return id;
}


public QmsMaterielSupplier reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public QmsMaterielSupplier materielId(Integer materielId){
    this.materielId = materielId;
    return this;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public String getReserveThird(){
    return reserveThird;
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


public String getReserveFirst(){
    return reserveFirst;
}


public void setSupplierId(Integer supplierId){
    this.supplierId = supplierId;
}


public String getReserveSecond(){
    return reserveSecond;
}


public QmsMaterielSupplier reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
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


public QmsMaterielSupplier makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsMaterielSupplier flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public Integer getSupplierId(){
    return supplierId;
}


public String getFlagStatus(){
    return flagStatus;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


}