package org.live.DTO;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import java.util.Date;
import org.live.Request.MobileUserRequest;
import org.live.Request.Impl.MobileUserRequestImpl;
import org.live.DTO.MobileUser;
import org.live.Request.LiveRoomRequest;
import org.live.Request.Impl.LiveRoomRequestImpl;
import org.live.DTO.LiveRoom;
public class Report extends BaseEntity{

 private  String reportNum;

 private  MobileUser user;

 private  LiveRoom liveRoom;

 private  Date createTime;

 private  boolean handleType;

 private String idYUDA;

 private String idWHGX;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public String getReportNum(){
    return reportNum;
}


public Date getCreateTime(){
    return createTime;
}


public LiveRoom getLiveRoom(){
    return liveRoom;
}


public MobileUser getUser(){
    return user;
}


public void setUser(MobileUser user){
this.idYUDA = user.getUser() ;
mobileuserrequest.setUser(user,this.idYUDA);
 this.user = user;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setUser"))

.queryParam("user",user)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLiveRoom(LiveRoom liveRoom){
this.idWHGX = liveRoom.getLiveroom() ;
liveroomrequest.setLiveRoom(liveRoom,this.idWHGX);
 this.liveRoom = liveRoom;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLiveRoom"))

.queryParam("liveRoom",liveRoom)
;
restTemplate.put(builder.toUriString(),null);
}


public void setReportNum(String reportNum){
    this.reportNum = reportNum;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setReportNum"))

.queryParam("reportNum",reportNum)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCreateTime"))

.queryParam("createTime",createTime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setHandleType(boolean handleType){
    this.handleType = handleType;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setHandleType"))

.queryParam("handleType",handleType)
;
restTemplate.put(builder.toUriString(),null);
}


}