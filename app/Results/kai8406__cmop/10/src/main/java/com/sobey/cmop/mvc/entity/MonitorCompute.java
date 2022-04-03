package com.sobey.cmop.mvc.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "monitor_compute", catalog = "cmop")
public class MonitorCompute {

 private  Integer id;

 private  Apply apply;

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

// Constructors
/**
 * default constructor
 */
public MonitorCompute() {
}/**
 * minimal constructor
 */
public MonitorCompute(Apply apply, String identifier, String ipAddress) {
    this.apply = apply;
    this.identifier = identifier;
    this.ipAddress = ipAddress;
}/**
 * full constructor
 */
public MonitorCompute(Apply apply, String identifier, String ipAddress, String cpuWarn, String cpuCritical, String memoryWarn, String memoryCritical, String diskWarn, String diskCritical, String pingLossWarn, String pingLossCritical, String pingDelayWarn, String pingDelayCritical, String maxProcessWarn, String maxProcessCritical, String port, String process, String mountPoint) {
    this.apply = apply;
    this.identifier = identifier;
    this.ipAddress = ipAddress;
    this.cpuWarn = cpuWarn;
    this.cpuCritical = cpuCritical;
    this.memoryWarn = memoryWarn;
    this.memoryCritical = memoryCritical;
    this.diskWarn = diskWarn;
    this.diskCritical = diskCritical;
    this.pingLossWarn = pingLossWarn;
    this.pingLossCritical = pingLossCritical;
    this.pingDelayWarn = pingDelayWarn;
    this.pingDelayCritical = pingDelayCritical;
    this.maxProcessWarn = maxProcessWarn;
    this.maxProcessCritical = maxProcessCritical;
    this.port = port;
    this.process = process;
    this.mountPoint = mountPoint;
}
public void setMountPoint(String mountPoint){
    this.mountPoint = mountPoint;
}


@Column(name = "ip_address", nullable = false, length = 45)
public String getIpAddress(){
    return ipAddress;
}


public void setDiskWarn(String diskWarn){
    this.diskWarn = diskWarn;
}


public void setIdentifier(String identifier){
    this.identifier = identifier;
}


@Column(name = "memory_warn", length = 5)
public String getMemoryWarn(){
    return memoryWarn;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


public void setMaxProcessCritical(String maxProcessCritical){
    this.maxProcessCritical = maxProcessCritical;
}


public void setPort(String port){
    this.port = port;
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "apply_id", nullable = false)
public Apply getApply(){
    return this.apply;
}


public void setMemoryWarn(String memoryWarn){
    this.memoryWarn = memoryWarn;
}


@Column(name = "cpu_critical", length = 5)
public String getCpuCritical(){
    return cpuCritical;
}


@Column(name = "identifier", nullable = false, length = 45)
public String getIdentifier(){
    return this.identifier;
}


@Column(name = "cpu_warn", length = 5)
public String getCpuWarn(){
    return cpuWarn;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "max_process_critical", length = 5)
public String getMaxProcessCritical(){
    return maxProcessCritical;
}


public void setApply(Apply apply){
    this.apply = apply;
}


public void setPingDelayCritical(String pingDelayCritical){
    this.pingDelayCritical = pingDelayCritical;
}


public void setPingLossWarn(String pingLossWarn){
    this.pingLossWarn = pingLossWarn;
}


@Column(name = "disk_critical", length = 5)
public String getDiskCritical(){
    return diskCritical;
}


public void setPingDelayWarn(String pingDelayWarn){
    this.pingDelayWarn = pingDelayWarn;
}


@Column(name = "process", length = 200)
public String getProcess(){
    return process;
}


public void setDiskCritical(String diskCritical){
    this.diskCritical = diskCritical;
}


@Column(name = "ping_delay_critical", length = 10)
public String getPingDelayCritical(){
    return pingDelayCritical;
}


@Column(name = "ping_loss_warn", length = 5)
public String getPingLossWarn(){
    return pingLossWarn;
}


public void setProcess(String process){
    this.process = process;
}


@Column(name = "max_process_warn", length = 5)
public String getMaxProcessWarn(){
    return maxProcessWarn;
}


public void setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
}


@Column(name = "ping_loss_critical", length = 5)
public String getPingLossCritical(){
    return pingLossCritical;
}


public void setCpuCritical(String cpuCritical){
    this.cpuCritical = cpuCritical;
}


@Column(name = "memory_critical", length = 5)
public String getMemoryCritical(){
    return memoryCritical;
}


@Column(name = "disk_warn", length = 5)
public String getDiskWarn(){
    return diskWarn;
}


public void setCpuWarn(String cpuWarn){
    this.cpuWarn = cpuWarn;
}


@Column(name = "port", length = 200)
public String getPort(){
    return port;
}


public void setMaxProcessWarn(String maxProcessWarn){
    this.maxProcessWarn = maxProcessWarn;
}


public void setPingLossCritical(String pingLossCritical){
    this.pingLossCritical = pingLossCritical;
}


@Column(name = "mount_point", length = 200)
public String getMountPoint(){
    return mountPoint;
}


public void setMemoryCritical(String memoryCritical){
    this.memoryCritical = memoryCritical;
}


@Column(name = "ping_delay_warn", length = 10)
public String getPingDelayWarn(){
    return pingDelayWarn;
}


}