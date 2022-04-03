package com.ukefu.webim.web.model;
 import com.ukefu.core.UKDataContext;
import Interface.SNSAccount;
public class MessageInContent implements MessageDataBean{

 private  long serialVersionUID;

 public  String id;

 private  String nickName;

 private  String orgi;

 private  String message;

 private  String filename;

 private  int filesize;

 private  String messageType;

 private  String fromUser;

 private  String calltype;

 private  String toUser;

 private  SNSAccount snsAccount;

 private  AgentUser agentUser;

 private  Object channelMessage;

 private  String agentserviceid;

 private  boolean topic;

 private  int duration;

 private  String attachmentid;

 private  boolean noagent;

 private  boolean quickagent;

 private  Object user;

 private  String contextid;

 private  String createtime;


public void setDuration(int duration){
    this.duration = duration;
}


public void setAttachmentid(String attachmentid){
    this.attachmentid = attachmentid;
}


public String getNickName(){
    return nickName;
}


public Object getUser(){
    return user;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public void setContextid(String contextid){
    this.contextid = contextid;
}


public String getAgentserviceid(){
    return agentserviceid;
}


public String getId(){
    return id;
}


public String getAttachmentid(){
    return attachmentid;
}


public void setCalltype(String calltype){
    this.calltype = calltype;
}


public void setFilename(String filename){
    this.filename = filename;
}


public String getCalltype(){
    return calltype;
}


public String getToUser(){
    return toUser;
}


public SNSAccount getSnsAccount(){
    return snsAccount;
}


public String getCreatetime(){
    return createtime;
}


public int getDuration(){
    return duration;
}


public void setChannelMessage(Object channelMessage){
    this.channelMessage = channelMessage;
}


public boolean isNoagent(){
    return noagent;
}


public void setId(String id){
    this.id = id;
}


public boolean isTopic(){
    return topic;
}


public void setCreatetime(String createtime){
    this.createtime = createtime;
}


public void setUser(Object user){
    this.user = user;
}


public boolean isQuickagent(){
    return quickagent;
}


public String getMessageType(){
    return messageType;
}


public String getFromUser(){
    return fromUser;
}


public void setQuickagent(boolean quickagent){
    this.quickagent = quickagent;
}


public int getFilesize(){
    return filesize;
}


public Object getChannelMessage(){
    return channelMessage;
}


public String getMessage(){
    return message;
}


public void setNickName(String nickName){
    this.nickName = nickName;
}


public void setSnsAccount(SNSAccount snsAccount){
    this.snsAccount = snsAccount;
}


public void setToUser(String toUser){
    this.toUser = toUser;
}


public void setMessage(String message){
    this.message = message;
}


public void setMessageType(String messageType){
    this.messageType = messageType;
}


public void setTopic(boolean topic){
    this.topic = topic;
}


public AgentUser getAgentUser(){
    return agentUser;
}


public void setAgentUser(AgentUser agentUser){
    this.agentUser = agentUser;
}


public String getFilename(){
    return filename;
}


public void setAgentserviceid(String agentserviceid){
    this.agentserviceid = agentserviceid;
}


public void setFromUser(String fromUser){
    this.fromUser = fromUser;
}


public void setFilesize(int filesize){
    this.filesize = filesize;
}


public String getOrgi(){
    return orgi;
}


public String getContextid(){
    return contextid;
}


public void setNoagent(boolean noagent){
    this.noagent = noagent;
}


}