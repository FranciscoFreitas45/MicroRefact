package com.sobey.cmop.mvc.entity.ToJson;
 public class MonitorComputeJson {

 private  Integer id;

 private  String identifier;

 private  String ipAddress;

 private  String cpuWarn;

 private  String cpuCritical;

 private  String memoryWarn;

 private  String memoryCritical;

 private  String diskWarn;

 private  String diskCritical;

 private  String pingLossWarn;

 private  String pingLossCritical;

 private  String pingDelayWarn;

 private  String pingDelayCritical;

 private  String maxProcessWarn;

 private  String maxProcessCritical;

 private  String port;

 private  String process;

 private  String mountPoint;


public void setMountPoint(String mountPoint){
    this.mountPoint = mountPoint;
}


public String getIpAddress(){
    return ipAddress;
}


public void setDiskWarn(String diskWarn){
    this.diskWarn = diskWarn;
}


public void setIdentifier(String identifier){
    this.identifier = identifier;
}


public String getMemoryWarn(){
    return memoryWarn;
}


public Integer getId(){
    return id;
}


public void setMaxProcessCritical(String maxProcessCritical){
    this.maxProcessCritical = maxProcessCritical;
}


public void setPort(String port){
    this.port = port;
}


public void setMemoryWarn(String memoryWarn){
    this.memoryWarn = memoryWarn;
}


public String getCpuCritical(){
    return cpuCritical;
}


public String getIdentifier(){
    return identifier;
}


public String getCpuWarn(){
    return cpuWarn;
}


public void setId(Integer id){
    this.id = id;
}


public String getMaxProcessCritical(){
    return maxProcessCritical;
}


public void setPingDelayCritical(String pingDelayCritical){
    this.pingDelayCritical = pingDelayCritical;
}


public void setPingLossWarn(String pingLossWarn){
    this.pingLossWarn = pingLossWarn;
}


public String getDiskCritical(){
    return diskCritical;
}


public void setPingDelayWarn(String pingDelayWarn){
    this.pingDelayWarn = pingDelayWarn;
}


public String getProcess(){
    return process;
}


public void setDiskCritical(String diskCritical){
    this.diskCritical = diskCritical;
}


public String getPingDelayCritical(){
    return pingDelayCritical;
}


public String getPingLossWarn(){
    return pingLossWarn;
}


public void setProcess(String process){
    this.process = process;
}


public String getMaxProcessWarn(){
    return maxProcessWarn;
}


public void setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
}


public String getPingLossCritical(){
    return pingLossCritical;
}


public void setCpuCritical(String cpuCritical){
    this.cpuCritical = cpuCritical;
}


public String getMemoryCritical(){
    return memoryCritical;
}


public String getDiskWarn(){
    return diskWarn;
}


public void setCpuWarn(String cpuWarn){
    this.cpuWarn = cpuWarn;
}


public String getPort(){
    return port;
}


public void setMaxProcessWarn(String maxProcessWarn){
    this.maxProcessWarn = maxProcessWarn;
}


public void setPingLossCritical(String pingLossCritical){
    this.pingLossCritical = pingLossCritical;
}


public String getMountPoint(){
    return mountPoint;
}


public void setMemoryCritical(String memoryCritical){
    this.memoryCritical = memoryCritical;
}


public String getPingDelayWarn(){
    return pingDelayWarn;
}


}