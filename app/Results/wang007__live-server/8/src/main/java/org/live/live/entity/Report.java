package org.live.live.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import java.util.Date;
import org.live.Request.MobileUserRequest;
import org.live.Request.Impl.MobileUserRequestImpl;
import org.live.DTO.MobileUser;
import org.live.Request.LiveRoomRequest;
import org.live.Request.Impl.LiveRoomRequestImpl;
import org.live.DTO.LiveRoom;
@Entity
@Table(name = "live_report")
public class Report extends BaseEntity{

@Column
 private  String reportNum;

@Transient
 private  MobileUser user;

@Transient
 private  LiveRoom liveRoom;

@Column
@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;

@Column
 private  boolean handleType;

@Column(name = "idYUDA")
 private String idYUDA;

@Transient
 private MobileUserRequest mobileuserrequest = new MobileUserRequestImpl();;

@Column(name = "idWHGX")
 private String idWHGX;

@Transient
 private LiveRoomRequest liveroomrequest = new LiveRoomRequestImpl();;


public String getReportNum(){
    return reportNum;
}


public void setReportNum(String reportNum){
    this.reportNum = reportNum;
}


public Date getCreateTime(){
    return createTime;
}


public LiveRoom getLiveRoom(){
  this.liveRoom = liveroomrequest.getLiveRoom(this.idWHGX);
return this.liveRoom;
}}



public MobileUser getUser(){
  this.user = mobileuserrequest.getUser(this.idYUDA);
return this.user;
}}



public void setLiveRoom(LiveRoom liveRoom){
this.idWHGX = liveRoom.getLiveroom() ;
liveroomrequest.setLiveRoom(liveRoom,this.idWHGX);
 this.liveRoom = liveRoom;
}



public void setHandleType(boolean handleType){
    this.handleType = handleType;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setUser(MobileUser user){
this.idYUDA = user.getUser() ;
mobileuserrequest.setUser(user,this.idYUDA);
 this.user = user;
}



public boolean isHandleType(){
    return handleType;
}


}