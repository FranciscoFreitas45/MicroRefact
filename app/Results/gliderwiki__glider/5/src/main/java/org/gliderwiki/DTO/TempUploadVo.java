package org.gliderwiki.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://12";


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


public String getFilePath(){
    return filePath;
}


public boolean isUploaded(){
    return isUploaded;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isUploaded"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}