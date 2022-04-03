package com.ukefu.webim.web.model;
 import java.util.LinkedList;
import com.ukefu.util.IP;
public class AiUser {

 private  long serialVersionUID;

 private  String id;

 private  String userid;

 private  long time;

 private  IP ipdata;

 private  String orgi;

 private  String agentserviceid;

 private  String sessionid;

 private  String contextid;

 private  String appid;

 private  String channel;

 private  String username;

 private  String aiid;

 private  String skill;

 private  String busstype;

 private  String aitype;

 private  String bussid;

 private  String dataid;

 private  boolean bussend;

 private  int userask;

 private  boolean agent;

 private  int timeoutnums;

 private  int retimes;

 private  int errortimes;

 private  String queresultid;

 private  String callnumber;

 private  boolean interrupt;

 private  int interrupttime;

 private  int maxspreak;

 private  boolean bridge;

 private  String trans;

 private  LinkedList<QueSurveyResultQuestion> questionList;

public AiUser(String id, String userid, long time, String orgi, IP ipdata) {
    this.id = id.replace("-", "");
    this.userid = userid;
    this.time = time;
    this.ipdata = ipdata;
    this.orgi = orgi;
    this.questionList = new LinkedList<>();
}
public void setIpdata(IP ipdata){
    this.ipdata = ipdata;
}


public void setAiid(String aiid){
    this.aiid = aiid;
}


public void setRetimes(int retimes){
    this.retimes = retimes;
}


public void setChannel(String channel){
    this.channel = channel;
}


public boolean isBridge(){
    return bridge;
}


public void setAppid(String appid){
    this.appid = appid;
}


public int getUserask(){
    return userask;
}


public String getCallnumber(){
    return callnumber;
}


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
}


public boolean isAgent(){
    return agent;
}


public String getChannel(){
    return channel;
}


public void setId(String id){
    this.id = id;
}


public void setCallnumber(String callnumber){
    this.callnumber = callnumber;
}


public void setInterrupt(boolean interrupt){
    this.interrupt = interrupt;
}


public void setTime(long time){
    this.time = time;
}


public void setMaxspreak(int maxspreak){
    this.maxspreak = maxspreak;
}


public void setTrans(String trans){
    this.trans = trans;
}


public String getBusstype(){
    return busstype;
}


public void setAitype(String aitype){
    this.aitype = aitype;
}


public LinkedList<QueSurveyResultQuestion> getQuestionList(){
    return questionList;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public boolean isBussend(){
    return bussend;
}


public String getBussid(){
    return bussid;
}


public void setSkill(String skill){
    this.skill = skill;
}


public String getSkill(){
    return skill;
}


public String getAitype(){
    return aitype;
}


public int getMaxspreak(){
    return maxspreak;
}


public String getAiid(){
    return aiid;
}


public long getTime(){
    return time;
}


public String getTrans(){
    return trans;
}


public int getInterrupttime(){
    return interrupttime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public String getAgentserviceid(){
    return agentserviceid;
}


public void setContextid(String contextid){
    this.contextid = contextid;
}


public String getId(){
    return id;
}


public void setTimeoutnums(int timeoutnums){
    this.timeoutnums = timeoutnums;
}


public void setQueresultid(String queresultid){
    this.queresultid = queresultid;
}


public void setErrortimes(int errortimes){
    this.errortimes = errortimes;
}


public String getUsername(){
    return username;
}


public void setQuestionList(LinkedList<QueSurveyResultQuestion> questionList){
    this.questionList = questionList;
}


public void setBussend(boolean bussend){
    this.bussend = bussend;
}


public String getQueresultid(){
    return queresultid;
}


public IP getIpdata(){
    return ipdata;
}


public String getSessionid(){
    return sessionid;
}


public int getErrortimes(){
    return errortimes;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


public boolean isInterrupt(){
    return interrupt;
}


public void setUsername(String username){
    this.username = username;
}


public void setBusstype(String busstype){
    this.busstype = busstype;
}


public String getAppid(){
    return appid;
}


public void setAgent(boolean agent){
    this.agent = agent;
}


public void setAgentserviceid(String agentserviceid){
    this.agentserviceid = agentserviceid;
}


public String getDataid(){
    return dataid;
}


public void setUserask(int userask){
    this.userask = userask;
}


public void setBridge(boolean bridge){
    this.bridge = bridge;
}


public String getOrgi(){
    return orgi;
}


public int getTimeoutnums(){
    return timeoutnums;
}


public String getContextid(){
    return contextid;
}


public int getRetimes(){
    return retimes;
}


public void setInterrupttime(int interrupttime){
    this.interrupttime = interrupttime;
}


public void setBussid(String bussid){
    this.bussid = bussid;
}


}