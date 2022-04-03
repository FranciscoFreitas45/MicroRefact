package com.ukefu.webim.util.server.message;
 import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
import com.ukefu.util.event.UserEvent;
import com.ukefu.webim.util.OnlineUserUtils;
@Entity
@Table(name = "uk_chat_message")
@org.hibernate.annotations.Proxy(lazy = false)
public class ChatMessage implements UserEvent{

 private  long serialVersionUID;

 private  String id;

 private  String appid;

 private  String userid;

 private  String username;

 private  String aiid;

 private  String touser;

 private  String tousername;

 private  boolean cooperation;

 private  String msgtype;

 private  String creater;

 private  String usession;

 private  String agentserviceid;

 private  String sessionid;

 private  String topicid;

 private  String topicatid;

 private  boolean topic;

 private  boolean aichat;

 private  String message;

 private  String expmsg;

 private  boolean readstatus;

 private  boolean useful;

 private  String notreason;

 private  Date usetime;

 private  String orgi;

 private  String channel;

 private  String model;

 private  String chatype;

 private  Date lastagentmsgtime;

 private  Date lastmsgtime;

 private  int agentreplytime;

 private  int agentreplyinterval;

 private  String batid;

 private  String headimgurl;

 private  String filename;

 private  int filesize;

 private  String attachmentid;

 private  boolean datastatus;

 private  String ckind;

 private  String ckindname;

 private  String clabel;

 private  String clabelname;

 private  String matchtype;

 private  String mediaid;

 private  String locx;

 private  String locy;

 private  long updatetime;

 private  int duration;

 private  String title;

 private  String qusid;

 private  String code;

 private  String score;

 private  String url;

 private  String bustype;

 private  String scale;

 private  String suggestmsg;

 private  boolean quickagent;

 private  int tokenum;

 private  String agentuser;

 private  String type;

 private  String contextid;

 private  String calltype;

 private  String createtime;


public String getBatid(){
    return batid;
}


public void setChannel(String channel){
    this.channel = channel;
}


public void setDatastatus(boolean datastatus){
    this.datastatus = datastatus;
}


public String getClabelname(){
    return clabelname;
}


public void setAppid(String appid){
    this.appid = appid;
}


public void setBatid(String batid){
    this.batid = batid;
}


public int getDuration(){
    return duration;
}


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
}


public String getChannel(){
    return channel;
}


public void setTopicatid(String topicatid){
    this.topicatid = topicatid;
}


public String getCkindname(){
    return ckindname;
}


public String getHeadimgurl(){
    return headimgurl;
}


public String getTousername(){
    return tousername;
}


public void setMsgtype(String msgtype){
    this.msgtype = msgtype;
}


public String getCode(){
    return code;
}


public String getTopicatid(){
    return topicatid;
}


public void setReadstatus(boolean readstatus){
    this.readstatus = readstatus;
}


public void setLastagentmsgtime(Date lastagentmsgtime){
    this.lastagentmsgtime = lastagentmsgtime;
}


public void setCooperation(boolean cooperation){
    this.cooperation = cooperation;
}


public void setCode(String code){
    this.code = code;
}


public void setClabel(String clabel){
    this.clabel = clabel;
}


public String getMediaid(){
    return mediaid;
}


public String getLocx(){
    return locx;
}


public String getLocy(){
    return locy;
}


public void setTopic(boolean topic){
    this.topic = topic;
}


public String getNotreason(){
    return notreason;
}


public void setExpmsg(String expmsg){
    this.expmsg = expmsg;
}


public String getType(){
    return type;
}


public void setFilesize(int filesize){
    this.filesize = filesize;
}


public boolean isUseful(){
    return useful;
}


public String getClabel(){
    return clabel;
}


public void setContextid(String contextid){
    this.contextid = contextid;
}


public String getAgentserviceid(){
    return agentserviceid;
}


public void setAichat(boolean aichat){
    this.aichat = aichat;
}


public void setCalltype(String calltype){
    this.calltype = calltype;
}


public String getUsername(){
    return username;
}


public void setFilename(String filename){
    this.filename = filename;
}


public Date getLastmsgtime(){
    return lastmsgtime;
}


public String getCreatetime(){
    return createtime;
}


public void setMatchtype(String matchtype){
    this.matchtype = matchtype;
}


public void setChatype(String chatype){
    this.chatype = chatype;
}


public void setTouser(String touser){
    this.touser = touser;
}


public boolean isTopic(){
    return topic;
}


public Date getLastagentmsgtime(){
    return lastagentmsgtime;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


public void setAgentuser(String agentuser){
    this.agentuser = agentuser;
}


public void setUsetime(Date usetime){
    this.usetime = usetime;
}


public String getMessage(){
    return message;
}


public void setBustype(String bustype){
    this.bustype = bustype;
}


public void setMessage(String message){
    this.message = message;
}


public void setMediaid(String mediaid){
    this.mediaid = mediaid;
}


public void setCkindname(String ckindname){
    this.ckindname = ckindname;
}


public String getAppid(){
    return appid;
}


public void setAgentreplytime(int agentreplytime){
    this.agentreplytime = agentreplytime;
}


public void setAgentserviceid(String agentserviceid){
    this.agentserviceid = agentserviceid;
}


public String getFilename(){
    return filename;
}


public void setQusid(String qusid){
    this.qusid = qusid;
}


public void setHeadimgurl(String headimgurl){
    this.headimgurl = headimgurl;
}


public int getAgentreplytime(){
    return agentreplytime;
}


public String getQusid(){
    return qusid;
}


public String getContextid(){
    return contextid;
}


public String getExpmsg(){
    return expmsg;
}


public String getChatype(){
    return chatype;
}


public void setTokenum(int tokenum){
    this.tokenum = tokenum;
}


@Transient
public String getAgentuser(){
    return agentuser;
}


public void setAiid(String aiid){
    this.aiid = aiid;
}


public String getCkind(){
    return ckind;
}


@Transient
public int getTokenum(){
    return tokenum;
}


public void setAgentreplyinterval(int agentreplyinterval){
    this.agentreplyinterval = agentreplyinterval;
}


public String getAttachmentid(){
    return attachmentid;
}


public String getCalltype(){
    return calltype;
}


public String getTitle(){
    return title;
}


public Date getUsetime(){
    return usetime;
}


public void setId(String id){
    this.id = id;
}


public boolean isQuickagent(){
    return quickagent;
}


public void setTopicid(String topicid){
    this.topicid = topicid;
}


public String getModel(){
    return model;
}


public void setQuickagent(boolean quickagent){
    this.quickagent = quickagent;
}


public void setScale(String scale){
    this.scale = scale;
}


public String getBustype(){
    return bustype;
}


public long getUpdatetime(){
    return updatetime;
}


public boolean isCooperation(){
    return cooperation;
}


public void setTitle(String title){
    this.title = title;
}


public void setUrl(String url){
    this.url = url;
}


public String getTopicid(){
    return topicid;
}


public String getUrl(){
    return url;
}


public String getScale(){
    return scale;
}


public void setSuggestmsg(String suggestmsg){
    this.suggestmsg = suggestmsg;
}


public void setModel(String model){
    this.model = model;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setDuration(int duration){
    this.duration = duration;
}


public void setAttachmentid(String attachmentid){
    this.attachmentid = attachmentid;
}


public void setLastmsgtime(Date lastmsgtime){
    this.lastmsgtime = lastmsgtime;
}


public String getAiid(){
    return aiid;
}


public void setUpdatetime(long updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public void setUsession(String usession){
    this.usession = usession;
}


public void setTousername(String tousername){
    this.tousername = tousername;
}


public String getSuggestmsg(){
    return suggestmsg;
}


public void setCreatetime(String createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getSessionid(){
    return sessionid;
}


public String getMatchtype(){
    return matchtype;
}


public String getTouser(){
    return touser;
}


public int getAgentreplyinterval(){
    return agentreplyinterval;
}


public void setClabelname(String clabelname){
    this.clabelname = clabelname;
}


public String getUsession(){
    return usession;
}


public int getFilesize(){
    return filesize;
}


public boolean isAichat(){
    return aichat;
}


public void setUsername(String username){
    this.username = username;
}


public void setCkind(String ckind){
    this.ckind = ckind;
}


public void setType(String type){
    this.type = type;
}


public void setUseful(boolean useful){
    this.useful = useful;
}


@Transient
public List<OtherMessageItem> getSuggest(){
    List<OtherMessageItem> otherMessageItemList = null;
    if (!StringUtils.isBlank(this.getSuggestmsg())) {
        try {
            otherMessageItemList = OnlineUserUtils.objectMapper.readValue(this.getSuggestmsg(), UKTools.getCollectionType(ArrayList.class, OtherMessageItem.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return otherMessageItemList;
}


public String getMsgtype(){
    return msgtype;
}


public void setLocx(String locx){
    this.locx = locx;
}


public void setNotreason(String notreason){
    this.notreason = notreason;
}


public void setLocy(String locy){
    this.locy = locy;
}


public String getOrgi(){
    return orgi;
}


public boolean isReadstatus(){
    return readstatus;
}


public String getScore(){
    return score;
}


public boolean isDatastatus(){
    return datastatus;
}


public void setScore(String score){
    this.score = score;
}


}