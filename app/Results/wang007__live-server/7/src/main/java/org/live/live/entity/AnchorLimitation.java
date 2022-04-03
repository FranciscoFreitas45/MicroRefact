package org.live.live.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import java.util.Date;
import org.live.Request.MobileUserRequest;
import org.live.Request.Impl.MobileUserRequestImpl;
import org.live.DTO.MobileUser;
@Entity
@Table(name = "live_anchor_limitation")
public class AnchorLimitation extends BaseEntity{

 public  int LIMIT_TYPE_SHUTUP;

 public  int LIMIT_TYPE_KICKOUT;

@Transient
 private  MobileUser user;

@ManyToOne
@JoinColumn(name = "live_room_id")
 private  LiveRoom liveRoom;

@Column
@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;

 private  int limitType;

@Column(name = "idL5CE")
 private String idL5CE;

@Transient
 private MobileUserRequest mobileuserrequest = new MobileUserRequestImpl();;


public Date getCreateTime(){
    return createTime;
}


public LiveRoom getLiveRoom(){
    return liveRoom;
}


public MobileUser getUser(){
  this.user = mobileuserrequest.getUser(this.idL5CE);
return this.user;
}}



public void setLiveRoom(LiveRoom liveRoom){
    this.liveRoom = liveRoom;
}


public int getLimitType(){
    return limitType;
}


public void setLimitType(int limitType){
    this.limitType = limitType;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setUser(MobileUser user){
this.idL5CE = user.getUser() ;
mobileuserrequest.setUser(user,this.idL5CE);
 this.user = user;
}



}