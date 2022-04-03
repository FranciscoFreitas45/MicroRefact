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


public Date getCreateTime(){
    return createTime;
}


public LiveRoom getLiveRoom(){
    return liveRoom;
}


public MobileUser getUser(){
    return user;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


}