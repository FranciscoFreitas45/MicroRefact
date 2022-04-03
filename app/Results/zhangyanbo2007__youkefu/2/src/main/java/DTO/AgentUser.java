package DTO;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import com.ukefu.util.UKTools;
public class AgentUser implements Serializable,Comparable<AgentUser>{

 private  long serialVersionUID;

 private  String id;

 private  String username;

 private  String agentno;

 private  String userid;

 private  String channel;

 private  Date logindate;

 private  String source;

 private  Date endtime;

 private  String title;

 private  String url;

 private  String traceid;

 private  String owner;

 private  String ipaddr;

 private  String osname;

 private  String browser;

 private  String nickname;

 protected  String city;

 private  String sessionid;

 protected  String province;

 protected  String country;

 protected  String headimgurl;

 private  String region;

 private  long sessiontimes;

 private  int waittingtime;

 private  int tokenum;

 private  Date createtime;

 private  Date updatetime;

 private  String status;

 private  String appid;

 private  String sessiontype;

 private  String contextid;

 private  String agentserviceid;

 private  String orgi;

 private  long ordertime;

 private  String snsuser;

 private  Date lastmessage;

 private  Date servicetime;

 private  Date waittingtimestart;

 private  Date lastgetmessage;

 private  String lastmsg;

 private  String skill;

 private  String agent;

 private  String skillname;

 private  String name;

 private  String email;

 private  String phone;

 private  String resion;

 private  boolean tip;

 private  boolean agentTip;

 private  boolean fromhis;

 private  boolean online;

 private  boolean disconnect;

 private  String agentskill;

 private  String agentservice;

public AgentUser() {
}public AgentUser(String userid, String channel, String snsuser, String username, String orgi, String appid) {
    this.userid = userid;
    this.channel = channel;
    this.snsuser = snsuser;
    this.appid = appid;
    this.username = username;
    this.orgi = orgi;
}
public String getLastmsg(){
    return this.lastmsg;
}


public String getStatus(){
    return this.status;
}


public String getOwner(){
    return owner;
}


@Transient
public String getSessiontype(){
    return this.sessiontype;
}


public String getChannel(){
    return this.channel;
}


public String getIpaddr(){
    return this.ipaddr;
}


public String getHeadimgurl(){
    return this.headimgurl;
}


public Date getLogindate(){
    return this.logindate;
}


public String getResion(){
    return resion;
}


public String getBrowser(){
    return this.browser;
}


public Date getWaittingtimestart(){
    return this.waittingtimestart;
}


public Date getServicetime(){
    return this.servicetime;
}


public String getPhone(){
    return phone;
}


public Date getLastgetmessage(){
    return this.lastgetmessage;
}


@Transient
public long getOrdertime(){
    return this.ordertime;
}


public String getAgentserviceid(){
    return this.agentserviceid;
}


@Transient
public String getTopic(){
    return "/" + this.orgi + "/" + this.agentno;
}


public String getUsername(){
    return this.username;
}


public Date getCreatetime(){
    return this.createtime;
}


public String getProvince(){
    return this.province;
}


public String getUserid(){
    return this.userid;
}


public String getAgent(){
    return agent;
}


public String getOsname(){
    return this.osname;
}


public String getAppid(){
    return this.appid;
}


public String getAgentservice(){
    return this.agentservice;
}


public String getSnsuser(){
    return this.snsuser;
}


public String getContextid(){
    return this.contextid;
}


public String getName(){
    return name;
}


public String getCountry(){
    return this.country;
}


public int getTokenum(){
    return this.tokenum;
}


@Transient
public String getSkillname(){
    return skillname;
}


public String getTitle(){
    return title;
}


public String getCity(){
    return this.city;
}


public Date getUpdatetime(){
    return this.updatetime;
}


public Date getLastmessage(){
    return this.lastmessage;
}


public String getUrl(){
    return url;
}


public String getRegion(){
    return this.region;
}


public String getSource(){
    return this.source;
}


public String getAgentskill(){
    return this.agentskill;
}


public String getEmail(){
    return email;
}


public String getSkill(){
    return skill;
}


public String getTraceid(){
    return traceid;
}


public long getSessiontimes(){
    return this.sessiontimes;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return this.id;
}


public String getSessionid(){
    return sessionid;
}


public String getAgentno(){
    return this.agentno;
}


public String getNickname(){
    return this.nickname;
}


public String getOrgi(){
    return this.orgi;
}


public int getWaittingtime(){
    return this.waittingtime;
}


public Date getEndtime(){
    return this.endtime;
}


}