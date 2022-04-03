package edu.xr.campusweibo.domain;
 import org.springframework.data.annotation.CreatedDate;
import javax.persistence.Entity;
import javax.persistence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
@Entity
@Table(name = "w_weibo")
public class Weibo implements Serializable{

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

@CreatedDate
@Column(name = "create_date", nullable = false)
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


public void setCreateDate(LocalDateTime createDate){
    this.createDate = createDate;
}


@Override
public int hashCode(){
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (text != null ? text.hashCode() : 0);
    result = 31 * result + (uid != null ? uid.hashCode() : 0);
    result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
    return result;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    Weibo weibo = (Weibo) o;
    if (id != null ? !id.equals(weibo.id) : weibo.id != null)
        return false;
    if (text != null ? !text.equals(weibo.text) : weibo.text != null)
        return false;
    if (uid != null ? !uid.equals(weibo.uid) : weibo.uid != null)
        return false;
    return createDate != null ? createDate.equals(weibo.createDate) : weibo.createDate == null;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


@Override
public String toString(){
    return "Weibo{" + "id=" + id + ", text='" + text + '\'' + ", uid=" + uid + ", createDate=" + createDate + '}';
}


public void setUid(Long uid){
    this.uid = uid;
}


public void setText(String text){
    this.text = text;
}


}