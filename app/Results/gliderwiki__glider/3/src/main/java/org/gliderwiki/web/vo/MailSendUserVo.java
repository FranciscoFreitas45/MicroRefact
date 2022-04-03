package org.gliderwiki.web.vo;
 import java.util.Date;
import org.gliderwiki.framework.util.DateUtil;
public class MailSendUserVo extends BaseObjectBean{

 private  Integer weUserIdx;

 private  String weUserId;

 private  String weUserNick;

 private  String weUserName;

 private  String weUserEmail;

 private  Date weUserJoinDate;

 private  String weUserAuthYn;

 private  Date weUserAuthDate;

 private  Date weInsDate;

 private  String weSendStatus;

 private  Date weVisitDate;

 private  Integer weGrade;

 private  String wePoint;

 private  String weTechYn;


public String getWeUserId(){
    return weUserId;
}


public void setWeUserNick(String weUserNick){
    this.weUserNick = weUserNick;
}


public String getWeUserName(){
    return weUserName;
}


public Integer getWeUserIdx(){
    return weUserIdx;
}


public String getWeSendStatus(){
    return weSendStatus;
}


public void setWeVisitDate(Date weVisitDate){
    this.weVisitDate = weVisitDate;
}


public void setWeTechYn(String weTechYn){
    this.weTechYn = weTechYn;
}


public Date getWeUserJoinDate(){
    return weUserJoinDate;
}


public void setWeUserId(String weUserId){
    this.weUserId = weUserId;
}


public void setWeUserIdx(Integer weUserIdx){
    this.weUserIdx = weUserIdx;
}


public void setWePoint(String wePoint){
    this.wePoint = wePoint;
}


public void setWeUserJoinDate(Date weUserJoinDate){
    this.weUserJoinDate = weUserJoinDate;
}


public void setWeUserName(String weUserName){
    this.weUserName = weUserName;
}


public void setWeGrade(Integer weGrade){
    this.weGrade = weGrade;
}


public String getWeTechYn(){
    return weTechYn;
}


public void setWeUserEmail(String weUserEmail){
    this.weUserEmail = weUserEmail;
}


public void setWeUserAuthYn(String weUserAuthYn){
    this.weUserAuthYn = weUserAuthYn;
}


public Date getWeVisitDate(){
    return weVisitDate;
}


public void setWeUserAuthDate(Date weUserAuthDate){
    this.weUserAuthDate = weUserAuthDate;
}


public String getWeUserNick(){
    return weUserNick;
}


public String getWePoint(){
    return wePoint;
}


public String getWeUserEmail(){
    return weUserEmail;
}


public void setWeInsDate(Date weInsDate){
    this.weInsDate = weInsDate;
}


public void setWeSendStatus(String weSendStatus){
    this.weSendStatus = weSendStatus;
}


public Integer getWeGrade(){
    return weGrade;
}


public Date getWeUserAuthDate(){
    return weUserAuthDate;
}


public String getWeUserAuthYn(){
    return weUserAuthYn;
}


public Date getWeInsDate(){
    return weInsDate;
}


}