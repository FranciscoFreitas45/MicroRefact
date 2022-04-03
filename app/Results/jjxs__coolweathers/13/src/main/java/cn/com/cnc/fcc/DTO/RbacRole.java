package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class RbacRole implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer appId;

 private  Integer storeId;

 private  String roleCode;

 private  String roleName;

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


public Integer getStopFlag(){
    return stopFlag;
}


public String getRoleCode(){
    return roleCode;
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


public String getUpdProgarmCd(){
    return updProgarmCd;
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


public String getDelOperCd(){
    return delOperCd;
}


public Integer getAppId(){
    return appId;
}


public ZonedDateTime getInsDateTime(){
    return insDateTime;
}


public Integer getStoreId(){
    return storeId;
}


public String getRoleName(){
    return roleName;
}


public String getUpdOperCd(){
    return updOperCd;
}


public String getInsOperCd(){
    return insOperCd;
}


}