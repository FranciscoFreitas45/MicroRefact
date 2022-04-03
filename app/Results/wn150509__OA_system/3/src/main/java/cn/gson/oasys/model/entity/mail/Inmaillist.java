package cn.gson.oasys.model.entity.mail;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.NotEmpty;
import cn.gson.oasys.model.entity.note.Attachment;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_in_mail_list")
public class Inmaillist {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "mail_id")
 private  Long mailId;

@Column(name = "mail_type")
 private  Long mailType;

@Column(name = "mail_status_id")
 private  Long mailStatusid;

@Transient
 private  User mailUserid;

@Column(name = "mail_title")
@NotEmpty(message = "邮件主题不能为空")
 private  String mailTitle;

@Column(name = "mail_content")
@NotEmpty(message = "邮件内容不能为空")
 private  String content;

@Column(name = "in_receiver")
 private  String inReceiver;

@ManyToOne
@JoinColumn(name = "mail_file_id")
 private  Attachment mailFileid;

@Column(name = "mail_create_time")
 private  Date mailCreateTime;

@ManyToOne
@JoinColumn(name = "mail_number_id")
 private  Mailnumber mailNumberid;

@Column(name = "mail_del")
 private  Boolean del;

@Column(name = "mail_push")
 private  Boolean push;

@Column(name = "mail_star")
 private  Boolean star;

@Transient
 private  Long inmail;

@Transient
 private  String huizhuan;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public Inmaillist() {
}
public void setContent(String content){
    this.content = content;
}


public void setStar(Boolean star){
    this.star = star;
}


public String getInReceiver(){
    return inReceiver;
}


public Date getMailCreateTime(){
    return mailCreateTime;
}


public String getMailTitle(){
    return mailTitle;
}


public String getContent(){
    return content;
}


public void setDel(Boolean del){
    this.del = del;
}


public String getHuizhuan(){
    return huizhuan;
}


public Boolean getStar(){
    return star;
}


public void setPush(Boolean push){
    this.push = push;
}


public void setMailNumberid(Mailnumber mailNumberid){
    this.mailNumberid = mailNumberid;
}


public Boolean getPush(){
    return push;
}


public Mailnumber getMailNumberid(){
    return mailNumberid;
}


public Long getInmail(){
    return inmail;
}


public void setMailUserid(User mailUserid){
 userrequest.setMailUserid(mailUserid,this.userId);
}



public Boolean getDel(){
    return del;
}


public void setMailStatusid(Long mailStatusid){
    this.mailStatusid = mailStatusid;
}


public void setMailTitle(String mailTitle){
    this.mailTitle = mailTitle;
}


public void setMailId(Long mailId){
    this.mailId = mailId;
}


public void setHuizhuan(String huizhuan){
    this.huizhuan = huizhuan;
}


public void setInmail(Long inmail){
    this.inmail = inmail;
}


public void setInReceiver(String inReceiver){
    this.inReceiver = inReceiver;
}


public void setMailType(Long mailType){
    this.mailType = mailType;
}


public void setMailCreateTime(Date mailCreateTime){
    this.mailCreateTime = mailCreateTime;
}


public void setMailFileid(Attachment mailFileid){
    this.mailFileid = mailFileid;
}


public User getMailUserid(){
  this.mailUserid = userrequest.getMailUserid(this.userId);
return this.mailUserid;
}


public Long getMailStatusid(){
    return mailStatusid;
}


public Long getMailId(){
    return mailId;
}


public Attachment getMailFileid(){
    return mailFileid;
}


public Long getMailType(){
    return mailType;
}


@Override
public String toString(){
    return "Inmaillist [mailId=" + mailId + ", mailType=" + mailType + ", mailStatusid=" + mailStatusid + ", mailTitle=" + mailTitle + ", content=" + content + ", inReceiver=" + inReceiver + ", mailCreateTime=" + mailCreateTime + ", mailNumberid=" + mailNumberid + ", del=" + del + ", push=" + push + ", star=" + star + ", inmail=" + inmail + ", huizhuan=" + huizhuan + "]";
}


}