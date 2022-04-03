package cn.maxcj.modular.system.model;
 import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
@TableName("sys_activity")
public class Activity extends Model<Activity>{

 private  long serialVersionUID;

@TableId(value = "activity_id", type = IdType.AUTO)
 private  Integer activityId;

@TableField("activity_category")
 private  Integer activityCategory;

@TableField("activity_name")
 private  String activityName;

@TableField("activity_place")
 private  String activityPlace;

@TableField("activity_start_time")
 private  Date activityStartTime;

@TableField("activity_end_time")
 private  Date activityEndTime;

@TableField("activity_club")
 private  String activityClub;

@TableField("activity_person")
 private  Integer activityPerson;

@TableField("activity_creat_time")
 private  Date activityCreatTime;

@TableField("activity_state")
 private  Integer activityState;

@TableField("activity_number")
 private  Integer activity_number;


public Integer getActivityCategory(){
    return activityCategory;
}


public String getActivityPlace(){
    return activityPlace;
}


public void setActivityCreatTime(Date activityCreatTime){
    this.activityCreatTime = activityCreatTime;
}


public void setActivity_number(Integer activity_number){
    this.activity_number = activity_number;
}


public void setActivityPlace(String activityPlace){
    this.activityPlace = activityPlace;
}


public void setActivityState(Integer activityState){
    this.activityState = activityState;
}


public void setActivityStartTime(Date activityStartTime){
    this.activityStartTime = activityStartTime;
}


public Integer getActivity_number(){
    return activity_number;
}


public void setActivityId(Integer activityId){
    this.activityId = activityId;
}


public void setActivityClub(String activityClub){
    this.activityClub = activityClub;
}


public Date getActivityEndTime(){
    return activityEndTime;
}


public void setActivityCategory(Integer activityCategory){
    this.activityCategory = activityCategory;
}


public void setActivityName(String activityName){
    this.activityName = activityName;
}


public String getActivityName(){
    return activityName;
}


public Date getActivityStartTime(){
    return activityStartTime;
}


public void setActivityEndTime(Date activityEndTime){
    this.activityEndTime = activityEndTime;
}


public Integer getActivityState(){
    return activityState;
}


@Override
public Serializable pkVal(){
    return this.activityId;
}


@Override
public String toString(){
    return "Activity{" + "activityId=" + activityId + ", activityCategory=" + activityCategory + ", activityName='" + activityName + '\'' + ", activityPlace='" + activityPlace + '\'' + ", activityStartTime=" + activityStartTime + ", activityEndTime=" + activityEndTime + ", activityClub='" + activityClub + '\'' + ", activityPerson=" + activityPerson + ", activityCreatTime=" + activityCreatTime + ", activityState=" + activityState + ", activity_number=" + activity_number + '}';
}


public String getActivityClub(){
    return activityClub;
}


public void setActivityPerson(Integer activityPerson){
    this.activityPerson = activityPerson;
}


public Integer getActivityId(){
    return activityId;
}


public Integer getActivityPerson(){
    return activityPerson;
}


public Date getActivityCreatTime(){
    return activityCreatTime;
}


}