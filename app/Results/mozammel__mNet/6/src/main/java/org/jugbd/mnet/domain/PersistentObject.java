package org.jugbd.mnet.domain;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class PersistentObject implements Serializable{

@CreatedDate
@NotNull
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime createdDate;

@LastModifiedDate
@NotNull
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime lastModifiedDate;

@CreatedBy
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn
 private  User createdBy;

@LastModifiedBy
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn
 private  User lastModifiedBy;


public void setLastModifiedDate(DateTime lastModifiedDate){
    this.lastModifiedDate = lastModifiedDate;
}


public DateTime getLastModifiedDate(){
    return lastModifiedDate;
}


public void setCreatedDate(DateTime createdDate){
    this.createdDate = createdDate;
}


public User getLastModifiedBy(){
    return lastModifiedBy;
}


public void setCreatedBy(User createdBy){
    this.createdBy = createdBy;
}


public void setLastModifiedBy(User lastModifiedBy){
    this.lastModifiedBy = lastModifiedBy;
}


public DateTime getCreatedDate(){
    return createdDate;
}


public User getCreatedBy(){
    return createdBy;
}


}