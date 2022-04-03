package com.cym.ext;
 import java.util.List;
public class ConfExt {

 private String conf;

 private List<ConfFile> fileList;


public String getConf(){
    return conf;
}


public void setFileList(List<ConfFile> fileList){
    this.fileList = fileList;
}


public void setConf(String conf){
    this.conf = conf;
}


public List<ConfFile> getFileList(){
    return fileList;
}


}