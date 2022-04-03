package com.csquard.mregister.payload;
 public class UploadFileResponse {

 private  String fileName;

 private  String fileDownloadUri;

 private  String fileType;

 private  long size;

public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
    this.fileName = fileName;
    this.fileDownloadUri = fileDownloadUri;
    this.fileType = fileType;
    this.size = size;
}
public void setFileName(String fileName){
    this.fileName = fileName;
}


public long getSize(){
    return size;
}


public void setFileType(String fileType){
    this.fileType = fileType;
}


public void setSize(long size){
    this.size = size;
}


public void setFileDownloadUri(String fileDownloadUri){
    this.fileDownloadUri = fileDownloadUri;
}


public String getFileDownloadUri(){
    return fileDownloadUri;
}


public String getFileName(){
    return fileName;
}


public String getFileType(){
    return fileType;
}


}