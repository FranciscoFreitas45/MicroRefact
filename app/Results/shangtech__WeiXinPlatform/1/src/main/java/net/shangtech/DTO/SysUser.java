package net.shangtech.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.shangtech.ssh.core.base.IBaseEntity;
public class SysUser extends IBaseEntity{

 private  long serialVersionUID;

 private  String username;

 private  String password;

 private  String mobile;

 private  String email;

 private  String qq;

 private  Date createTime;

 private  String wxName;

 private  String wxNumber;

 private  String token;

 private  String appid;

 private  String appkey;

 private  String province;

 private  String city;

 private  String description;

 private  String openid;

 private  Integer userType;

 private  String siteLogo;

 private  String siteTel;


public String getOpenid(){
    return openid;
}


public Date getCreateTime(){
    return createTime;
}


public String getQq(){
    return qq;
}


public String getDescription(){
    return description;
}


public String getUsername(){
    return username;
}


public String getProvince(){
    return province;
}


public String getSiteLogo(){
    return siteLogo;
}


public String getCity(){
    return city;
}


public Integer getUserType(){
    return userType;
}


public String getWxName(){
    return wxName;
}


public String getSiteTel(){
    return siteTel;
}


public String getAppkey(){
    return appkey;
}


public String getWxNumber(){
    return wxNumber;
}


public String getAppid(){
    return appid;
}


public String getPassword(){
    return password;
}


public String getToken(){
    return token;
}


public String getMobile(){
    return mobile;
}


public String getEmail(){
    return email;
}


}