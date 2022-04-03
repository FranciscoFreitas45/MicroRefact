package edu.xr.campusweibo.domain;
 import javax.persistence.Entity;
import javax.persistence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Entity
@Table(name = "w_friend")
public class Friend implements Serializable{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@NotNull
@Column(nullable = false)
 private  Long uid;

@NotNull
@Column(nullable = false)
 private  Long fuid;


public Long getUid(){
    return uid;
}


public void setFuid(Long fuid){
    this.fuid = fuid;
}


@Override
public int hashCode(){
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (uid != null ? uid.hashCode() : 0);
    result = 31 * result + (fuid != null ? fuid.hashCode() : 0);
    return result;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    Friend friend = (Friend) o;
    if (id != null ? !id.equals(friend.id) : friend.id != null)
        return false;
    if (uid != null ? !uid.equals(friend.uid) : friend.uid != null)
        return false;
    return fuid != null ? fuid.equals(friend.fuid) : friend.fuid == null;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


@Override
public String toString(){
    return "Friend{" + "id=" + id + ", uid=" + uid + ", fuid=" + fuid + '}';
}


public void setUid(Long uid){
    this.uid = uid;
}


public Long getFuid(){
    return fuid;
}


}