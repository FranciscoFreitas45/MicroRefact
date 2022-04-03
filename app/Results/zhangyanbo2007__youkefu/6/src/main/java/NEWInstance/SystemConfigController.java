package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SystemConfigController {

 private SystemConfig systemconfig;

 private SystemConfig systemconfig;


@GetMapping
("/isTenantshare")
public boolean isTenantshare(){
  return systemconfig.isTenantshare();
}


@GetMapping
("/isEnablessl")
public boolean isEnablessl(){
  return systemconfig.isEnablessl();
}


@GetMapping
("/isTenantconsole")
public boolean isTenantconsole(){
  return systemconfig.isTenantconsole();
}


@GetMapping
("/isEnableregorgi")
public boolean isEnableregorgi(){
  return systemconfig.isEnableregorgi();
}


@GetMapping
("/isEnabletneant")
public boolean isEnabletneant(){
  return systemconfig.isEnabletneant();
}


@PutMapping
("/setLoglevel")
public void setLoglevel(@RequestParam(name = "loglevel") String loglevel){
systemconfig.setLoglevel(loglevel);
}


@GetMapping
("/isSavelog")
public boolean isSavelog(){
  return systemconfig.isSavelog();
}


}