package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_xiaoe_topic_item")
@org.hibernate.annotations.Proxy(lazy = false)
public class TopicItem {

 private  long serialVersionUID;

 private  String id;

 private  String topicid;

 private  String title;

 private  Date createtime;

 private  String creater;

 private  String orgi;


public String getTopicid(){
    return topicid;
}


public String getTitle(){
    return title;
}


public Date getCreatetime(){
    return createtime;
}


public String getOrgi(){
    return orgi;
}


public void setId(String id){
    this.id = id;
}


public void setTitle(String title){
    this.title = title;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public void setTopicid(String topicid){
    this.topicid = topicid;
}


}