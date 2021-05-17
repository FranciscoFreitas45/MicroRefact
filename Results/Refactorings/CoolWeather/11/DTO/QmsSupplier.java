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


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getRemark(){
    return remark;
}


public String getFaxNum(){
    return faxNum;
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


public String getAddress(){
    return address;
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


public String getFlagStatus(){
    return flagStatus;
}


}