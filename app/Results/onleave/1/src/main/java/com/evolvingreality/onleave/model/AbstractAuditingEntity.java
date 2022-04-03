package com.evolvingreality.onleave.model;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
@MappedSuperclass
@Audited
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditingEntity {

@CreatedBy
@NotNull
@Column(name = "created_by", nullable = false, length = 50, updatable = false)
@JsonIgnore
 private  String createdBy;

@CreatedDate
@NotNull
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
@Column(name = "created_date", nullable = false)
@JsonIgnore
 private  DateTime createdDate;

@LastModifiedBy
@Column(name = "last_modified_by", length = 50)
@JsonIgnore
 private  String lastModifiedBy;

@LastModifiedDate
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
@Column(name = "last_modified_date")
@JsonIgnore
 private  DateTime lastModifiedDate;


public void setLastModifiedDate(DateTime lastModifiedDate){
    this.lastModifiedDate = lastModifiedDate;
}


public DateTime getLastModifiedDate(){
    return lastModifiedDate;
}


public void setCreatedDate(DateTime createdDate){
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


public DateTime getCreatedDate(){
    return createdDate;
}


public String getCreatedBy(){
    return createdBy;
}


}