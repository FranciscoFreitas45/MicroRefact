package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ComputeItemController {

 private ComputeItem computeitem;

 private ComputeItem computeitem;


@PutMapping
("/setId")
public void setId(@RequestParam(name = "id") Integer id){
computeitem.setId(id);
}


@PutMapping
("/setApply")
public void setApply(@RequestParam(name = "apply") Apply apply){
computeitem.setApply(apply);
}


@PutMapping
("/setIdentifier")
public void setIdentifier(@RequestParam(name = "identifier") String identifier){
computeitem.setIdentifier(identifier);
}


@PutMapping
("/setComputeType")
public void setComputeType(@RequestParam(name = "computeType") Integer computeType){
computeitem.setComputeType(computeType);
}


@PutMapping
("/setOsType")
public void setOsType(@RequestParam(name = "osType") Integer osType){
computeitem.setOsType(osType);
}


@PutMapping
("/setOsBit")
public void setOsBit(@RequestParam(name = "osBit") Integer osBit){
computeitem.setOsBit(osBit);
}


@PutMapping
("/setServerType")
public void setServerType(@RequestParam(name = "serverType") Integer serverType){
computeitem.setServerType(serverType);
}


@PutMapping
("/setRemark")
public void setRemark(@RequestParam(name = "remark") String remark){
computeitem.setRemark(remark);
}


@PutMapping
("/setInnerIp")
public void setInnerIp(@RequestParam(name = "innerIp") String innerIp){
computeitem.setInnerIp(innerIp);
}


@PutMapping
("/setOldIp")
public void setOldIp(@RequestParam(name = "oldIp") String oldIp){
computeitem.setOldIp(oldIp);
}


@PutMapping
("/setHostName")
public void setHostName(@RequestParam(name = "hostName") String hostName){
computeitem.setHostName(hostName);
}


@PutMapping
("/setServerAlias")
public void setServerAlias(@RequestParam(name = "serverAlias") String serverAlias){
computeitem.setServerAlias(serverAlias);
}


@PutMapping
("/setHostServerAlias")
public void setHostServerAlias(@RequestParam(name = "hostServerAlias") String hostServerAlias){
computeitem.setHostServerAlias(hostServerAlias);
}


@PutMapping
("/setOsStorageAlias")
public void setOsStorageAlias(@RequestParam(name = "osStorageAlias") String osStorageAlias){
computeitem.setOsStorageAlias(osStorageAlias);
}


@GetMapping
("/toString")
public String toString(){
  return computeitem.toString();
}


@GetMapping
("/extractToString")
public String extractToString(@RequestParam(name = "networkEsgItems") List<NetworkEsgItem> networkEsgItems){
  return computeitem.extractToString(networkEsgItems);
}


}