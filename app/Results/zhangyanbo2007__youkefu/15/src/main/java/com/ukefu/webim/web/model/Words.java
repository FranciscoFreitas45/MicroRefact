package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_xiaoe_words")
@org.hibernate.annotations.Proxy(lazy = false)
public class Words {

 private  long serialVersionUID;

 private  String id;

 private  String keyword;

 private  String superordinate;

 private  String partofspeech;

 private  String content;

 private  String cate;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

public Words() {
}public Words(String keyword, String superordinate, String content) {
    this.keyword = keyword;
    this.superordinate = superordinate;
    this.content = content;
}
public void setContent(String content){
    this.content = content;
}


public String getContent(){
    return content;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getSuperordinate(){
    return superordinate;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public void setSuperordinate(String superordinate){
    this.superordinate = superordinate;
}


public void setPartofspeech(String partofspeech){
    this.partofspeech = partofspeech;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public String getKeyword(){
    return keyword;
}


public Date getCreatetime(){
    return createtime;
}


public void setKeyword(String keyword){
    this.keyword = keyword;
}


public String getOrgi(){
    return orgi;
}


public String getCate(){
    return cate;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setId(String id){
    this.id = id;
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


public String getPartofspeech(){
    return partofspeech;
}


}