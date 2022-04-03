package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsSupplier implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer supplierClassId;

 private  String supplierCd;

 private  String supplierName;

 private  String address;

 private  String telNum1;

 private  String telNum2;

 private  String faxNum;

 private  String urlAddress;

 private  String mailAddress;

 private  String linkMan;

 private  String department;

 private  String assRecord;

 private  String flagStatus;

 private  String compPkid;

 private  String remark;

 private  String makeUser;

 private  ZonedDateTime makeTime;

 private  String modifyUser;

 private  ZonedDateTime modifyTime;


public String getDepartment(){
    return department;
}


public String getMakeUser(){
    return makeUser;
}


public String getLinkMan(){
    return linkMan;
}


public QmsSupplier modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getRemark(){
    return remark;
}


public void setAssRecord(String assRecord){
    this.assRecord = assRecord;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public void setDepartment(String department){
    this.department = department;
}


public void setSupplierName(String supplierName){
    this.supplierName = supplierName;
}


public String getFaxNum(){
    return faxNum;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsSupplier makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsSupplier faxNum(String faxNum){
    this.faxNum = faxNum;
    return this;
}


public void setUrlAddress(String urlAddress){
    this.urlAddress = urlAddress;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public Integer getSupplierClassId(){
    return supplierClassId;
}


public String getSupplierCd(){
    return supplierCd;
}


public Long getId(){
    return id;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public String getAddress(){
    return address;
}


public QmsSupplier department(String department){
    this.department = department;
    return this;
}


public String getTelNum1(){
    return telNum1;
}


public String getTelNum2(){
    return telNum2;
}


public String getUrlAddress(){
    return urlAddress;
}


public String getSupplierName(){
    return supplierName;
}


public QmsSupplier telNum2(String telNum2){
    this.telNum2 = telNum2;
    return this;
}


public QmsSupplier telNum1(String telNum1){
    this.telNum1 = telNum1;
    return this;
}


public void setSupplierCd(String supplierCd){
    this.supplierCd = supplierCd;
}


public String getAssRecord(){
    return assRecord;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public String getMailAddress(){
    return mailAddress;
}


public String getModifyUser(){
    return modifyUser;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    QmsSupplier qmsSupplier = (QmsSupplier) o;
    if (qmsSupplier.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsSupplier.getId());
}


public String getFlagStatus(){
    return flagStatus;
}


}