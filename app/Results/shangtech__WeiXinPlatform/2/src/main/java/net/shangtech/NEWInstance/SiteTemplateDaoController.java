package net.shangtech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SiteTemplateDaoController {

 private SiteTemplateDao sitetemplatedao;


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return sitetemplatedao.find(Object);
}


}