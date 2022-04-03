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
public class Mailreciver {

 private  Long pkId;

 private  Inmaillist mailId;

 private  User reciverId;

 private  Boolean read;

 private  Boolean star;

 private  Boolean del;

public Mailreciver() {
}
public void setStar(Boolean star){
    this.star = star;
}


public Boolean getRead(){
    return read;
}


public Boolean getStar(){
    return star;
}


public User getReciverId(){
    return reciverId;
}


public Long getPkId(){
    return pkId;
}


public void setReciverId(User reciverId){
    this.reciverId = reciverId;
}


public Inmaillist getMailId(){
    return mailId;
}


@Override
public String toString(){
    return "Mailreciver [pkId=" + pkId + ", mailId=" + mailId + ", read=" + read + ", star=" + star + ", del=" + del + "]";
}


public Boolean getDel(){
    return del;
}


}