package org.live.live.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import java.util.Date;
import org.live.Request.MobileUserRequest;
import org.live.Request.Impl.MobileUserRequestImpl;
import org.live.DTO.MobileUser;
@Entity
@Table(name = "live_anchor")
public class Anchor extends BaseEntity{

@Transient
 private  MobileUser user;

@Column
 private  String idCard;

@Column
 private  String realName;

@Column
@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;

@Column
 private  String description;

@Column(name = "idQ2RW")
 private String idQ2RW;

@Transient
 private MobileUserRequest mobileuserrequest = new MobileUserRequestImpl();;


public void setRealName(String realName){
    this.realName = realName;
}


public Date getCreateTime(){
    return createTime;
}


public String getIdCard(){
    return idCard;
}


public MobileUser getUser(){
  this.user = mobileuserrequest.getUser(this.idQ2RW);
return this.user;
}}



public void setIdCard(String idCard){
    this.idCard = idCard;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setDescription(String description){
    this.description = description;
}


public void setUser(MobileUser user){
this.idQ2RW = user.getUser() ;
mobileuserrequest.setUser(user,this.idQ2RW);
 this.user = user;
}



public String getRealName(){
    return realName;
}


public String getDescription(){
    return description;
}


}