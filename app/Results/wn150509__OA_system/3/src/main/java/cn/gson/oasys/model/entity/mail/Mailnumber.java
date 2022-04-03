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
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_mailnumber")
public class Mailnumber {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "mail_number_id")
 private  Long mailNumberId;

@Column(name = "mail_type")
 private  Long mailType;

@Column(name = "status")
 private  Long status;

@Transient
 private  User mailUserId;

@Column(name = "mail_user_name", nullable = false)
@NotEmpty(message = "发件昵称不能为空")
 private  String mailUserName;

@Column(name = "mail_create_time")
 private  Date mailCreateTime;

@Column(name = "mail_account", nullable = false)
@NotEmpty(message = "邮件账号不能为空")
@Pattern(regexp = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$", message = "请填写正确邮箱号")
 private  String mailAccount;

@Column(name = "password", nullable = false)
@NotEmpty(message = "授权码不能为空")
 private  String password;

@Column(name = "mail_des")
 private  String mailDes;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public Mailnumber() {
}
public void setPassword(String password){
    this.password = password;
}


public Date getMailCreateTime(){
    return mailCreateTime;
}


public void setMailType(Long mailType){
    this.mailType = mailType;
}


public String getMailAccount(){
    return mailAccount;
}


public void setMailCreateTime(Date mailCreateTime){
    this.mailCreateTime = mailCreateTime;
}


public Long getStatus(){
    return status;
}


public void setStatus(Long status){
    this.status = status;
}


public String getMailDes(){
    return mailDes;
}


public String getMailUserName(){
    return mailUserName;
}


public String getPassword(){
    return password;
}


public void setMailNumberId(Long mailNumberId){
    this.mailNumberId = mailNumberId;
}


public User getMailUserId(){
  this.mailUserId = userrequest.getMailUserId(this.userId);
return this.mailUserId;
}


public Long getMailNumberId(){
    return mailNumberId;
}


public void setMailUserName(String mailUserName){
    this.mailUserName = mailUserName;
}


public void setMailAccount(String mailAccount){
    this.mailAccount = mailAccount;
}


public Long getMailType(){
    return mailType;
}


@Override
public String toString(){
    return "Mailnumber [mailNumberId=" + mailNumberId + ", mailType=" + mailType + ", status=" + status + ", mailUserName=" + mailUserName + ", mailCreateTime=" + mailCreateTime + ", mailAccount=" + mailAccount + ", password=" + password + ", mailDes=" + mailDes + "]";
}


public void setMailUserId(User mailUserId){
 userrequest.setMailUserId(mailUserId,this.userId);
}



public void setMailDes(String mailDes){
    this.mailDes = mailDes;
}


}