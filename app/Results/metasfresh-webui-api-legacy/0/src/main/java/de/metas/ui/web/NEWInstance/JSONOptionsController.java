package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JSONOptionsController {

 private JSONOptions jsonoptions;


@GetMapping
("/newInstance")
public JSONOptions newInstance(){
  return jsonoptions.newInstance();
}


@GetMapping
("/ofAdLanguage")
public JSONOptions ofAdLanguage(@RequestParam(name = "adLanguage") String adLanguage){
  return jsonoptions.ofAdLanguage(adLanguage);
}


}