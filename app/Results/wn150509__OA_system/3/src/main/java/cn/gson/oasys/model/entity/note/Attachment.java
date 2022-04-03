package cn.gson.oasys.model.entity.note;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "aoa_attachment_list")
public class Attachment {

@Id
@Column(name = "attachment_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long attachmentId;

@Column(name = "user_id")
 private  String userId;

@Column(name = "attachment_name")
 private  String attachmentName;

@Column(name = "attachment_path")
 private  String attachmentPath;

@Column(name = "attachment_size")
 private  Long attachmentSize;

@Column(name = "attachment_type")
 private  String attachmentType;

@Column(name = "upload_time")
 private  Date uploadTime;

 private  String model;

@Column(name = "attachment_shuffix")
 private  String attachmentShuffix;

public Attachment(Long attachmentId, String attachmentName, String attachmentPath, long attachmentSize, String attachmentType, Date uploadTime, String model, String attachmentShuffix) {
    super();
    this.attachmentId = attachmentId;
    this.attachmentName = attachmentName;
    this.attachmentPath = attachmentPath;
    this.attachmentSize = attachmentSize;
    this.attachmentType = attachmentType;
    this.uploadTime = uploadTime;
    this.model = model;
    this.attachmentShuffix = attachmentShuffix;
}public Attachment() {
    super();
// TODO Auto-generated constructor stub
}
public void setAttachmentId(Long attachmentId){
    this.attachmentId = attachmentId;
}


public String getModel(){
    return model;
}


public void setAttachmentSize(Long attachmentSize){
    this.attachmentSize = attachmentSize;
}


public Date getUploadTime(){
    return uploadTime;
}


public String getAttachmentName(){
    return attachmentName;
}


public String getAttachmentShuffix(){
    return attachmentShuffix;
}


public Long getAttachmentId(){
    return attachmentId;
}


public void setAttachmentPath(String attachmentPath){
    this.attachmentPath = attachmentPath;
}


public void setAttachmentName(String attachmentName){
    this.attachmentName = attachmentName;
}


public String getAttachmentPath(){
    return attachmentPath;
}


public Long getAttachmentSize(){
    return attachmentSize;
}


public void setUploadTime(Date uploadTime){
    this.uploadTime = uploadTime;
}


public void setModel(String model){
    this.model = model;
}


public void setAttachmentType(String attachmentType){
    this.attachmentType = attachmentType;
}


@Override
public String toString(){
    return "Attachment [attachmentId=" + attachmentId + ", attachmentName=" + attachmentName + ", attachmentPath=" + attachmentPath + ", attachmentSize=" + attachmentSize + ", attachmentType=" + attachmentType + ", uploadTime=" + uploadTime + ", model=" + model + ", attachmentShuffix=" + attachmentShuffix + "]";
}


public String getAttachmentType(){
    return attachmentType;
}


public String getUserId(){
    return userId;
}


public void setAttachmentShuffix(String attachmentShuffix){
    this.attachmentShuffix = attachmentShuffix;
}


public void setUserId(String userId){
    this.userId = userId;
}


}