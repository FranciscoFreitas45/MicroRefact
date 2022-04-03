package com.zammc.domain.activity;
 import javax.persistence;
import java.sql.Timestamp;
@Entity
@Table(name = "activity")
public class ActivityEntity {

 private  long activityId;

 private  String activityName;

 private  Timestamp createTime;

 private  Timestamp updateTime;

 private  byte status;

 private  byte dataStatus;

public ActivityEntity() {
}
@Basic
@Column(name = "create_time")
public Timestamp getCreateTime(){
    return createTime;
}


@Basic
@Column(name = "data_status")
public byte getDataStatus(){
    return dataStatus;
}


public void setDataStatus(byte dataStatus){
    this.dataStatus = dataStatus;
}


public void setCreateTime(Timestamp createTime){
    this.createTime = createTime;
}


public void setActivityId(long activityId){
    this.activityId = activityId;
}


@Basic
@Column(name = "status")
public byte getStatus(){
    return status;
}


public void setStatus(byte status){
    this.status = status;
}


@Basic
@Column(name = "update_time")
public Timestamp getUpdateTime(){
    return updateTime;
}


public void setActivityName(String activityName){
    this.activityName = activityName;
}


@Basic
@Column(name = "activity_name")
public String getActivityName(){
    return activityName;
}


public void setUpdateTime(Timestamp updateTime){
    this.updateTime = updateTime;
}


@Override
public String toString(){
    return "ActivityEntity{" + "activityId=" + activityId + ", activityName='" + activityName + '\'' + ", createTime=" + createTime + ", updateTime=" + updateTime + ", status=" + status + ", dataStatus=" + dataStatus + '}';
}


@Id
@Column(name = "activity_id")
public long getActivityId(){
    return activityId;
}


}