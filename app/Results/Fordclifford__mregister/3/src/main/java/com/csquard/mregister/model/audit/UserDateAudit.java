package com.csquard.mregister.model.audit;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
@JsonIgnoreProperties(value = { "createdBy", "updatedBy" }, allowGetters = true)
public class UserDateAudit extends DateAudit{

@CreatedBy
@Column(updatable = false)
 private  Long createdBy;

@LastModifiedBy
 private  Long updatedBy;


public void setUpdatedBy(Long updatedBy){
    this.updatedBy = updatedBy;
}


public Long getUpdatedBy(){
    return updatedBy;
}


public void setCreatedBy(Long createdBy){
    this.createdBy = createdBy;
}


public Long getCreatedBy(){
    return createdBy;
}


}