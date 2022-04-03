package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class NetworkElbItemController {

 private NetworkElbItem networkelbitem;

 private NetworkElbItem networkelbitem;


@PutMapping
("/setComputeItemList")
public void setComputeItemList(@RequestParam(name = "computeItemList") List<ComputeItem> computeItemList){
networkelbitem.setComputeItemList(computeItemList);
}


@PutMapping
("/setKeepSession")
public void setKeepSession(@RequestParam(name = "keepSession") Boolean keepSession){
networkelbitem.setKeepSession(keepSession);
}


@PutMapping
("/setOldIp")
public void setOldIp(@RequestParam(name = "oldIp") String oldIp){
networkelbitem.setOldIp(oldIp);
}


@PutMapping
("/setVirtualIp")
public void setVirtualIp(@RequestParam(name = "virtualIp") String virtualIp){
networkelbitem.setVirtualIp(virtualIp);
}


}