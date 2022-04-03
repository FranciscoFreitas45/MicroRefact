package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ConfServiceController {

 private ConfService confservice;


@GetMapping
("/getAsycPack")
public AsycPack getAsycPack(@RequestParam(name = "asycData") String[] asycData){
  return confservice.getAsycPack(asycData);
}


@PutMapping
("/setAsycPack")
public void setAsycPack(@RequestParam(name = "asycPack") AsycPack asycPack,@RequestParam(name = "adminName") String adminName){
confservice.setAsycPack(asycPack,adminName);
}


@PutMapping
("/replace")
public void replace(@RequestParam(name = "nginxPath") String nginxPath,@RequestParam(name = "nginxContent") String nginxContent,@RequestParam(name = "subContent") List<String> subContent,@RequestParam(name = "subName") List<String> subName,@RequestParam(name = "isBak") Boolean isBak,@RequestParam(name = "adminName") String adminName){
confservice.replace(nginxPath,nginxContent,subContent,subName,isBak,adminName);
}


@GetMapping
("/buildConf")
public ConfExt buildConf(@RequestParam(name = "decompose") Boolean decompose,@RequestParam(name = "check") Boolean check){
  return confservice.buildConf(decompose,check);
}


}