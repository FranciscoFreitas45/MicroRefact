package com.cym.ext;
 public class DiskInfo {

 private String path;

 private String useSpace;

 private String totalSpace;

 private String percent;


public String getTotalSpace(){
    return totalSpace;
}


public void setPercent(String percent){
    this.percent = percent;
}


public String getPath(){
    return path;
}


public void setPath(String path){
    this.path = path;
}


public void setUseSpace(String useSpace){
    this.useSpace = useSpace;
}


public String getPercent(){
    return percent;
}


public String getUseSpace(){
    return useSpace;
}


public void setTotalSpace(String totalSpace){
    this.totalSpace = totalSpace;
}


}