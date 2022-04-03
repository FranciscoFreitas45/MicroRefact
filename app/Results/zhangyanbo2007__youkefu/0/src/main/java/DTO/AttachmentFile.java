package DTO;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
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


public String getCreater(){
    return creater;
}


public String getModelid(){
    return modelid;
}


public String getModel(){
    return model;
}


public Date getUpdatetime(){
    return updatetime;
}


public int getFilelength(){
    return filelength;
}


public String getUrl(){
    return url;
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


public String getFiletype(){
    return filetype;
}


public void setCreater(String creater){
    this.creater = creater;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreater"))

.queryParam("creater",creater)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOrgi(String orgi){
    this.orgi = orgi;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrgi"))

.queryParam("orgi",orgi)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOrgan(String organ){
    this.organ = organ;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrgan"))

.queryParam("organ",organ)
;
restTemplate.put(builder.toUriString(),null);
}


public void setModel(String model){
    this.model = model;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setModel"))

.queryParam("model",model)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFilelength(int filelength){
    this.filelength = filelength;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFilelength"))

.queryParam("filelength",filelength)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFiletype(String filetype){
    this.filetype = filetype;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFiletype"))

.queryParam("filetype",filetype)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTitle(String title){
    this.title = title;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTitle"))

.queryParam("title",title)
;
restTemplate.put(builder.toUriString(),null);
}


public void setImage(boolean image){
    this.image = image;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setImage"))

.queryParam("image",image)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFileid(String fileid){
    this.fileid = fileid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFileid"))

.queryParam("fileid",fileid)
;
restTemplate.put(builder.toUriString(),null);
}


}