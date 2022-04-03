package cn.gson.oasys.model.entity.note;
 import java.util.Date;
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
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_director_users")
public class DirectorUser {

@Id
@Column(name = "director_users_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long directorUserId;

@ManyToOne
@JoinColumn(name = "director_id")
 private  Director director;

@Transient
 private  User user;

@Transient
 private  User shareuser;

@Column(name = "catelog_name")
 private  String catalogName;

 private  Date sharetime;

@Column(name = "is_handle")
 private  Boolean handle;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public DirectorUser() {
    super();
// TODO Auto-generated constructor stub
}public DirectorUser(User user, String catalogName) {
    this.user = user;
    this.catalogName = catalogName;
}
public User getShareuser(){
  this.shareuser = userrequest.getShareuser(this.userId);
return this.shareuser;
}


public String getCatalogName(){
    return catalogName;
}


public void setHandle(Boolean handle){
    this.handle = handle;
}


public Date getSharetime(){
    return sharetime;
}


public Director getDirector(){
    return director;
}


public User getUser(){
  this.shareuser = userrequest.getUser(this.userId);
return this.shareuser;
}


public Boolean getHandle(){
    return handle;
}


public void setSharetime(Date sharetime){
    this.sharetime = sharetime;
}


public void setShareuser(User shareuser){
 userrequest.setShareuser(shareuser,this.userId);
}



public void setDirectorUserId(Long directorUserId){
    this.directorUserId = directorUserId;
}


public void setDirector(Director director){
    this.director = director;
}


public void setCatalogName(String catalogName){
    this.catalogName = catalogName;
}


public Long getDirectorUserId(){
    return directorUserId;
}


@Override
public String toString(){
    return "DirectorUser [directorUserId=" + directorUserId + ", catalogName=" + catalogName + ", handle=" + handle + ",sharetime" + sharetime + "]";
}


public void setUser(User user){
 userrequest.setUser(user,this.userId);
}



}