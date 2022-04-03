package cn.gson.oasys.model.entity.schedule;
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
@Entity
@Table(name = "aoa_schedule_list")
public class ScheduleList {

@Id
@Column(name = "rc_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long rcId;

@Column(name = "type_id")
 private  Long typeId;

@Column(name = "status_id")
 private  Long statusId;

@Column(name = "start_time")
 private  Date startTime;

@Column(name = "end_time")
 private  Date endTime;

@Column(name = "create_time")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date createTime;

 private  String title;

@Column(name = "miaoshu")
 private  String describe;

@Column(name = "is_remind")
 private  Boolean isRemind;

@Transient
 private  String username;

 private  Boolean isreminded;

@Transient
 private  User user;

@ManyToMany
@JsonIgnore
@JoinTable(// 日程联系人关联表
name = "aoa_schedule_user", joinColumns = { @JoinColumn(name = "rcid") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
 private  List<User> users;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public ScheduleList() {
}
public Date getCreateTime(){
    return createTime;
}


public void setStatusId(Long statusId){
    this.statusId = statusId;
}


public User getUser(){
  this.user = userrequest.getUser(this.userId);
return this.user;
}


public void setIsreminded(Boolean isreminded){
    this.isreminded = isreminded;
}


public String getDescribe(){
    return describe;
}


public Long getTypeId(){
    return typeId;
}


public void setDescribe(String describe){
    this.describe = describe;
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


public void setUsers(List<User> users){
    this.users = users;
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


public void setStartTime(Date startTime){
    this.startTime = startTime;
}


public void setIsRemind(Boolean isRemind){
    this.isRemind = isRemind;
}


public void setUser(User user){
 userrequest.setUser(user,this.userId);
}



public void setRcId(Long rcId){
    this.rcId = rcId;
}


public void setUsername(String username){
    this.username = username;
}


public Long getRcId(){
    return rcId;
}


public void setTitle(String title){
    this.title = title;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setTypeId(Long typeId){
    this.typeId = typeId;
}


public Boolean getIsreminded(){
    return isreminded;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


@Override
public String toString(){
    return "ScheduleList [rcId=" + rcId + ", typeId=" + typeId + ", statusId=" + statusId + ", startTime=" + startTime + ", endTime=" + endTime + ", createTime=" + createTime + ", title=" + title + ", describe=" + describe + ", isRemind=" + isRemind + ", username=" + username + ", isreminded=" + isreminded + "]";
}


}