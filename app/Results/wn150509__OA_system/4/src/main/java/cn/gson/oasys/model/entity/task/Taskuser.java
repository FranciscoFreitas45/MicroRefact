package cn.gson.oasys.model.entity.task;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_task_user")
public class Taskuser {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pk_id")
 private  Long pkId;

@ManyToOne
@JoinColumn(name = "task_id")
 private  Tasklist taskId;

@Transient
 private  User userId;

@Column(name = "status_id")
 private  Integer statusId;

@Column(name = "userIdv2")
 private Long userIdv2;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public Taskuser() {
// TODO Auto-generated constructor stub
}
public Tasklist getTaskId(){
    return taskId;
}


public Integer getStatusId(){
    return statusId;
}


public void setStatusId(Integer statusId){
    this.statusId = statusId;
}


public void setPkId(Long pkId){
    this.pkId = pkId;
}


public void setTaskId(Tasklist taskId){
    this.taskId = taskId;
}


@Override
public String toString(){
    return "Taskuser [pkId=" + pkId + ", statusId=" + statusId + "]";
}


public User getUserId(){
  this.userId = userrequest.getUserId(this.userIdv2);
return this.userId;
}


public Long getPkId(){
    return pkId;
}


public void setUserId(User userId){
 userrequest.setUserId(userId,this.userIdv2);
}



}