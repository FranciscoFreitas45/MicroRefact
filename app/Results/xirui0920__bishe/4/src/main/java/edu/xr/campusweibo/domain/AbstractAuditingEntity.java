package edu.xr.campusweibo.domain;
 import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
@Audited
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditingEntity implements Serializable{

 private  long serialVersionUID;

@CreatedBy
@Column(name = "created_by", nullable = false, length = 50, updatable = false)
@JsonIgnore
 private  String createdBy;

@CreatedDate
@Column(name = "created_date", nullable = false)
@JsonIgnore
 private  ZonedDateTime createdDate;

@LastModifiedBy
@Column(name = "last_modified_by", length = 50)
@JsonIgnore
 private  String lastModifiedBy;

@LastModifiedDate
@Column(name = "last_modified_date")
@JsonIgnore
 private  ZonedDateTime lastModifiedDate;


public void setLastModifiedDate(ZonedDateTime lastModifiedDate){
    this.lastModifiedDate = lastModifiedDate;
}


public ZonedDateTime getLastModifiedDate(){
    return lastModifiedDate;
}


public void setCreatedDate(ZonedDateTime createdDate){
    this.createdDate = createdDate;
}


public String getLastModifiedBy(){
    return lastModifiedBy;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public void setLastModifiedBy(String lastModifiedBy){
    this.lastModifiedBy = lastModifiedBy;
}


public ZonedDateTime getCreatedDate(){
    return createdDate;
}


public String getCreatedBy(){
    return createdBy;
}


}