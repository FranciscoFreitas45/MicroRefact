package cn.gson.oasys.model.entity.process;
 import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_reviewed")
public class Reviewed {

@Id
@Column(name = "reviewed_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long reviewedId;

@Transient
 private  User userId;

 private  String advice;

 private  Long statusId;

@Column(name = "reviewed_time")
 private  Date reviewedTime;

@ManyToOne
@JoinColumn(name = "pro_id")
 private  ProcessList proId;

@Column(name = "del")
 private  Boolean del;

@Transient
 private  String username;

@Column(name = "userIdv2")
 private Long userIdv2;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


public void setStatusId(Long statusId){
    this.statusId = statusId;
}


public void setUsername(String username){
    this.username = username;
}


public Date getReviewedTime(){
    return reviewedTime;
}


public void setDel(Boolean del){
    this.del = del;
}


public Long getReviewedId(){
    return reviewedId;
}


public void setProId(ProcessList proId){
    this.proId = proId;
}


public String getUsername(){
    return username;
}


public ProcessList getProId(){
    return proId;
}


public void setAdvice(String advice){
    this.advice = advice;
}


public Long getStatusId(){
    return statusId;
}


public void setReviewedId(Long reviewedId){
    this.reviewedId = reviewedId;
}


public void setReviewedTime(Date reviewedTime){
    this.reviewedTime = reviewedTime;
}


@Override
public String toString(){
    return "Reviewed [reviewedId=" + reviewedId + ", advice=" + advice + ", statusId=" + statusId + ", reviewedTime=" + reviewedTime + ", del=" + del + ", username=" + username + "]";
}


public String getAdvice(){
    return advice;
}


public Boolean getDel(){
    return del;
}


public User getUserId(){
  this.userId = userrequest.getUserId(this.userIdv2);
return this.userId;
}


public void setUserId(User userId){
 userrequest.setUserId(userId,this.userIdv2);
}



}