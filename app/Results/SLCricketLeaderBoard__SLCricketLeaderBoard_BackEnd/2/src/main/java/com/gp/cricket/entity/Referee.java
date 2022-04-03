package com.gp.cricket.entity;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import com.gp.cricket.Request.UserRequest;
import com.gp.cricket.Request.Impl.UserRequestImpl;
import com.gp.cricket.DTO.User;
@Entity
@Table(name = "referee")
public class Referee {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "referee_id")
 private  Integer refereeId;

@Transient
 private  User userId;

@Column(name = "userIdv2")
 private Integer userIdv2;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public Referee() {
    super();
// TODO Auto-generated constructor stub
}public Referee(Integer refereeId, @NotNull User userId) {
    super();
    this.refereeId = refereeId;
    this.userId = userId;
}
public void setRefereeId(Integer refereeId){
    this.refereeId = refereeId;
}


@Override
public String toString(){
    return "Referee [refereeId=" + refereeId + ", userId=" + userId + "]";
}


public Integer getRefereeId(){
    return refereeId;
}


public User getUserId(){
  this.userId = userrequest.getUserId(this.userIdv2);
return this.userId;
}


public void setUserId(User userId){
 userrequest.setUserId(userId,this.userIdv2);
}



}