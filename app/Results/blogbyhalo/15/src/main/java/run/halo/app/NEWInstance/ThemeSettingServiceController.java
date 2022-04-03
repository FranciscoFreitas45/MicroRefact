package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ThemeSettingServiceController {

 private ThemeSettingService themesettingservice;


@GetMapping
("/listAll")
public Object listAll(@RequestParam(name = "Object") Object Object){
  return themesettingservice.listAll(Object);
}


@GetMapping
("/createInBatch")
public Object createInBatch(@RequestParam(name = "Object") Object Object){
  return themesettingservice.createInBatch(Object);
}


@GetMapping
("/listAsMapBy")
public Map<String,Object> listAsMapBy(@RequestParam(name = "themeId") String themeId){
  return themesettingservice.listAsMapBy(themeId);
}


}