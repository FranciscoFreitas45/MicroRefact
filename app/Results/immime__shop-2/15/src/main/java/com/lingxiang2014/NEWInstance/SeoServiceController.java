package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SeoServiceController {

 private SeoService seoservice;


@GetMapping
("/find")
public Seo find(@RequestParam(name = "type") Type type,@RequestParam(name = "cacheRegion") String cacheRegion){
  return seoservice.find(type,cacheRegion);
}


}