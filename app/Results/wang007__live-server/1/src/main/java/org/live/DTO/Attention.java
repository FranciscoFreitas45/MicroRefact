package org.live.DTO;
 import org.live.common.base.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import org.live.Request.MobileUserRequest;
import org.live.Request.Impl.MobileUserRequestImpl;
import org.live.DTO.MobileUser;
public class Attention extends BaseEntity{

 private  MobileUser user;

 private  LiveRoom liveRoom;

 private  Date createTime;

 private String id1YA2;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


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
this.id1YA2 = user.getUser() ;
mobileuserrequest.setUser(user,this.id1YA2);
 this.user = user;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setUser"))

.queryParam("user",user)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLiveRoom(LiveRoom liveRoom){
    this.liveRoom = liveRoom;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLiveRoom"))

.queryParam("liveRoom",liveRoom)
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


}