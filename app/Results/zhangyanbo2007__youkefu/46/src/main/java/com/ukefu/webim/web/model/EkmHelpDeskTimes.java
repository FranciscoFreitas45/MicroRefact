package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_ekm_helpdesk_times")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmHelpDeskTimes {

 private  long serialVersionUID;

 private  String id;

 private  String knowid;

 private  String kbid;

 private  String ktid;

 private  boolean helps;

 private  int version;

 private  Date createtime;

 private  String creater;

 private  String orgi;


public int getVersion(){
    return version;
}


public void setHelps(boolean helps){
    this.helps = helps;
}


public void setVersion(int version){
    this.version = version;
}


public String getKbid(){
    return kbid;
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


public void setKtid(String ktid){
    this.ktid = ktid;
}


public String getKnowid(){
    return knowid;
}


public void setKnowid(String knowid){
    this.knowid = knowid;
}


public Date getCreatetime(){
    return createtime;
}


public void setKbid(String kbid){
    this.kbid = kbid;
}


public String getOrgi(){
    return orgi;
}


public void setId(String id){
    this.id = id;
}


public boolean isHelps(){
    return helps;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getKtid(){
    return ktid;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


}