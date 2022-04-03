package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ConfigurationServiceController {

 private ConfigurationService configurationservice;


@GetMapping
("/getConfiguration")
public Configuration getConfiguration(){
  return configurationservice.getConfiguration();
}


@GetMapping
("/getSpamWords")
public List<String> getSpamWords(){
  return configurationservice.getSpamWords();
}


@GetMapping
("/isStringSpam")
public Boolean isStringSpam(@RequestParam(name = "s") String s,@RequestParam(name = "spamWords") List<String> spamWords){
  return configurationservice.isStringSpam(s,spamWords);
}


@GetMapping
("/computeScore")
public Double computeScore(@RequestParam(name = "a") Actor a){
  return configurationservice.computeScore(a);
}


@GetMapping
("/isActorSuspicious")
public Boolean isActorSuspicious(@RequestParam(name = "a") Actor a){
  return configurationservice.isActorSuspicious(a);
}


}