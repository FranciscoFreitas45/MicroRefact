package cn.gson.oasys.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
public class Attachment {

 private  Long attachmentId;

 private  String userId;

 private  String attachmentName;

 private  String attachmentPath;

 private  Long attachmentSize;

 private  String attachmentType;

 private  Date uploadTime;

 private  String model;

 private  String attachmentShuffix;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

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
public String getModel(){
    return model;
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


public String getAttachmentPath(){
    return attachmentPath;
}


public Long getAttachmentSize(){
    return attachmentSize;
}


public String getAttachmentType(){
    return attachmentType;
}


public String getUserId(){
    return userId;
}


public void setAttachmentName(String attachmentName){
    this.attachmentName = attachmentName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ attachmentId).concat("/setAttachmentName"))

.queryParam("attachmentName",attachmentName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAttachmentPath(String attachmentPath){
    this.attachmentPath = attachmentPath;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ attachmentId).concat("/setAttachmentPath"))

.queryParam("attachmentPath",attachmentPath)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAttachmentShuffix(String attachmentShuffix){
    this.attachmentShuffix = attachmentShuffix;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ attachmentId).concat("/setAttachmentShuffix"))

.queryParam("attachmentShuffix",attachmentShuffix)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAttachmentSize(Long attachmentSize){
    this.attachmentSize = attachmentSize;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ attachmentId).concat("/setAttachmentSize"))

.queryParam("attachmentSize",attachmentSize)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAttachmentType(String attachmentType){
    this.attachmentType = attachmentType;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ attachmentId).concat("/setAttachmentType"))

.queryParam("attachmentType",attachmentType)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUploadTime(Date uploadTime){
    this.uploadTime = uploadTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ attachmentId).concat("/setUploadTime"))

.queryParam("uploadTime",uploadTime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUserId(String userId){
    this.userId = userId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ attachmentId).concat("/setUserId"))

.queryParam("userId",userId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setModel(String model){
    this.model = model;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ attachmentId).concat("/setModel"))

.queryParam("model",model)
;
restTemplate.put(builder.toUriString(),null);
}


}