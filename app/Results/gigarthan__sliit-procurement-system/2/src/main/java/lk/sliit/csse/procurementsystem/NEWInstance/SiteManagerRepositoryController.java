package lk.sliit.csse.procurementsystem.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SiteManagerRepositoryController {

 private SiteManagerRepository sitemanagerrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return sitemanagerrepository.save(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return sitemanagerrepository.findAll(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return sitemanagerrepository.delete(Object);
}


}