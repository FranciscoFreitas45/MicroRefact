package org.live.live.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import org.live.Request.MobileUserRequest;
import org.live.Request.Impl.MobileUserRequestImpl;
import org.live.DTO.MobileUser;
@Entity
@Table(name = "live_attention")
public class Attention extends BaseEntity{

@Transient
 private  MobileUser user;

@OneToOne
@JoinColumn(name = "live_room_id")
 private  LiveRoom liveRoom;

 private  Date createTime;

@Column(name = "id1YA2")
 private String id1YA2;

@Transient
 private MobileUserRequest mobileuserrequest = new MobileUserRequestImpl();;


public Date getCreateTime(){
    return createTime;
}


public LiveRoom getLiveRoom(){
    return liveRoom;
}


public MobileUser getUser(){
  this.user = mobileuserrequest.getUser(this.id1YA2);
return this.user;
}}



public void setLiveRoom(LiveRoom liveRoom){
    this.liveRoom = liveRoom;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setUser(MobileUser user){
this.id1YA2 = user.getUser() ;
mobileuserrequest.setUser(user,this.id1YA2);
 this.user = user;
}



}