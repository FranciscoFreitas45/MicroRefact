package org.sdrc.childinfo.model;
 import java.util.List;
import org.springframework.web.multipart.MultipartFile;
public class UploadedFile {

 private  List<MultipartFile> file;


public void setFile(List<MultipartFile> file){
    this.file = file;
}


public List<MultipartFile> getFile(){
    return file;
}


}