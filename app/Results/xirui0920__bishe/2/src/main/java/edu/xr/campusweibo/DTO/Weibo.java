package edu.xr.campusweibo.DTO;
 import org.springframework.data.annotation.CreatedDate;
import javax.persistence.Entity;
import javax.persistence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
public class Weibo implements Serializable{

 private  Long id;

 private  String text;

 private  Long uid;

 private  LocalDateTime createDate;


public Long getUid(){
    return uid;
}


public LocalDateTime getCreateDate(){
    return createDate;
}


public String getText(){
    return text;
}


@Override
public int hashCode(){
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (text != null ? text.hashCode() : 0);
    result = 31 * result + (uid != null ? uid.hashCode() : 0);
    result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
    return result;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public void setUid(Long uid){
    this.uid = uid;
}


}