package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MutiLangServiceIController {

 private MutiLangServiceI mutilangservicei;


@GetMapping
("/getLang")
public String getLang(@RequestParam(name = "lang_key") String lang_key,@RequestParam(name = "args") String args){
  return mutilangservicei.getLang(lang_key,args);
}


}