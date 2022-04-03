package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OwsInfoController {

 private OwsInfo owsinfo;

 private OwsInfo owsinfo;


@GetMapping
("/findWfsInfo")
public OwsInfo findWfsInfo(@RequestParam(name = "info") List<OwsInfo> info){
  return owsinfo.findWfsInfo(info);
}


@GetMapping
("/findWmsInfo")
public OwsInfo findWmsInfo(@RequestParam(name = "info") List<OwsInfo> info){
  return owsinfo.findWmsInfo(info);
}


}