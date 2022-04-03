package org.danyuan.application.share.file.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_share_file_path_info")
@NamedQuery(name = "SysShareFilePathInfo.findAll", query = "SELECT s FROM SysShareFilePathInfo s")
public class SysShareFilePathInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "path")
 private  String path;

@Column(name = "file_name")
 private  String fileName;

@Column(name = "file_size")
 private  String fileSize;

@Column(name = "share_uuid")
 private  String shareUuid;

@Column(name = "md5")
 private  String md5;

@Column(name = "path_type")
 private  String pathType;

@Column(name = "sha256")
 private  String sha256;

@Column(name = "password")
 private  String password;

@Column(name = "sha1")
 private  String sha1;

@Column(name = "sha512")
 private  String sha512;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysShareFilePathInfo() {
    super();
}
public String getMd5(){
    return md5;
}


public void setPassword(String password){
    this.password = password;
}


public void setFileSize(String fileSize){
    this.fileSize = fileSize;
}


public String getShareUuid(){
    return shareUuid;
}


public void setPathType(String pathType){
    this.pathType = pathType;
}


public String getSha256(){
    return sha256;
}


public void setPath(String path){
    this.path = path;
}


public void setShareUuid(String shareUuid){
    this.shareUuid = shareUuid;
}


public void setFileName(String fileName){
    this.fileName = fileName;
}


public String getFileSize(){
    return fileSize;
}


public String getPassword(){
    return password;
}


public void setSha1(String sha1){
    this.sha1 = sha1;
}


public String getPath(){
    return path;
}


public void setSha256(String sha256){
    this.sha256 = sha256;
}


public String getSha512(){
    return sha512;
}


public String getPathType(){
    return pathType;
}


public void setSha512(String sha512){
    this.sha512 = sha512;
}


public String getFileName(){
    return fileName;
}


public void setMd5(String md5){
    this.md5 = md5;
}


public String getSha1(){
    return sha1;
}


}