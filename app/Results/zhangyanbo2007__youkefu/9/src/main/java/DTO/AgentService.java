package DTO;
 import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
public class AgentService implements Serializable{

 private  long serialVersionUID;

 private  String agentusername;

 private  String agentno;

 private  String status;

 private  long times;

 private  Date servicetime;

 private  String orgi;

 private  String id;

 private  String username;

 private  String userid;

 private  String channel;

 private  Date logindate;

 private  int firstreplytime;

 private  boolean invite;

 private  Date invitedate;

 private  String servicekind;

 private  String sessionid;

 private  int queneindex;

 private  String source;

 private  Date endtime;

 private  String ipaddr;

 private  String owner;

 private  String osname;

 private  String browser;

 private  String nickname;

 protected  String city;

 protected  String province;

 protected  String country;

 protected  String headimgurl;

 private  String region;

 private  long sessiontimes;

 private  int waittingtime;

 private  int tokenum;

 private  Date createtime;

 private  String agent;

 private  String skill;

 private  String memo;

 private  String agentuserid;

 private  String createdate;

 private  Date updatetime;

 private  String appid;

 private  String sessiontype;

 private  String contactsid;

 private  boolean satisfaction;

 private  Date satistime;

 private  String satislevel;

 private  String satiscomment;

 private  int agentreplyinterval;

 private  int agentreplytime;

 private  int avgreplyinterval;

 private  int avgreplytime;

 private  int agentreplys;

 private  int userasks;

 private  boolean trans;

 private  String transmemo;

 private  Date transtime;

 private  String initiator;

 private  String endby;

 private  String aiid;

 private  boolean aiservice;

 private  boolean foragent;

 private  String solvestatus;

 private  boolean leavemsg;

 private  String leavemsgstatus;

 private  String qualitystatus;

 private  String qualitydisorgan;

 private  String qualitydisuser;

 private  Date qualitydistime;

 private  String assuser;

 private  String templateid;

 private  String qualitydistype;

 private  String qualityorgan;

 private  String qualityuser;

 private  int qualityscore;

 private  Date qualitytime;

 private  String qualitytype;

 private  String qualityactid;

 private  String qualityfilterid;

 private  String name;

 private  String email;

 private  String phone;

 private  String resion;

 private  String contextid;

 private  String dataid;

 private  String agentserviceid;

 private  long ordertime;

 private  String snsuser;

 private  Date lastmessage;

 private  Date waittingtimestart;

 private  Date lastgetmessage;

 private  String lastmsg;

 private  boolean tip;

 private  String agentservice;

 private  boolean agentTip;

 private  int qualitypass;

 private  boolean fromhis;

 private  boolean online;

 private  boolean disconnect;

 private  String agentskill;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


@Transient
public String getLastmsg(){
    return this.lastmsg;
}


public String getOwner(){
    return owner;
}


public String getQualitydisuser(){
    return qualitydisuser;
}


public String getIpaddr(){
    return this.ipaddr;
}


public String getHeadimgurl(){
    return this.headimgurl;
}


public String getMemo(){
    return memo;
}


public String getContactsid(){
    return contactsid;
}


public String getBrowser(){
    return this.browser;
}


@Transient
public Date getWaittingtimestart(){
    return this.waittingtimestart;
}


@Transient
public Date getLastgetmessage(){
    return this.lastgetmessage;
}


@Transient
public long getOrdertime(){
    return this.ordertime;
}


public String getAssuser(){
    return assuser;
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


public String getOsname(){
    return this.osname;
}


public String getDataid(){
    return dataid;
}


public String getSnsuser(){
    return this.snsuser;
}


public String getAgentuserid(){
    return agentuserid;
}


public String getServicekind(){
    return servicekind;
}


public String getName(){
    return name;
}


public Date getTranstime(){
    return transtime;
}


public String getQualityuser(){
    return qualityuser;
}


@Transient
public int getQueneindex(){
    return queneindex;
}


public String getCity(){
    return this.city;
}


public long getTimes(){
    return this.times;
}


@Transient
public Date getLastmessage(){
    return this.lastmessage;
}


public int getAgentreplys(){
    return agentreplys;
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


public String getAiid(){
    return aiid;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return this.id;
}


public String getSessionid(){
    return sessionid;
}


public String getEndby(){
    return endby;
}


public int getQualitypass(){
    return qualitypass;
}


public String getQualityorgan(){
    return qualityorgan;
}


public String getTemplateid(){
    return templateid;
}


public int getAvgreplytime(){
    return avgreplytime;
}


public int getFirstreplytime(){
    return firstreplytime;
}


public String getTransmemo(){
    return transmemo;
}


public String getQualitytype(){
    return qualitytype;
}


public String getStatus(){
    return this.status;
}


public String getSolvestatus(){
    return solvestatus;
}


public Date getQualitydistime(){
    return qualitydistime;
}


public String getSessiontype(){
    return this.sessiontype;
}


public String getChannel(){
    return this.channel;
}


public Date getLogindate(){
    return this.logindate;
}


public Date getSatistime(){
    return satistime;
}


public int getUserasks(){
    return userasks;
}


public int getQualityscore(){
    return qualityscore;
}


public String getQualitystatus(){
    return qualitystatus;
}


public String getResion(){
    return resion;
}


public Date getServicetime(){
    return this.servicetime;
}


public String getPhone(){
    return phone;
}


public String getCreatedate(){
    return createdate;
}


public String getQualityactid(){
    return qualityactid;
}


public String getAgentserviceid(){
    return this.agentserviceid;
}


public String getAgentusername(){
    return agentusername;
}


public String getAgent(){
    return agent;
}


public String getAppid(){
    return this.appid;
}


@Transient
public String getAgentservice(){
    return this.agentservice;
}


public int getAgentreplytime(){
    return agentreplytime;
}


public String getContextid(){
    return this.contextid;
}


public String getQualitydisorgan(){
    return qualitydisorgan;
}


public String getCountry(){
    return this.country;
}


public int getTokenum(){
    return this.tokenum;
}


public Date getQualitytime(){
    return qualitytime;
}


public String getLeavemsgstatus(){
    return leavemsgstatus;
}


public Date getInvitedate(){
    return invitedate;
}


public Date getUpdatetime(){
    return this.updatetime;
}


public String getEmail(){
    return email;
}


public String getQualitydistype(){
    return qualitydistype;
}


public String getSkill(){
    return skill;
}


public long getSessiontimes(){
    return this.sessiontimes;
}


public String getSatiscomment(){
    return satiscomment;
}


public int getAvgreplyinterval(){
    return avgreplyinterval;
}


public String getAgentno(){
    return this.agentno;
}


public int getAgentreplyinterval(){
    return agentreplyinterval;
}


public String getSatislevel(){
    return satislevel;
}


public String getInitiator(){
    return initiator;
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


public String getQualityfilterid(){
    return qualityfilterid;
}


public Date getEndtime(){
    return this.endtime;
}


public void setQualitystatus(String qualitystatus){
    this.qualitystatus = qualitystatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitystatus"))

.queryParam("qualitystatus",qualitystatus)
;
restTemplate.put(builder.toUriString(),null);
}


}