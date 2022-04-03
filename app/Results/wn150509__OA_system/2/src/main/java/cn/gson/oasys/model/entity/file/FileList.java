package cn.gson.oasys.model.entity.file;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_file_list")
public class FileList {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "file_id")
 private  Long fileId;

@Column(name = "file_name")
 private  String fileName;

@Column(name = "file_path")
 private  String filePath;

 private  Long size;

@Column(name = "content_type")
 private  String contentType;

@Column(name = "upload_time")
 private  Date uploadTime;

 private  String model;

@Column(name = "file_shuffix")
 private  String fileShuffix;

@Column(name = "file_istrash")
 private  Long fileIstrash;

@Column(name = "file_isshare")
 private  Long fileIsshare;

@Transient
 private  User user;

@ManyToOne
@JoinColumn(name = "path_id")
@JsonIgnore
 private  FilePath fpath;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public FileList() {
}
public User getUser(){
  this.user = userrequest.getUser(this.userId);
return this.user;
}


public void setFileIsshare(Long fileIsshare){
    this.fileIsshare = fileIsshare;
}


public Long getFileIsshare(){
    return fileIsshare;
}


public void setFileName(String fileName){
    this.fileName = fileName;
}


public void setSize(Long size){
    this.size = size;
}


public String getFileShuffix(){
    return fileShuffix;
}


public void setUploadTime(Date uploadTime){
    this.uploadTime = uploadTime;
}


public void setContentType(String contentType){
    this.contentType = contentType;
}


public void setFileIstrash(Long fileIstrash){
    this.fileIstrash = fileIstrash;
}


public void setUser(User user){
 userrequest.setUser(user,this.userId);
}



public String getFileName(){
    return fileName;
}


public String getModel(){
    return model;
}


public Date getUploadTime(){
    return uploadTime;
}


public void setFileId(Long fileId){
    this.fileId = fileId;
}


public Long getFileId(){
    return fileId;
}


public Long getSize(){
    return size;
}


public void setFpath(FilePath fpath){
    this.fpath = fpath;
}


public String getFilePath(){
    return filePath;
}


public void setModel(String model){
    this.model = model;
}


public String getContentType(){
    return contentType;
}


public void setFilePath(String filePath){
    this.filePath = filePath;
}


public Long getFileIstrash(){
    return fileIstrash;
}


public FilePath getFpath(){
    return fpath;
}


@Override
public String toString(){
    return "FileList [fileId=" + fileId + ", fileName=" + fileName + ", filePath=" + filePath + ", size=" + size + ", contentType=" + contentType + ", uploadTime=" + uploadTime + ", model=" + model + ", fileShuffix=" + fileShuffix + ", fileIstrash=" + fileIstrash + ", fileIsshare=" + fileIsshare + "]";
}


public void setFileShuffix(String fileShuffix){
    this.fileShuffix = fileShuffix;
}


}