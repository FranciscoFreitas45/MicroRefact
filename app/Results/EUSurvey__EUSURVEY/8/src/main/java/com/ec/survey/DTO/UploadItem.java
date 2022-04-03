package com.ec.survey.DTO;
 import org.springframework.web.multipart.commons.CommonsMultipartFile;
public class UploadItem {

 private  String filename;

 private  CommonsMultipartFile fileData;


public String getFilename(){
    return filename;
}


public CommonsMultipartFile getFileData(){
    return fileData;
}


}