package net.shangtech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SiteTemplateServiceController {

 private SiteTemplateService sitetemplateservice;


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return sitetemplateservice.find(Object);
}


@GetMapping
("/findByType")
public List<SiteTemplate> findByType(@RequestParam(name = "type") int type){
  return sitetemplateservice.findByType(type);
}


}