package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class SipTrunk {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String orgi;

 private  String creater;

 private  String type;

 private  Date createtime;

 private  Date updatetime;

 private  String hostid;

 private  String sipserver;

 private  int port;

 private  String extention;

 private  String outnumber;

 private  String prefix;

 private  String prefixstr;

 private  String dtmf;

 private  boolean register;

 private  boolean defaultsip;

 private  String title;

 private  String username;

 private  String authuser;

 private  String password;

 private  String fromuser;

 private  boolean transprotocol;

 private  String protocol;

 private  int exptime;

 private  int retry;

 private  int heartbeat;

 private  String sipcontent;

 private  String busyext;

 private  String notready;

 private  String noname;

 private  boolean enablecallagent;

 private  String province;

 private  String city;


public String getName(){
    return name;
}


public String getExtention(){
    return extention;
}


public String getTitle(){
    return title;
}


public String getCity(){
    return city;
}


public int getHeartbeat(){
    return heartbeat;
}


public String getPrefix(){
    return prefix;
}


public String getSipserver(){
    return sipserver;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getAuthuser(){
    return authuser;
}


public String getPrefixstr(){
    return prefixstr;
}


public String getType(){
    return type;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getOutnumber(){
    return outnumber;
}


public String getUsername(){
    return username;
}


public String getFromuser(){
    return fromuser;
}


public Date getCreatetime(){
    return createtime;
}


public String getHostid(){
    return hostid;
}


public String getProvince(){
    return province;
}


public String getNoname(){
    return noname;
}


public String getCreater(){
    return creater;
}


public String getSipcontent(){
    return sipcontent;
}


public int getRetry(){
    return retry;
}


public String getDtmf(){
    return dtmf;
}


public String getProtocol(){
    return protocol;
}


public String getNotready(){
    return notready;
}


public String getPassword(){
    return password;
}


public int getPort(){
    return port;
}


public String getBusyext(){
    return busyext;
}


public String getOrgi(){
    return orgi;
}


public int getExptime(){
    return exptime;
}


}