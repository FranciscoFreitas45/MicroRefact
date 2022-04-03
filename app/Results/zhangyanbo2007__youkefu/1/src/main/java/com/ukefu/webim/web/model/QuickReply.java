package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import com.ukefu.util.UKTools;
@Document(indexName = "uckefu", type = "uk_quickreply")
@Entity
@Table(name = "uk_quickreply")
@org.hibernate.annotations.Proxy(lazy = false)
public class QuickReply {

 private  String id;

 private  String title;

 private  String content;

 private  String type;

 private  String creater;

 private  Date createtime;

 private  String orgi;

 private  String cate;


public void setContent(String content){
    this.content = content;
}


public String getContent(){
    return content;
}


public void setTitle(String title){
    this.title = title;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public void setType(String type){
    this.type = type;
}


public String getTitle(){
    return title;
}


public Date getCreatetime(){
    return createtime;
}


public String getType(){
    return type;
}


public String getCate(){
    return cate;
}


public String getOrgi(){
    return orgi;
}


public void setId(String id){
    this.id = id;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setCate(String cate){
    this.cate = cate;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


}