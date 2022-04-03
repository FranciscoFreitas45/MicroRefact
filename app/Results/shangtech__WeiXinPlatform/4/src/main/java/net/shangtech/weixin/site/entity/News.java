package net.shangtech.weixin.site.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "site_news")
public class News extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "type_id")
 private  Integer type;

@Column(name = "title")
 private  String title;

@Column(name = "image")
 private  String image;

@Column(name = "is_publish")
 private  Boolean isPublish;

@Column(name = "create_time")
 private  Date createTime;

@Column(name = "sort")
 private  Integer sort;

@Column(name = "news_content")
 private  String content;

@Column(name = "sys_user_id")
 private  Integer sysUserId;

@Transient
 private  NewsType newsType;


public Date getCreateTime(){
    return createTime;
}


public void setContent(String content){
    this.content = content;
}


public String getContent(){
    return content;
}


public void setIsPublish(Boolean isPublish){
    this.isPublish = isPublish;
}


public void setTitle(String title){
    this.title = title;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public Integer getSysUserId(){
    return sysUserId;
}


public void setType(Integer type){
    this.type = type;
}


public void setSort(Integer sort){
    this.sort = sort;
}


public Integer getSort(){
    return sort;
}


public String getTitle(){
    return title;
}


public Integer getType(){
    return type;
}


public Boolean getIsPublish(){
    return isPublish;
}


public void setSysUserId(Integer sysUserId){
    this.sysUserId = sysUserId;
}


public void setNewsType(NewsType newsType){
    this.newsType = newsType;
}


public String getImage(){
    return image;
}


public NewsType getNewsType(){
    return newsType;
}


public void setImage(String image){
    this.image = image;
}


}