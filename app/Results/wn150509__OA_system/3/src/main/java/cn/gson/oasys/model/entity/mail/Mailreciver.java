package cn.gson.oasys.model.entity.mail;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_mail_reciver")
public class Mailreciver {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pk_id")
 private  Long pkId;

@ManyToOne
@JoinColumn(name = "mail_id")
 private  Inmaillist mailId;

@Transient
 private  User reciverId;

@Column(name = "is_read")
 private  Boolean read;

@Column(name = "is_star")
 private  Boolean star;

@Column(name = "is_del")
 private  Boolean del;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public Mailreciver() {
}
public void setMailId(Inmaillist mailId){
    this.mailId = mailId;
}


public void setStar(Boolean star){
    this.star = star;
}


public Boolean getRead(){
    return read;
}


public void setDel(Boolean del){
    this.del = del;
}


public Boolean getStar(){
    return star;
}


public User getReciverId(){
  this.reciverId = userrequest.getReciverId(this.userId);
return this.reciverId;
}


public Long getPkId(){
    return pkId;
}


public void setPkId(Long pkId){
    this.pkId = pkId;
}


public void setReciverId(User reciverId){
 userrequest.setReciverId(reciverId,this.userId);
}



public Inmaillist getMailId(){
    return mailId;
}


public void setRead(Boolean read){
    this.read = read;
}


@Override
public String toString(){
    return "Mailreciver [pkId=" + pkId + ", mailId=" + mailId + ", read=" + read + ", star=" + star + ", del=" + del + "]";
}


public Boolean getDel(){
    return del;
}


}