package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WebuiProcessClassInfoController {

 private WebuiProcessClassInfo webuiprocessclassinfo;

 private WebuiProcessClassInfo webuiprocessclassinfo;


@GetMapping
("/of")
public WebuiProcessClassInfo of(@RequestParam(name = "processClassname") String processClassname){
  return webuiprocessclassinfo.of(processClassname);
}


@GetMapping
("/isForwardValueToJavaProcessInstance")
public boolean isForwardValueToJavaProcessInstance(@RequestParam(name = "parameterName") String parameterName){
  return webuiprocessclassinfo.isForwardValueToJavaProcessInstance(parameterName);
}


}