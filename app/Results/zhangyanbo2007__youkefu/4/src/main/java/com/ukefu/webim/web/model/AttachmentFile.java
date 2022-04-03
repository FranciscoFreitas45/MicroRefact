package com.ukefu.webim.web.model;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
@Entity
@Table(name = "uk_attachment_file")
@Proxy(lazy = false)
public class AttachmentFile implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  String creater;

 private  String organ;

 private  Date createtime;

 private  Date updatetime;

 private  String title;

 private  String url;

 private  int filelength;

 private  boolean datastatus;

 private  String filetype;

 private  String fileid;

 private  String dataid;

 private  String modelid;

 private  String model;

 private  boolean image;


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
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


public void setDatastatus(boolean datastatus){
    this.datastatus = datastatus;
}


public void setFileid(String fileid){
    this.fileid = fileid;
}


public String getFileid(){
    return fileid;
}


public String getTitle(){
    return title;
}


public Date getCreatetime(){
    return createtime;
}


public void setId(String id){
    this.id = id;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getModelid(){
    return modelid;
}


public String getModel(){
    return model;
}


public void setModelid(String modelid){
    this.modelid = modelid;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setTitle(String title){
    this.title = title;
}


public void setUrl(String url){
    this.url = url;
}


public int getFilelength(){
    return filelength;
}


public String getUrl(){
    return url;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public void setFiletype(String filetype){
    this.filetype = filetype;
}


public boolean isImage(){
    return image;
}


public String getDataid(){
    return dataid;
}


public String getOrgan(){
    return organ;
}


public String getOrgi(){
    return orgi;
}


public void setFilelength(int filelength){
    this.filelength = filelength;
}


public void setModel(String model){
    this.model = model;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public boolean isDatastatus(){
    return datastatus;
}


public String getFiletype(){
    return filetype;
}


public void setImage(boolean image){
    this.image = image;
}


}