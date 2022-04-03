package net.shangtech.weixin.weixin.entity;
 import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "wx_message")
public class WxMessage extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "msg_title")
 private  String title;

@Column(name = "summary")
 private  String summary;

@Column(name = "image")
 private  String image;

@Column(name = "msg_content")
 private  String content;

@Column(name = "msg_url")
 private  String url;

@Column(name = "create_time")
 private  Date createTime;

@Column(name = "sys_user_id")
 private  Integer sysUserId;

@Column(name = "main_id")
 private  Integer mainId;

@Transient
 private  List<WxMessage> subMessages;


public void setSubMessages(List<WxMessage> subMessages){
    this.subMessages = subMessages;
}


public void setSummary(String summary){
    this.summary = summary;
}


public void setContent(String content){
    this.content = content;
}


public Date getCreateTime(){
    return createTime;
}


public void setMainId(Integer mainId){
    this.mainId = mainId;
}


public String getSummary(){
    return summary;
}


public String getContent(){
    return content;
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


public void setUrl(String url){
    this.url = url;
}


public String getUrl(){
    return url;
}


public String getTitle(){
    return title;
}


public List<WxMessage> getSubMessages(){
    return subMessages;
}


public Integer getMainId(){
    return mainId;
}


public void setSysUserId(Integer sysUserId){
    this.sysUserId = sysUserId;
}


public String getImage(){
    return image;
}


public void setImage(String image){
    this.image = image;
}


}