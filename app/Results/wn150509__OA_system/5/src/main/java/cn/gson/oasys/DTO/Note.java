package cn.gson.oasys.DTO;
 import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import cn.gson.oasys.model.entity.user.User;
public class Note {

 private  Long noteId;

 private  String title;

 private  String content;

 private  Long catalogId;

 private  Long typeId;

 private  Long statusId;

 private  Long attachId;

 private  Date createTime;

 private  Long isCollected;

 private  Long createmanId;

 private  String receiver;

 private  Set<User> userss;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://12";

public Note() {
}public Note(String title, String content, Long catalogId, Long typeId, Long statusId, Long attachId, Date createTime, Long isCollected) {
    super();
    this.title = title;
    this.content = content;
    this.catalogId = catalogId;
    this.typeId = typeId;
    this.statusId = statusId;
    this.attachId = attachId;
    this.createTime = createTime;
    this.isCollected = isCollected;
}
public Long getAttachId(){
    return attachId;
}


public void setContent(String content){
    this.content = content;
}


public Date getCreateTime(){
    return createTime;
}


public String getReceiver(){
    return receiver;
}


public Long getCreatemanId(){
    return createmanId;
}


public String getContent(){
    return content;
}


public Long getTypeId(){
    return typeId;
}


public Long getStatusId(){
    return statusId;
}


public String getTitle(){
    return title;
}


public void setCatalogId(Long catalogId){
    this.catalogId = catalogId;
}


public long getIsCollected(){
    return isCollected;
}


public Set<User> getUserss(){
    return userss;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public Long getCatalogId(){
    return catalogId;
}


public void setAttachId(Long attachId){
    this.attachId = attachId;
}


@Override
public String toString(){
    return "Note [noteId=" + noteId + ", title=" + title + ", content=" + content + ", createTime=" + createTime + ",isCollected=" + isCollected + "]";
}


public Long getNoteId(){
    return noteId;
}


public void setReceiver(String receiver){
    this.receiver = receiver;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ noteId).concat("/setReceiver"))

.queryParam("receiver",receiver)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreatemanId(Long createmanId){
    this.createmanId = createmanId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ noteId).concat("/setCreatemanId"))

.queryParam("createmanId",createmanId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUserss(Set<User> userss){
    this.userss = userss;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ noteId).concat("/setUserss"))

.queryParam("userss",userss)
;
restTemplate.put(builder.toUriString(),null);
}


}