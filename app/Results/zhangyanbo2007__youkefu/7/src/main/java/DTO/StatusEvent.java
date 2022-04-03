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
import com.ukefu.util.event.UserEvent;
public class StatusEvent implements Comparable<StatusEvent>,Serializable,UserEvent{

 private  long serialVersionUID;

 private  String id;

 private  Date createtime;

 private  Date updatetime;

 private  boolean inside;

 private  String code;

 private  String creater;

 private  String source;

 private  String answer;

 private  boolean callback;

 private  String ccquene;

 private  String calltype;

 private  String voicecalled;

 private  String servicestatus;

 private  String channelstatus;

 private  Date answertime;

 private  int ringduration;

 private  boolean current;

 private  boolean forecast;

 private  String skill;

 private  String forecastid;

 private  boolean init;

 private  String tracesip;

 private  String caller;

 private  String calling;

 private  String called;

 private  String discaller;

 private  String discalled;

 private  String agentype;

 private  String quene;

 private  String ani;

 private  String touser;

 private  String direction;

 private  String calldir;

 private  String otherdir;

 private  boolean waste;

 private  boolean apstatus;

 private  String otherlegdest;

 private  long time;

 private  String localdatetime;

 private  Date starttime;

 private  Date endtime;

 private  int duration;

 private  String status;

 private  String state;

 private  String agent;

 private  String action;

 private  String name;

 private  String host;

 private  String ipaddr;

 private  String sipaddr;

 private  String extention;

 private  String hostid;

 private  String metaname;

 private  String taskid;

 private  String actid;

 private  String batid;

 private  String dataid;

 private  String nameid;

 private  String statustype;

 private  String disphonenum;

 private  String distype;

 private  String busstype;

 private  String siptrunk;

 private  boolean prefix;

 private  boolean callstatus;

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

 private  boolean record;

 private  Date startrecord;

 private  Date endrecord;

 private  int recordtime;

 private  String recordfile;

 private  String recordfilename;

 private  String contactsid;

 private  String bridgeid;

 private  boolean bridge;

 private  boolean misscall;

 private  boolean servicesummary;

 private  String serviceid;

 private  int calls;

 private  String orgi;

 private  String country;

 private  String province;

 private  String city;

 private  String isp;

 private  boolean satisf;

 private  String satisfaction;

 private  Date satisfdate;

 private  String datestr;

 private  String hourstr;

 private  String userid;

 private  String username;

 private  String organ;

 private  boolean dtmf;

 private  String dtmfrec;

 private  int trans;

 private  Date transbegin;

 private  Date transend;

 private  String transtime;

 private  String transtatus;

 private  int transcost;

 private  String tranid;

 private  int qualitypass;

 private  String workstatus;

 private  String hangupcase;

 private  String hangupinitiator;

 private  String extno;

 private  String callresult;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


public String getServiceid(){
    return serviceid;
}


public String getBatid(){
    return batid;
}


public int getRingduration(){
    return ringduration;
}


public String getCalling(){
    return calling;
}


public String getQualitydisuser(){
    return qualitydisuser;
}


public int getDuration(){
    return duration;
}


public String getIpaddr(){
    return ipaddr;
}


public String getOtherlegdest(){
    return otherlegdest;
}


@Transient
public int getCalls(){
    return calls;
}


public String getContactsid(){
    return contactsid;
}


public String getAssuser(){
    return assuser;
}


public String getUsername(){
    return username;
}


public String getDtmfrec(){
    return dtmfrec;
}


public Date getCreatetime(){
    return createtime;
}


public String getHangupcase(){
    return hangupcase;
}


public String getAnswer(){
    return answer;
}


public String getProvince(){
    return province;
}


public String getDatestr(){
    return datestr;
}


public String getUserid(){
    return userid;
}


public String getDataid(){
    return dataid;
}


public String getName(){
    return name;
}


public int getRecordtime(){
    return recordtime;
}


public String getLocaldatetime(){
    return localdatetime;
}


public String getMetaname(){
    return metaname;
}


public String getForecastid(){
    return forecastid;
}


public String getTranstime(){
    return transtime;
}


public String getQualityuser(){
    return qualityuser;
}


public Date getTransend(){
    return transend;
}


public String getCity(){
    return city;
}


public String getBridgeid(){
    return bridgeid;
}


public String getOrgan(){
    return organ;
}


public String getSource(){
    return source;
}


public String getSiptrunk(){
    return siptrunk;
}


public String getTaskid(){
    return taskid;
}


public String getDirection(){
    return direction;
}


public long getTime(){
    return time;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public Date getSatisfdate(){
    return satisfdate;
}


public String getIsp(){
    return isp;
}


public String getTranstatus(){
    return transtatus;
}


public int getQualitypass(){
    return qualitypass;
}


public String getTouser(){
    return touser;
}


public String getQualityorgan(){
    return qualityorgan;
}


public String getTemplateid(){
    return templateid;
}


public String getHourstr(){
    return hourstr;
}


public String getCallresult(){
    return callresult;
}


public Date getAnswertime(){
    return answertime;
}


public String getOtherdir(){
    return otherdir;
}


public String getTranid(){
    return tranid;
}


public String getExtention(){
    return extention;
}


public String getQualitytype(){
    return qualitytype;
}


public String getStatus(){
    return status;
}


public String getDisphonenum(){
    return disphonenum;
}


public String getCaller(){
    return caller;
}


public String getAgentype(){
    return agentype;
}


public Date getQualitydistime(){
    return qualitydistime;
}


public String getCode(){
    return code;
}


public String getHangupinitiator(){
    return hangupinitiator;
}


public int getQualityscore(){
    return qualityscore;
}


public String getSipaddr(){
    return sipaddr;
}


public String getBusstype(){
    return busstype;
}


public String getAni(){
    return ani;
}


public String getStatustype(){
    return statustype;
}


public String getQualitystatus(){
    return qualitystatus;
}


public String getQualityactid(){
    return qualityactid;
}


public Date getEndrecord(){
    return endrecord;
}


public String getActid(){
    return actid;
}


public String getRecordfile(){
    return recordfile;
}


public String getHostid(){
    return hostid;
}


public Date getStartrecord(){
    return startrecord;
}


public String getChannelstatus(){
    return channelstatus;
}


public String getWorkstatus(){
    return workstatus;
}


public String getAction(){
    return action;
}


public String getAgent(){
    return agent;
}


public String getVoicecalled(){
    return voicecalled;
}


public String getTracesip(){
    return tracesip;
}


public String getQualitydisorgan(){
    return qualitydisorgan;
}


public String getCountry(){
    return country;
}


@Transient
public String getExtno(){
    return extno;
}


public String getDiscaller(){
    return discaller;
}


public String getNameid(){
    return nameid;
}


public String getQuene(){
    return quene;
}


public String getCalltype(){
    return calltype;
}


public Date getQualitytime(){
    return qualitytime;
}


public int getTranscost(){
    return transcost;
}


public String getCcquene(){
    return ccquene;
}


public String getHost(){
    return host;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getCalldir(){
    return calldir;
}


public String getQualitydistype(){
    return qualitydistype;
}


public String getDiscalled(){
    return discalled;
}


public String getSkill(){
    return skill;
}


public String getRecordfilename(){
    return recordfilename;
}


public int getTrans(){
    return trans;
}


public String getSatisfaction(){
    return satisfaction;
}


public String getCreater(){
    return creater;
}


public String getCalled(){
    return called;
}


public Date getTransbegin(){
    return transbegin;
}


public String getDistype(){
    return distype;
}


public Date getStarttime(){
    return starttime;
}


public String getState(){
    return state;
}


public String getOrgi(){
    return orgi;
}


public String getQualityfilterid(){
    return qualityfilterid;
}


public Date getEndtime(){
    return endtime;
}


public String getServicestatus(){
    return servicestatus;
}


public void setQualitydistime(Date qualitydistime){
    this.qualitydistime = qualitydistime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitydistime"))

.queryParam("qualitydistime",qualitydistime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualitytype(String qualitytype){
    this.qualitytype = qualitytype;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitytype"))

.queryParam("qualitytype",qualitytype)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAssuser(String assuser){
    this.assuser = assuser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAssuser"))

.queryParam("assuser",assuser)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTemplateid(String templateid){
    this.templateid = templateid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTemplateid"))

.queryParam("templateid",templateid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualityactid(String qualityactid){
    this.qualityactid = qualityactid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualityactid"))

.queryParam("qualityactid",qualityactid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualityfilterid(String qualityfilterid){
    this.qualityfilterid = qualityfilterid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualityfilterid"))

.queryParam("qualityfilterid",qualityfilterid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualitystatus(String qualitystatus){
    this.qualitystatus = qualitystatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitystatus"))

.queryParam("qualitystatus",qualitystatus)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualitydisorgan(String qualitydisorgan){
    this.qualitydisorgan = qualitydisorgan;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitydisorgan"))

.queryParam("qualitydisorgan",qualitydisorgan)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualitydistype(String qualitydistype){
    this.qualitydistype = qualitydistype;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitydistype"))

.queryParam("qualitydistype",qualitydistype)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualitydisuser(String qualitydisuser){
    this.qualitydisuser = qualitydisuser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitydisuser"))

.queryParam("qualitydisuser",qualitydisuser)
;
restTemplate.put(builder.toUriString(),null);
}


}