package cn.gson.oasys.model.entity.task;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import com.alibaba.fastjson.annotation.JSONField;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_task_list")
public class Tasklist implements Serializable{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "task_id")
 private  Long taskId;

@Column(name = "type_id")
 private  Long typeId;

@Column(name = "publish_time")
@JSONField(format = "yyyy-MM-dd hh:mm:ss")
 private  Date publishTime;

@Column(name = "star_time")
@JSONField(format = "yyyy-MM-dd hh:mm:ss")
 private  Date starTime;

@Column(name = "end_time")
@JSONField(format = "yyyy-MM-dd hh:mm:ss")
 private  Date endTime;

@Column(name = "modify_time")
@JSONField(format = "yyyy-MM-dd hh:mm:ss")
 private  Date modifyTime;

@Column(name = "title", nullable = false)
@NotEmpty(message = "主题名字不能为空")
 private  String title;

@Column(name = "reciverlist")
@NotEmpty(message = "接收人不能为空")
 private  String reciverlist;

@Transient
 private  User usersId;

@Column(name = "task_describe")
 private  String taskDescribe;

@Column(name = "comment")
 private  String comment;

@Column(name = "is_top")
 private  Boolean top;

@Column(name = "is_cancel")
 private  Boolean cancel;

@Column(name = "ticking")
 private  String ticking;

@Column(name = "status_id")
 private  Integer statusId;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public Tasklist() {
}public Tasklist(Long typeId, Date starTime, Date endTime, String title, String reciverlist, String taskDescribe, String comment, Boolean top, Boolean cancel, Integer statusId) {
    super();
    this.typeId = typeId;
    this.starTime = starTime;
    this.endTime = endTime;
    this.title = title;
    this.reciverlist = reciverlist;
    this.taskDescribe = taskDescribe;
    this.comment = comment;
    this.top = top;
    this.cancel = cancel;
    this.statusId = statusId;
}public Tasklist(String comment, String taskDescribe, String title, Date starTime, Date endTime, Long typeId, Integer statusId, Boolean Cancel, Boolean Top, Date publishTime, Long userid) {
}
public void setTicking(String ticking){
    this.ticking = ticking;
}


public void setStarTime(Date starTime){
    this.starTime = starTime;
}


public void setStatusId(Integer statusId){
    this.statusId = statusId;
}


public User getUsersId(){
  this.usersId = userrequest.getUsersId(this.userId);
return this.usersId;
}


public void setTaskId(Long taskId){
    this.taskId = taskId;
}


public Long getTypeId(){
    return typeId;
}


public Integer getStatusId(){
    return statusId;
}


public void setTop(Boolean top){
    this.top = top;
}


public Date getEndTime(){
    return endTime;
}


public String getTitle(){
    return title;
}


public Boolean getTop(){
    return top;
}


public void setReciverlist(String reciverlist){
    this.reciverlist = reciverlist;
}


public String getTaskDescribe(){
    return taskDescribe;
}


public String getComment(){
    return comment;
}


public String getTicking(){
    return ticking;
}


public Long getTaskId(){
    return taskId;
}


public Boolean getCancel(){
    return cancel;
}


public void setPublishTime(Date publishTime){
    this.publishTime = publishTime;
}


public Date getModifyTime(){
    return modifyTime;
}


public void setTitle(String title){
    this.title = title;
}


public void setTypeId(Long typeId){
    this.typeId = typeId;
}


public void setModifyTime(Date modifyTime){
    this.modifyTime = modifyTime;
}


public Date getPublishTime(){
    return publishTime;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public void setTaskDescribe(String taskDescribe){
    this.taskDescribe = taskDescribe;
}


public void setUsersId(User usersId){
 userrequest.setUsersId(usersId,this.userId);
}



public void setComment(String comment){
    this.comment = comment;
}


public String getReciverlist(){
    return reciverlist;
}


public void setCancel(Boolean cancel){
    this.cancel = cancel;
}


@Override
public String toString(){
    return "Tasklist [taskId=" + taskId + ", typeId=" + typeId + ", publishTime=" + publishTime + ", starTime=" + starTime + ", endTime=" + endTime + ", modifyTime=" + modifyTime + ", title=" + title + ", taskDescribe=" + taskDescribe + ", comment=" + comment + ", cancel=" + cancel + ", top=" + top + ", ticking=" + ticking + ", statusId=" + statusId + "]";
}


public Date getStarTime(){
    return starTime;
}


}