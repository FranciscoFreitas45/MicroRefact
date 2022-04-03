package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HaloPropertiesController {

 private HaloProperties haloproperties;


@GetMapping
("/getMode")
public Object getMode(@RequestParam(name = "Object") Object Object){
  return haloproperties.getMode(Object);
}


@GetMapping
("/getWorkDir")
public Object getWorkDir(@RequestParam(name = "Object") Object Object){
  return haloproperties.getWorkDir(Object);
}


@GetMapping
("/getAdminPath")
public Object getAdminPath(@RequestParam(name = "Object") Object Object){
  return haloproperties.getAdminPath(Object);
}


@GetMapping
("/isAuthEnabled")
public Object isAuthEnabled(@RequestParam(name = "Object") Object Object){
  return haloproperties.isAuthEnabled(Object);
}


}