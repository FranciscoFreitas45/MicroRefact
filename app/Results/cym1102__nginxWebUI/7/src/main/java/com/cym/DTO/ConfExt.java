package com.cym.DTO;
 import java.util.List;
public class ConfExt {

 private String conf;

 private List<ConfFile> fileList;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public String getConf(){
    return conf;
}


public List<ConfFile> getFileList(){
    return fileList;
}


public void setFileList(List<ConfFile> fileList){
    this.fileList = fileList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFileList"))

.queryParam("fileList",fileList)
;
restTemplate.put(builder.toUriString(),null);
}


public void setConf(String conf){
    this.conf = conf;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setConf"))

.queryParam("conf",conf)
;
restTemplate.put(builder.toUriString(),null);
}


}