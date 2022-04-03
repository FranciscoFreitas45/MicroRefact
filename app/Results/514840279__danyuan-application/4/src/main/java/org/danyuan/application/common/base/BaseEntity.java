package org.danyuan.application.common.base;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

@Id
@Column(unique = true, nullable = false)
 protected  String uuid;

@Column(columnDefinition = "varchar(200) COMMENT '资源功能描述'")
 protected  String discription;

@Column(name = "create_time", updatable = false)
@Temporal(TemporalType.TIMESTAMP)
@CreatedDate
@org.hibernate.annotations.CreationTimestamp
@DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
 protected  Date createTime;

@CreatedBy
@Column(name = "create_user", updatable = false)
 protected  String createUser;

// 这里应用数据库更行策略 ON UPDATE CURRENT_TIMESTAMP 所以无需jpa插座
@Column(name = "update_time", updatable = false, insertable = false)
@Temporal(TemporalType.TIMESTAMP)
@org.hibernate.annotations.UpdateTimestamp
@LastModifiedDate
@DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
 protected  Date updateTime;

@LastModifiedBy
@Column(name = "update_user")
 protected  String updateUser;

@Column(name = "delete_flag")
 protected  Integer deleteFlag;

public BaseEntity() {
    super();
}public BaseEntity(String uuid) {
    super();
    this.uuid = uuid;
}
public String getCreateUser(){
    return createUser;
}


public void setDiscription(String discription){
    this.discription = discription;
}


public Date getCreateTime(){
    return createTime;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public Integer getDeleteFlag(){
    return deleteFlag;
}


public void setCreateUser(String createUser){
    this.createUser = createUser;
}


public void setUuid(String uuid){
    this.uuid = uuid;
}


public Date getUpdateTime(){
    return updateTime;
}


public String getDiscription(){
    return discription;
}


public void setUpdateUser(String updateUser){
    this.updateUser = updateUser;
}


public void setDeleteFlag(Integer deleteFlag){
    this.deleteFlag = deleteFlag;
}


public String getUuid(){
    return uuid;
}


public String getUpdateUser(){
    return updateUser;
}


public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
}


}