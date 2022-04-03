package org.live.live.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "live_live_record")
public class LiveRecord extends BaseEntity{

@Column
 private  String recordNum;

@ManyToOne
@JoinColumn(name = "live_room_id")
 private  LiveRoom liveRoom;

@Column
 private  int maxOnlineCount;

@Column
@Temporal(TemporalType.TIMESTAMP)
 private  Date startTime;

@Column
@Temporal(TemporalType.TIMESTAMP)
 private  Date endTime;


public Date getEndTime(){
    return endTime;
}


public LiveRoom getLiveRoom(){
    return liveRoom;
}


public void setMaxOnlineCount(int maxOnlineCount){
    this.maxOnlineCount = maxOnlineCount;
}


public int getMaxOnlineCount(){
    return maxOnlineCount;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public void setLiveRoom(LiveRoom liveRoom){
    this.liveRoom = liveRoom;
}


public String getRecordNum(){
    return recordNum;
}


public void setRecordNum(String recordNum){
    this.recordNum = recordNum;
}


public Date getStartTime(){
    return startTime;
}


public void setStartTime(Date startTime){
    this.startTime = startTime;
}


}