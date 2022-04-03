package org.gliderwiki.web.vo;
 import org.springframework.web.multipart.MultipartFile;
public class TempUploadVo extends BaseObjectBean{

 private  String fileSaveName;

 private  String fileRealName;

 private  String filePath;

 private  double fileSize;

 private  String fileType;

 private  String thumbYn;

 private  String thumbPath;

 private  String thumbName;

 private  boolean isUploaded;

 private  MultipartFile file;


public void setFileType(String fileType){
    this.fileType = fileType;
}


public void setThumbYn(String thumbYn){
    this.thumbYn = thumbYn;
}


public void setFileSize(double fileSize){
    this.fileSize = fileSize;
}


public String getThumbName(){
    return thumbName;
}


public String getFileSaveName(){
    return fileSaveName;
}


public String getThumbPath(){
    return thumbPath;
}


public MultipartFile getFile(){
    return file;
}


public String getFileRealName(){
    return fileRealName;
}


public String getThumbYn(){
    return thumbYn;
}


public String getFileType(){
    return fileType;
}


public double getFileSize(){
    return fileSize;
}


public void setFile(MultipartFile file){
    this.file = file;
}


public String getFilePath(){
    return filePath;
}


public void setThumbPath(String thumbPath){
    this.thumbPath = thumbPath;
}


public void setFileRealName(String fileRealName){
    this.fileRealName = fileRealName;
}


public void setIsUploaded(boolean isUploaded){
    this.isUploaded = isUploaded;
}


public void setFilePath(String filePath){
    this.filePath = filePath;
}


public boolean isUploaded(){
    return isUploaded;
}


public void setFileSaveName(String fileSaveName){
    this.fileSaveName = fileSaveName;
}


public void setThumbName(String thumbName){
    this.thumbName = thumbName;
}


}