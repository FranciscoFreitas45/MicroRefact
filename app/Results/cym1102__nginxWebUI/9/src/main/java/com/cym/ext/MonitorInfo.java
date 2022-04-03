package com.cym.ext;
 import java.util.List;
import java.util.Map;
public class MonitorInfo {

 private  String totalMemorySize;

 private  String usedMemory;

 private  String cpuRatio;

 private  String memRatio;

 private  Integer cpuCount;

 private  Integer threadCount;


public void setMemRatio(String memRatio){
    this.memRatio = memRatio;
}


public void setUsedMemory(String usedMemory){
    this.usedMemory = usedMemory;
}


public void setCpuCount(Integer cpuCount){
    this.cpuCount = cpuCount;
}


public String getTotalMemorySize(){
    return totalMemorySize;
}


public void setTotalMemorySize(String totalMemorySize){
    this.totalMemorySize = totalMemorySize;
}


public String getUsedMemory(){
    return usedMemory;
}


public String getCpuRatio(){
    return cpuRatio;
}


public void setThreadCount(Integer threadCount){
    this.threadCount = threadCount;
}


public void setCpuRatio(String cpuRatio){
    this.cpuRatio = cpuRatio;
}


public Integer getThreadCount(){
    return threadCount;
}


public String getMemRatio(){
    return memRatio;
}


public Integer getCpuCount(){
    return cpuCount;
}


}