package com.uec.imonitor.peopledaily.bean;
 import java.util.Date;
import javax.persistence;
@Entity
@Table(name = "news_peopledaily_image")
public class PeoplesDailyImgEntity {

// 指明这个属性映射为数据库的主键
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "innerid", nullable = false)
 private  Integer innerid;

@Column(name = "title")
 private  String title;

@Column(name = "description")
 private  String description;

@Column(name = "url")
 private  String url;

@Column(name = "webpage_code")
 private  String webpageCode;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_datetime")
 private  Date createDatetime;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "update_datetime")
 private  Date updateDatetime;

@Column(name = "status")
 private  Integer status;

@Column(name = "is_delete")
 private  Integer isDelete;


public Date getUpdateDatetime(){
    return updateDatetime;
}


public Integer getIsDelete(){
    return isDelete;
}


public void setWebpageCode(String webpageCode){
    this.webpageCode = webpageCode;
}


// 在对实体数据进行数据库更新操作之前，调用实体的 @PreUpdate 回调方法。这些数据库操作可以在更新实体状态时发生，也可以在将状态刷新到数据库（可能位于事务结尾）时发生。请注意：此回调是否在持久保存实体并随后在单个事务中修改该实体时发生，均依赖于实施。
@PrePersist
public void prePersist(){
    Date nowDate = new Date();
    this.createDatetime = nowDate;
    this.updateDatetime = nowDate;
}


public void setTitle(String title){
    this.title = title;
}


public void setDescription(String description){
    this.description = description;
}


public void setCreateDatetime(Date createDatetime){
    this.createDatetime = createDatetime;
}


public Integer getStatus(){
    return status;
}


public String getDescription(){
    return description;
}


public void setStatus(Integer status){
    this.status = status;
}


public void setUrl(String url){
    this.url = url;
}


public void setInnerid(Integer innerid){
    this.innerid = innerid;
}


public String getUrl(){
    return url;
}


public void setIsDelete(Integer isDelete){
    this.isDelete = isDelete;
}


public String getTitle(){
    return title;
}


public Date getCreateDatetime(){
    return createDatetime;
}


public void setUpdateDatetime(Date updateDatetime){
    this.updateDatetime = updateDatetime;
}


public Integer getInnerid(){
    return innerid;
}


// 在对实体数据进行数据库更新操作之前。
@PreUpdate
public void preUpdate(){
    this.updateDatetime = new Date();
}


public String getWebpageCode(){
    return webpageCode;
}


}