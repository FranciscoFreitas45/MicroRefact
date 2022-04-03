package DTO;
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
public int getUserask(){
    return userask;
}


public String getCallnumber(){
    return callnumber;
}


public String getChannel(){
    return channel;
}


public String getBusstype(){
    return busstype;
}


public LinkedList<QueSurveyResultQuestion> getQuestionList(){
    return questionList;
}


public String getBussid(){
    return bussid;
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


public String getAgentserviceid(){
    return agentserviceid;
}


public String getId(){
    return id;
}


public String getUsername(){
    return username;
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


public String getAppid(){
    return appid;
}


public String getDataid(){
    return dataid;
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


}