package cn.gson.oasys.model.entity.note;
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
@Entity
@Table(name = "aoa_note_list")
public class Note {

@Id
@Column(name = "note_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long noteId;

@NotBlank
 private  String title;

@NotBlank
 private  String content;

@Column(name = "catalog_id")
 private  Long catalogId;

@Column(name = "type_id")
 private  Long typeId;

@Column(name = "status_id")
 private  Long statusId;

@Column(name = "attach_id")
 private  Long attachId;

@Column(name = "create_time")
 private  Date createTime;

@Column(name = "is_collected")
 private  Long isCollected;

@Column(name = "createman_id")
 private  Long createmanId;

@Column(name = "receiver")
 private  String receiver;

@ManyToMany
@JoinTable(name = "aoa_receiver_note", joinColumns = { @JoinColumn(name = "note_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
 private  Set<User> userss;

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


public void setIsCollected(Long isCollected){
    this.isCollected = isCollected;
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


public void setStatusId(Long statusId){
    this.statusId = statusId;
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


public void setCreatemanId(Long createmanId){
    this.createmanId = createmanId;
}


public void setCatalogId(Long catalogId){
    this.catalogId = catalogId;
}


public void setUserss(Set<User> userss){
    this.userss = userss;
}


public long getIsCollected(){
    return isCollected;
}


public Set<User> getUserss(){
    return userss;
}


public void setTitle(String title){
    this.title = title;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public Long getCatalogId(){
    return catalogId;
}


public void setTypeId(Long typeId){
    this.typeId = typeId;
}


public void setAttachId(Long attachId){
    this.attachId = attachId;
}


public void setReceiver(String receiver){
    this.receiver = receiver;
}


@Override
public String toString(){
    return "Note [noteId=" + noteId + ", title=" + title + ", content=" + content + ", createTime=" + createTime + ",isCollected=" + isCollected + "]";
}


public void setNoteId(Long noteId){
    this.noteId = noteId;
}


public Long getNoteId(){
    return noteId;
}


}