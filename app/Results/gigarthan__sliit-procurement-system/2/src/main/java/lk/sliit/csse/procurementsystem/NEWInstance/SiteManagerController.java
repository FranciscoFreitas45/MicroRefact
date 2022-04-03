package lk.sliit.csse.procurementsystem.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SiteManagerController {

 private SiteManager sitemanager;


@GetMapping
("/setEnabled")
public Object setEnabled(@RequestParam(name = "Object") Object Object){
  return sitemanager.setEnabled(Object);
}


@GetMapping
("/setPassword")
public Object setPassword(@RequestParam(name = "Object") Object Object){
  return sitemanager.setPassword(Object);
}


}