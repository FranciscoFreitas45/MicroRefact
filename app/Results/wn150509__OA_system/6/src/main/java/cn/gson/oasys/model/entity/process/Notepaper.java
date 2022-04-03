package cn.gson.oasys.model.entity.process;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_notepaper")
public class Notepaper {

@Id
@Column(name = "notepaper_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long notepaperId;

 private  String title;

// 便签内容
@Column(columnDefinition = "text")
 private  String concent;

@Transient
 private  User userId;

@Column(name = "create_time")
 private  Date createTime;

@Column(name = "userIdv2")
 private Long userIdv2;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


public Date getCreateTime(){
    return createTime;
}


public String getTitle(){
    return title;
}


public void setConcent(String concent){
    this.concent = concent;
}


public void setNotepaperId(Long notepaperId){
    this.notepaperId = notepaperId;
}


public String getConcent(){
    return concent;
}


public Long getNotepaperId(){
    return notepaperId;
}


public void setTitle(String title){
    this.title = title;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


@Override
public String toString(){
    return "Notepaper [notepaperId=" + notepaperId + ", title=" + title + ", concent=" + concent + ", createTime=" + createTime + "]";
}


public User getUserId(){
  this.userId = userrequest.getUserId(this.userIdv2);
return this.userId;
}


public void setUserId(User userId){
 userrequest.setUserId(userId,this.userIdv2);
}



}