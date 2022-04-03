package es.us.isa.ideas.app.util;
 import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties({ "bytes" })
public class FileMetadata {

 private  String fileName;

 private  String fileSize;

 private  String fileType;

 private  byte[] bytes;


public void setBytes(byte[] bytes){
    this.bytes = bytes;
}


public void setFileName(String fileName){
    this.fileName = fileName;
}


public String getFileSize(){
    return fileSize;
}


public void setFileType(String fileType){
    this.fileType = fileType;
}


public void setFileSize(String fileSize){
    this.fileSize = fileSize;
}


public String getFileName(){
    return fileName;
}


public byte[] getBytes(){
    return bytes;
}


public String getFileType(){
    return fileType;
}


}