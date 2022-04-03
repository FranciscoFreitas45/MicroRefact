package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MonitorComputeController {

 private MonitorCompute monitorcompute;

 private MonitorCompute monitorcompute;


@PutMapping
("/setMaxProcessWarn")
public void setMaxProcessWarn(@RequestParam(name = "maxProcessWarn") String maxProcessWarn){
monitorcompute.setMaxProcessWarn(maxProcessWarn);
}


@PutMapping
("/setPingLossCritical")
public void setPingLossCritical(@RequestParam(name = "pingLossCritical") String pingLossCritical){
monitorcompute.setPingLossCritical(pingLossCritical);
}


@PutMapping
("/setPingLossWarn")
public void setPingLossWarn(@RequestParam(name = "pingLossWarn") String pingLossWarn){
monitorcompute.setPingLossWarn(pingLossWarn);
}


@PutMapping
("/setDiskCritical")
public void setDiskCritical(@RequestParam(name = "diskCritical") String diskCritical){
monitorcompute.setDiskCritical(diskCritical);
}


@PutMapping
("/setDiskWarn")
public void setDiskWarn(@RequestParam(name = "diskWarn") String diskWarn){
monitorcompute.setDiskWarn(diskWarn);
}


@PutMapping
("/setMemoryWarn")
public void setMemoryWarn(@RequestParam(name = "memoryWarn") String memoryWarn){
monitorcompute.setMemoryWarn(memoryWarn);
}


@PutMapping
("/setCpuCritical")
public void setCpuCritical(@RequestParam(name = "cpuCritical") String cpuCritical){
monitorcompute.setCpuCritical(cpuCritical);
}


@PutMapping
("/setCpuWarn")
public void setCpuWarn(@RequestParam(name = "cpuWarn") String cpuWarn){
monitorcompute.setCpuWarn(cpuWarn);
}


@PutMapping
("/setMountPoint")
public void setMountPoint(@RequestParam(name = "mountPoint") String mountPoint){
monitorcompute.setMountPoint(mountPoint);
}


@PutMapping
("/setProcess")
public void setProcess(@RequestParam(name = "process") String process){
monitorcompute.setProcess(process);
}


@PutMapping
("/setPort")
public void setPort(@RequestParam(name = "port") String port){
monitorcompute.setPort(port);
}


@PutMapping
("/setIpAddress")
public void setIpAddress(@RequestParam(name = "ipAddress") String ipAddress){
monitorcompute.setIpAddress(ipAddress);
}


@PutMapping
("/setId")
public void setId(@RequestParam(name = "id") Integer id){
monitorcompute.setId(id);
}


@PutMapping
("/setApply")
public void setApply(@RequestParam(name = "apply") Apply apply){
monitorcompute.setApply(apply);
}


@PutMapping
("/setIdentifier")
public void setIdentifier(@RequestParam(name = "identifier") String identifier){
monitorcompute.setIdentifier(identifier);
}


@PutMapping
("/setMemoryCritical")
public void setMemoryCritical(@RequestParam(name = "memoryCritical") String memoryCritical){
monitorcompute.setMemoryCritical(memoryCritical);
}


@PutMapping
("/setPingDelayWarn")
public void setPingDelayWarn(@RequestParam(name = "pingDelayWarn") String pingDelayWarn){
monitorcompute.setPingDelayWarn(pingDelayWarn);
}


@PutMapping
("/setPingDelayCritical")
public void setPingDelayCritical(@RequestParam(name = "pingDelayCritical") String pingDelayCritical){
monitorcompute.setPingDelayCritical(pingDelayCritical);
}


@PutMapping
("/setMaxProcessCritical")
public void setMaxProcessCritical(@RequestParam(name = "maxProcessCritical") String maxProcessCritical){
monitorcompute.setMaxProcessCritical(maxProcessCritical);
}


}