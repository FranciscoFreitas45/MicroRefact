package org.danyuan.application.share.file.po;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.danyuan.application.common.base.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "sys_share_file_info")
@NamedQuery(name = "SysShareFileInfo.findAll", query = "SELECT s FROM SysShareFileInfo s")
public class SysShareFileInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "file_detial")
 private  String fileDetial;

@Column(name = "file_instru")
 private  String fileInstru;

@Column(name = "generate_type")
 private  String generateType;

@Column(name = "publish_org")
 private  String publishOrg;

@Column(name = "generate_type2")
 private  String generateType2;

@Column(name = "file_name")
 private  String fileName;

@Column(name = "file_size")
 private  String fileSize;

@Column(name = "file_img")
 private  String fileImg;

@Column(name = "file_type")
 private  String fileType;

@Column(name = "from_path")
 private  String fromPath;

@Column(name = "view_times", precision = 10)
 private  Integer viewTimes;

@Temporal(TemporalType.DATE)
@DateTimeFormat(style = "yyyy-MM-dd")
@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
@Column(name = "publish_date", nullable = false)
 private  Date publishDate;

@Column(name = "from_web")
 private  String fromWeb;

@Column(name = "version")
 private  String version;

@Column(name = "author")
 private  String author;

@Column(name = "language")
 private  String language;

/**
 * 构造方法：
 * 描 述： 默认构造函数
 * 参 数：
 * 作 者 ： test
 * @throws
 */
public SysShareFileInfo() {
    super();
}
public void setViewTimes(Integer viewTimes){
    this.viewTimes = viewTimes;
}


public void setFileInstru(String fileInstru){
    this.fileInstru = fileInstru;
}


public void setPublishOrg(String publishOrg){
    this.publishOrg = publishOrg;
}


public String getAuthor(){
    return author;
}


public void setPublishDate(Date publishDate){
    this.publishDate = publishDate;
}


public String getFileDetial(){
    return fileDetial;
}


public Date getPublishDate(){
    return publishDate;
}


public String getFromPath(){
    return fromPath;
}


public String getFileType(){
    return fileType;
}


public void setFileName(String fileName){
    this.fileName = fileName;
}


public String getFileSize(){
    return fileSize;
}


public void setFromPath(String fromPath){
    this.fromPath = fromPath;
}


public void setGenerateType2(String generateType2){
    this.generateType2 = generateType2;
}


public void setFromWeb(String fromWeb){
    this.fromWeb = fromWeb;
}


public String getFileName(){
    return fileName;
}


public Integer getViewTimes(){
    return viewTimes;
}


public String getVersion(){
    return version;
}


public void setFileDetial(String fileDetial){
    this.fileDetial = fileDetial;
}


public void setFileImg(String fileImg){
    this.fileImg = fileImg;
}


public String getLanguage(){
    return language;
}


public void setFileType(String fileType){
    this.fileType = fileType;
}


public String getFileInstru(){
    return fileInstru;
}


public String getPublishOrg(){
    return publishOrg;
}


public String getGenerateType(){
    return generateType;
}


public void setFileSize(String fileSize){
    this.fileSize = fileSize;
}


public void setVersion(String version){
    this.version = version;
}


public String getFileImg(){
    return fileImg;
}


public String getGenerateType2(){
    return generateType2;
}


public String getFromWeb(){
    return fromWeb;
}


public void setAuthor(String author){
    this.author = author;
}


public void setGenerateType(String generateType){
    this.generateType = generateType;
}


public void setLanguage(String language){
    this.language = language;
}


}