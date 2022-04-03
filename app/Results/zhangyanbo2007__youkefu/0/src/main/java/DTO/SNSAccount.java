package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
public class SNSAccount {

 private  String id;

 private  String snsid;

 private  String name;

 private  String code;

 private  String username;

 private  String password;

 private  String snstype;

 private  Date createtime;

 private  Date updatetime;

 private  int expirestime;

 private  String account;

 private  String verify;

 private  String headimg;

 private  String qrcode;

 private  String alias;

 private  String openpay;

 private  String openshake;

 private  String oepnscan;

 private  String opencard;

 private  String openstore;

 private  String refreshtoken;

 private  String authaccesstoken;

 private  String allowremote;

 private  String email;

 private  String userno;

 private  String token;

 private  String apipoint;

 private  String appkey;

 private  String secret;

 private  String aeskey;

 private  String baseURL;

 private  String apptoken;

 private  String sessionkey;

 private  boolean defaultaccount;

 private  String moreparam;

 private  String orgi;

 private  String lastatupdate;

 private  String lastprimsgupdate;

 private  String status;

 private  boolean agent;


public String getUserno(){
    return userno;
}


public String getName(){
    return name;
}


public String getSnstype(){
    return snstype;
}


@Transient
public String getStatus(){
    return status;
}


public String getOpenstore(){
    return openstore;
}


public String getHeadimg(){
    return headimg;
}


public String getOpencard(){
    return opencard;
}


public String getCode(){
    return code;
}


public String getRefreshtoken(){
    return refreshtoken;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getSessionkey(){
    return sessionkey;
}


public String getSecret(){
    return secret;
}


public String getEmail(){
    return email;
}


public String getAlias(){
    return alias;
}


public String getAuthaccesstoken(){
    return authaccesstoken;
}


public String getAeskey(){
    return aeskey;
}


public String getMoreparam(){
    return moreparam;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getUsername(){
    return username;
}


public String getBaseURL(){
    return baseURL;
}


public String getAllowremote(){
    return allowremote;
}


public Date getCreatetime(){
    return createtime;
}


public String getAccount(){
    return account;
}


public String getVerify(){
    return verify;
}


public String getQrcode(){
    return qrcode;
}


public String getApipoint(){
    return apipoint;
}


public String getAppkey(){
    return appkey;
}


public String getOpenpay(){
    return openpay;
}


public String getOpenshake(){
    return openshake;
}


public String getLastprimsgupdate(){
    return lastprimsgupdate;
}


public String getPassword(){
    return password;
}


public String getOepnscan(){
    return oepnscan;
}


public String getApptoken(){
    return apptoken;
}


public String getToken(){
    return token;
}


public String getOrgi(){
    return orgi;
}


public String getSnsid(){
    return snsid;
}


public String getLastatupdate(){
    return lastatupdate;
}


public int getExpirestime(){
    return expirestime;
}


}