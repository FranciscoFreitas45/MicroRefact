package org.live.common.support;
 import org.springframework.stereotype.Component;
@Component
public class UploadFilePathConfig {

 public  String UPLOAD_FILE_ROOT_PATH_KEY;

 public  String UPLOAD_FILE_PATH_PREFIX_KEY;

 public  String UPLOAD_FILE_PATH_KEY;

 private  String uploadFileRootPath;

 private  String uploadFilePathPrefix;

 private  String uploadFilePath;


public void setUploadFileRootPath(String uploadFileRootPath){
    this.uploadFileRootPath = uploadFileRootPath;
}


public String getUploadFileRootPath(){
    return uploadFileRootPath;
}


public String getUploadFilePath(){
    return uploadFilePath;
}


public void setUploadFilePath(String uploadFilePath){
    this.uploadFilePath = uploadFilePath;
}


public void setUploadFilePathPrefix(String uploadFilePathPrefix){
    this.uploadFilePathPrefix = uploadFilePathPrefix;
}


public String getUploadFilePathPrefix(){
    return uploadFilePathPrefix;
}


}