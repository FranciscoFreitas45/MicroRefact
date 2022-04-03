package cn.gson.oasys.DTO;
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
public class Taskuser {

 private  Long pkId;

 private  Tasklist taskId;

 private  User userId;

 private  Integer statusId;

public Taskuser() {
// TODO Auto-generated constructor stub
}
public Tasklist getTaskId(){
    return taskId;
}


public Integer getStatusId(){
    return statusId;
}


public void setPkId(Long pkId){
    this.pkId = pkId;
}


@Override
public String toString(){
    return "Taskuser [pkId=" + pkId + ", statusId=" + statusId + "]";
}


public User getUserId(){
    return userId;
}


public Long getPkId(){
    return pkId;
}


}