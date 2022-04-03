package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class NetworkEipItemController {

 private NetworkEipItem networkeipitem;

 private NetworkEipItem networkeipitem;


@PutMapping
("/setComputeItem")
public void setComputeItem(@RequestParam(name = "computeItem") ComputeItem computeItem){
networkeipitem.setComputeItem(computeItem);
}


@PutMapping
("/setNetworkElbItem")
public void setNetworkElbItem(@RequestParam(name = "networkElbItem") NetworkElbItem networkElbItem){
networkeipitem.setNetworkElbItem(networkElbItem);
}


@PutMapping
("/setIspType")
public void setIspType(@RequestParam(name = "ispType") Integer ispType){
networkeipitem.setIspType(ispType);
}


@PutMapping
("/setIpAddress")
public void setIpAddress(@RequestParam(name = "ipAddress") String ipAddress){
networkeipitem.setIpAddress(ipAddress);
}


@PutMapping
("/setOldIp")
public void setOldIp(@RequestParam(name = "oldIp") String oldIp){
networkeipitem.setOldIp(oldIp);
}


}