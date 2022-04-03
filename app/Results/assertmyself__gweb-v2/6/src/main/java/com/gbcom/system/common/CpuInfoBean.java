package com.gbcom.system.common;
 import java.io.Serializable;
public class CpuInfoBean implements Serializable{

 private  long serialVersionUID;

 private  String cpuUser;

 private  String cpuSys;

 private  String cpuWait;

 private  String cpuIdle;

 private  String cpuCombined;


public String getCpuUser(){
    return cpuUser;
}


public void setCpuIdle(String cpuIdle){
    this.cpuIdle = cpuIdle;
}


public void setCpuWait(String cpuWait){
    this.cpuWait = cpuWait;
}


public void setCpuCombined(String cpuCombined){
    this.cpuCombined = cpuCombined;
}


public String getCpuWait(){
    return cpuWait;
}


@Override
public String toString(){
    return "CpuInfoBean [cpuUser=" + cpuUser + ", cpuSys=" + cpuSys + ", cpuWait=" + cpuWait + ", cpuIdle=" + cpuIdle + ", cpuCombined=" + cpuCombined + "]";
}


public void setCpuSys(String cpuSys){
    this.cpuSys = cpuSys;
}


public String getCpuSys(){
    return cpuSys;
}


public void setCpuUser(String cpuUser){
    this.cpuUser = cpuUser;
}


public String getCpuIdle(){
    return cpuIdle;
}


public String getCpuCombined(){
    return cpuCombined;
}


}