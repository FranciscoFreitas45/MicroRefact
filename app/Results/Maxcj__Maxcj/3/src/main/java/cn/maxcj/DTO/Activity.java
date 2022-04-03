package cn.maxcj.DTO;
 import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
public class Activity extends Model<Activity>{

 private  long serialVersionUID;

 private  Integer activityId;

 private  Integer activityCategory;

 private  String activityName;

 private  String activityPlace;

 private  Date activityStartTime;

 private  Date activityEndTime;

 private  String activityClub;

 private  Integer activityPerson;

 private  Date activityCreatTime;

 private  Integer activityState;

 private  Integer activity_number;


public Integer getActivityCategory(){
    return activityCategory;
}


public String getActivityPlace(){
    return activityPlace;
}


public Integer getActivity_number(){
    return activity_number;
}


public Date getActivityEndTime(){
    return activityEndTime;
}


public String getActivityName(){
    return activityName;
}


public Date getActivityStartTime(){
    return activityStartTime;
}


public Integer getActivityState(){
    return activityState;
}


public String getActivityClub(){
    return activityClub;
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