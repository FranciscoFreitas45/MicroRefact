package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class NetworkDnsItemController {

 private NetworkDnsItem networkdnsitem;

 private NetworkDnsItem networkdnsitem;


@PutMapping
("/setCnameDomain")
public void setCnameDomain(@RequestParam(name = "cnameDomain") String cnameDomain){
networkdnsitem.setCnameDomain(cnameDomain);
}


@PutMapping
("/setDomainType")
public void setDomainType(@RequestParam(name = "domainType") Integer domainType){
networkdnsitem.setDomainType(domainType);
}


@PutMapping
("/setDomainName")
public void setDomainName(@RequestParam(name = "domainName") String domainName){
networkdnsitem.setDomainName(domainName);
}


@GetMapping
("/extractToString")
public String extractToString(@RequestParam(name = "networkEipItems") List<NetworkEipItem> networkEipItems){
  return networkdnsitem.extractToString(networkEipItems);
}


@GetMapping
("/toString")
public String toString(){
  return networkdnsitem.toString();
}


}