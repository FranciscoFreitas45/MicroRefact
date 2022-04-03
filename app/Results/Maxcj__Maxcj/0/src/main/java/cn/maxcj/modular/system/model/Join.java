package cn.maxcj.modular.system.model;
 import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
@TableName("sys_join")
public class Join extends Model<Join>{

 private  long serialVersionUID;

@TableId(value = "id", type = IdType.AUTO)
 private  Integer id;

@TableField("activity_id")
 private  Integer activityId;

 private  Integer userid;

@TableField("join_time")
 private  Date joinTime;

@TableField("join_state")
 private  Integer joinState;


public Date getJoinTime(){
    return joinTime;
}


public Integer getJoinState(){
    return joinState;
}


public void setJoinState(Integer joinState){
    this.joinState = joinState;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


@Override
public Serializable pkVal(){
    return this.id;
}


@Override
public String toString(){
    return "Join{" + ", id=" + id + ", activityId=" + activityId + ", userid=" + userid + ", joinTime=" + joinTime + ", joinState=" + joinState + "}";
}


public Integer getActivityId(){
    return activityId;
}


public void setActivityId(Integer activityId){
    this.activityId = activityId;
}


public void setJoinTime(Date joinTime){
    this.joinTime = joinTime;
}


public Integer getUserid(){
    return userid;
}


public void setUserid(Integer userid){
    this.userid = userid;
}


}