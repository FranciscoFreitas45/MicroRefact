package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class INewsContentServiceController {

 private INewsContentService inewscontentservice;


@GetMapping
("/findByWebpageCode")
public NewsContentEntity findByWebpageCode(@RequestParam(name = "webpageCode") String webpageCode){
  return inewscontentservice.findByWebpageCode(webpageCode);
}


}