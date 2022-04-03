package cn.gson.oasys.DTO;
 import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
public class ScheduleList {

 private  Long rcId;

 private  Long typeId;

 private  Long statusId;

 private  Date startTime;

 private  Date endTime;

 private  Date createTime;

 private  String title;

 private  String describe;

 private  Boolean isRemind;

 private  String username;

 private  Boolean isreminded;

 private  User user;

 private  List<User> users;

public ScheduleList() {
}
public Date getCreateTime(){
    return createTime;
}


public User getUser(){
    return user;
}


public String getDescribe(){
    return describe;
}


public Long getTypeId(){
    return typeId;
}


public String getUsername(){
    return username;
}


public Long getStatusId(){
    return statusId;
}


public Date getEndTime(){
    return endTime;
}


public String getTitle(){
    return title;
}


public List<User> getUsers(){
    return users;
}


public Boolean getIsRemind(){
    return isRemind;
}


public Date getStartTime(){
    return startTime;
}


public void setIsRemind(Boolean isRemind){
    this.isRemind = isRemind;
}


public void setRcId(Long rcId){
    this.rcId = rcId;
}


public Long getRcId(){
    return rcId;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public Boolean getIsreminded(){
    return isreminded;
}


@Override
public String toString(){
    return "ScheduleList [rcId=" + rcId + ", typeId=" + typeId + ", statusId=" + statusId + ", startTime=" + startTime + ", endTime=" + endTime + ", createTime=" + createTime + ", title=" + title + ", describe=" + describe + ", isRemind=" + isRemind + ", username=" + username + ", isreminded=" + isreminded + "]";
}


}