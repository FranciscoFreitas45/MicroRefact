package com.uec.imonitor.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
public class UserEntity {

 private  Integer innerid;

 private  String userName;

 private  String password;

 private  String name;

 private  String email;

 private  String mobilePhone;

 private  String telePhone;

 private  Date createTime;

 private  Integer enabled;

 private  String salt;


public Date getCreateTime(){
    return createTime;
}


public String getName(){
    return name;
}


public String getSalt(){
    return salt;
}


public String getPassword(){
    return password;
}


public Integer getInnerid(){
    return innerid;
}


public String getTelePhone(){
    return telePhone;
}


public Integer getEnabled(){
    return enabled;
}


public String getUserName(){
    return userName;
}


public String getEmail(){
    return email;
}


public String getMobilePhone(){
    return mobilePhone;
}


}