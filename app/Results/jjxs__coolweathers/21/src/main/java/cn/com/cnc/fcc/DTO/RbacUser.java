package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class RbacUser implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer appId;

 private  Integer storeId;

 private  String userCode;

 private  String userPassword;

 private  String userName;

 private  String userMobile;

 private  String userMail;

 private  ZonedDateTime userLastLoginTime;

 private  Integer userLoginCount;

 private  Integer stopFlag;

 private  Integer delFlag;

 private  String insProgarmCd;

 private  String insOperCd;

 private  ZonedDateTime insDateTime;

 private  String updProgarmCd;

 private  String updOperCd;

 private  ZonedDateTime updDateTime;

 private  String delProgarmCd;

 private  String delOperCd;

 private  ZonedDateTime delDateTime;

 private  ZonedDateTime triggerDateTime;

 private  String organizationCd;


public Integer getStopFlag(){
    return stopFlag;
}


public String getUserName(){
    return userName;
}


public Integer getUserLoginCount(){
    return userLoginCount;
}


public ZonedDateTime getDelDateTime(){
    return delDateTime;
}


public String getInsProgarmCd(){
    return insProgarmCd;
}


public String getDelProgarmCd(){
    return delProgarmCd;
}


public String getOrganizationCd(){
    return organizationCd;
}


public String getUpdProgarmCd(){
    return updProgarmCd;
}


public String getUserCode(){
    return userCode;
}


public ZonedDateTime getUpdDateTime(){
    return updDateTime;
}


public Integer getDelFlag(){
    return delFlag;
}


public ZonedDateTime getTriggerDateTime(){
    return triggerDateTime;
}


public Long getId(){
    return id;
}


public String getUserPassword(){
    return userPassword;
}


public String getDelOperCd(){
    return delOperCd;
}


public Integer getAppId(){
    return appId;
}


public ZonedDateTime getInsDateTime(){
    return insDateTime;
}


public String getUserMobile(){
    return userMobile;
}


public Integer getStoreId(){
    return storeId;
}


public String getUserMail(){
    return userMail;
}


public String getUpdOperCd(){
    return updOperCd;
}


public String getInsOperCd(){
    return insOperCd;
}


public ZonedDateTime getUserLastLoginTime(){
    return userLastLoginTime;
}


}