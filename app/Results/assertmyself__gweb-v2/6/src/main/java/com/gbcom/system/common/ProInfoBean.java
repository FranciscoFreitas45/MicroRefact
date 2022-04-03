package com.gbcom.system.common;
 import java.io.Serializable;
public class ProInfoBean implements Serializable{

 private  long serialVersionUID;

 private  String name;

 private  String pid;

 private  String startTime;

 private  String memUse;

 private  String cpuTime;

 private  String path;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setPath(String path){
    this.path = path;
}


public void setPid(String pid){
    this.pid = pid;
}


public void setCpuTime(String cpuTime){
    this.cpuTime = cpuTime;
}


public String getMemUse(){
    return memUse;
}


public String getCpuTime(){
    return cpuTime;
}


public String getPath(){
    return path;
}


public void setMemUse(String memUse){
    this.memUse = memUse;
}


@Override
public String toString(){
    return "ProInfoBean [name=" + name + ", pid=" + pid + ", startTime=" + startTime + ", memUse=" + memUse + ", cpuTime=" + cpuTime + ", path=" + path + "]";
}


public String getStartTime(){
    return startTime;
}


public void setStartTime(String startTime){
    this.startTime = startTime;
}


public String getPid(){
    return pid;
}


}