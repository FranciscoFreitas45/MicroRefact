package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import com.ukefu.util.event.UserEvent;
public class OnlineUser implements UserEvent{

 private  long serialVersionUID;

 private  String creater;

 private  String datastatus;

 private  String id;

 private  String impid;

 private  String appid;

 private  String ipcode;

 private  String orgi;

 private  String channel;

 private  String owner;

 private  String processid;

 private  String shares;

 private  Date updatetime;

 private  String updateuser;

 private  String username;

 private  String wfstatus;

 private  String resolution;

 private  String opersystem;

 private  String browser;

 private  String status;

 private  String userid;

 private  Date logintime;

 private  String sessionid;

 private  Date createtime;

 private  String usertype;

 private  String optype;

 private  String mobile;

 private  String olduser;

 private  String ip;

 private  String hostname;

 private  String country;

 private  String region;

 private  String city;

 private  String isp;

 private  String province;

 private  int betweentime;

 private  String datestr;

 private  String keyword;

 private  String source;

 private  String title;

 private  String url;

 private  String useragent;

 private  String contactsid;

 private  int invitetimes;

 private  String invitestatus;

 private  int refusetimes;

 private  Contacts contacts;


public Date getLogintime(){
    return this.logintime;
}


public String getCountry(){
    return this.country;
}


public String getOwner(){
    return this.owner;
}


public String getStatus(){
    return this.status;
}


public String getTitle(){
    return this.title;
}


public String getChannel(){
    return channel;
}


public String getIpcode(){
    return this.ipcode;
}


public String getOlduser(){
    return this.olduser;
}


public String getInvitestatus(){
    return invitestatus;
}


public String getCity(){
    return this.city;
}


public String getUseragent(){
    return this.useragent;
}


public String getUpdateuser(){
    return this.updateuser;
}


public Date getUpdatetime(){
    return this.updatetime;
}


public String getImpid(){
    return this.impid;
}


public String getUrl(){
    return this.url;
}


public String getContactsid(){
    return contactsid;
}


public String getRegion(){
    return this.region;
}


public String getSource(){
    return this.source;
}


public String getProcessid(){
    return this.processid;
}


public String getBrowser(){
    return this.browser;
}


public String getResolution(){
    return this.resolution;
}


public String getIp(){
    return this.ip;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return this.id;
}


@Transient
public Contacts getContacts(){
    return contacts;
}


public String getUsername(){
    return this.username;
}


public String getShares(){
    return this.shares;
}


public String getHostname(){
    return this.hostname;
}


public int getRefusetimes(){
    return refusetimes;
}


public Date getCreatetime(){
    return this.createtime;
}


public String getIsp(){
    return this.isp;
}


public String getProvince(){
    return this.province;
}


public String getDatestr(){
    return this.datestr;
}


public String getCreater(){
    return this.creater;
}


public String getSessionid(){
    return this.sessionid;
}


public String getUserid(){
    return this.userid;
}


public int getBetweentime(){
    return this.betweentime;
}


public String getDatastatus(){
    return this.datastatus;
}


public String getAppid(){
    return appid;
}


public String getOptype(){
    return this.optype;
}


public String getKeyword(){
    return this.keyword;
}


public String getOpersystem(){
    return this.opersystem;
}


public String getOrgi(){
    return this.orgi;
}


public String getWfstatus(){
    return this.wfstatus;
}


public String getMobile(){
    return this.mobile;
}


public String getUsertype(){
    return this.usertype;
}


public int getInvitetimes(){
    return invitetimes;
}


}