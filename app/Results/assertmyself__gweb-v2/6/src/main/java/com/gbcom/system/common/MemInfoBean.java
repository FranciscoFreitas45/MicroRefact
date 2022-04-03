package com.gbcom.system.common;
 import java.io.Serializable;
public class MemInfoBean implements Serializable{

 private  long serialVersionUID;

 private  String memTotal;

 private  String memUsed;

 private  String memFree;


public String getMemFree(){
    return memFree;
}


public String getMemTotal(){
    return memTotal;
}


public void setMemUsed(String memUsed){
    this.memUsed = memUsed;
}


public String getMemUsed(){
    return memUsed;
}


@Override
public String toString(){
    return "MemInfoBean [memTotal=" + memTotal + ", memUsed=" + memUsed + ", memFree=" + memFree + ", memUseRate=" + "]";
}


public void setMemTotal(String memTotal){
    this.memTotal = memTotal;
}


public void setMemFree(String memFree){
    this.memFree = memFree;
}


}