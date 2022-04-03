package cn.gson.oasys.model.entity.mail;
 import java.util.Date;
public class Pagemail {

 private  Long mailId;

 private  Long mailType;

 private  Long mailStatusid;

 private  String mailTitle;

 private  String inReceiver;

 private  Long mailFileid;

 private  Date mailCreateTime;

 private  Boolean star;

 private  Boolean read;

public Pagemail() {
}public Pagemail(Long mailId, Long mailType, Long mailStatusid, String mailTitle, String inReceiver, Long mailFileid, Date mailCreateTime, Boolean star, Boolean read) {
    super();
    this.mailId = mailId;
    this.mailType = mailType;
    this.mailStatusid = mailStatusid;
    this.mailTitle = mailTitle;
    this.inReceiver = inReceiver;
    this.mailFileid = mailFileid;
    this.mailCreateTime = mailCreateTime;
    this.star = star;
    this.read = read;
}
public void setMailId(Long mailId){
    this.mailId = mailId;
}


public String getInReceiver(){
    return inReceiver;
}


public void setInReceiver(String inReceiver){
    this.inReceiver = inReceiver;
}


public Date getMailCreateTime(){
    return mailCreateTime;
}


public void setStar(Boolean star){
    this.star = star;
}


public void setMailType(Long mailType){
    this.mailType = mailType;
}


public String getMailTitle(){
    return mailTitle;
}


public Boolean getRead(){
    return read;
}


public void setMailCreateTime(Date mailCreateTime){
    this.mailCreateTime = mailCreateTime;
}


public Boolean getStar(){
    return star;
}


public void setMailFileid(Long mailFileid){
    this.mailFileid = mailFileid;
}


public Long getMailStatusid(){
    return mailStatusid;
}


public Long getMailId(){
    return mailId;
}


public void setRead(Boolean read){
    this.read = read;
}


public Long getMailFileid(){
    return mailFileid;
}


public Long getMailType(){
    return mailType;
}


@Override
public String toString(){
    return "Pagemail [mailId=" + mailId + ", mailType=" + mailType + ", mailStatusid=" + mailStatusid + ", mailTitle=" + mailTitle + ", inReceiver=" + inReceiver + ", mailFileid=" + mailFileid + ", mailCreateTime=" + mailCreateTime + ", star=" + star + ", read=" + read + "]";
}


public void setMailStatusid(Long mailStatusid){
    this.mailStatusid = mailStatusid;
}


public void setMailTitle(String mailTitle){
    this.mailTitle = mailTitle;
}


}