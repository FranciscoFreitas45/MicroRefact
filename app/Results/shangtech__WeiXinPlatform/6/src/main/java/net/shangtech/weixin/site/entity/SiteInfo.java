package net.shangtech.weixin.site.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "site_info")
public class SiteInfo extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "title")
 private  String title;

@Column(name = "image")
 private  String image;

@Column(name = "create_time")
 private  Date createTime;

@Column(name = "sys_user_id")
 private  Integer sysUserId;

@Column(name = "info_content")
 private  String content;


public Date getCreateTime(){
    return createTime;
}


public void setContent(String content){
    this.content = content;
}


public String getTitle(){
    return title;
}


public String getContent(){
    return content;
}


public void setSysUserId(Integer sysUserId){
    this.sysUserId = sysUserId;
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


public String getImage(){
    return image;
}


public void setImage(String image){
    this.image = image;
}


}