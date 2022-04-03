package edu.xr.campusweibo.domain;
 import org.springframework.data.annotation.CreatedDate;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
@Entity
@Table(name = "w_reply")
public class MyReply implements Serializable{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@NotNull
@Size(min = 1, max = 256)
@Column(length = 256, nullable = false)
 private  String text;

@NotNull
@Column(nullable = false)
 private  Long uid;

@NotNull
@Column(nullable = false)
 private  Long wid;

@CreatedDate
@Column(name = "create_date", nullable = false)
 private  ZonedDateTime createDate;


public String getText(){
    return text;
}


public Long getId(){
    return id;
}


public void setUid(Long uid){
    this.uid = uid;
}


public Long getUid(){
    return uid;
}


public void setWid(Long wid){
    this.wid = wid;
}


public Long getWid(){
    return wid;
}


public ZonedDateTime getCreateDate(){
    return createDate;
}


public void setCreateDate(ZonedDateTime createDate){
    this.createDate = createDate;
}


@Override
public int hashCode(){
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (text != null ? text.hashCode() : 0);
    result = 31 * result + (uid != null ? uid.hashCode() : 0);
    result = 31 * result + (wid != null ? wid.hashCode() : 0);
    result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
    return result;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    MyReply reply = (MyReply) o;
    if (id != null ? !id.equals(reply.id) : reply.id != null)
        return false;
    if (text != null ? !text.equals(reply.text) : reply.text != null)
        return false;
    if (uid != null ? !uid.equals(reply.uid) : reply.uid != null)
        return false;
    if (wid != null ? !wid.equals(reply.wid) : reply.wid != null)
        return false;
    return createDate != null ? createDate.equals(reply.createDate) : reply.createDate == null;
}


public void setId(Long id){
    this.id = id;
}


@Override
public String toString(){
    return "Reply{" + "id=" + id + ", text='" + text + '\'' + ", uid=" + uid + ", wid=" + wid + ", createDate=" + createDate + '}';
}


public void setText(String text){
    this.text = text;
}


}