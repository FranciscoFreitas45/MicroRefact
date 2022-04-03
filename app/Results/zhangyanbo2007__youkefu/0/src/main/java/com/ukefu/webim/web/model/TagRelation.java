package com.ukefu.webim.web.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
@Entity
@Table(name = "uk_tagrelation")
@Proxy(lazy = false)
public class TagRelation {

 private  long serialVersionUID;

 private  String id;

 private  String tagid;

 private  String userid;

 private  String dataid;


public String getTagid(){
    return tagid;
}


public String getDataid(){
    return dataid;
}


public void setId(String id){
    this.id = id;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getUserid(){
    return userid;
}


public void setTagid(String tagid){
    this.tagid = tagid;
}


public void setUserid(String userid){
    this.userid = userid;
}


}