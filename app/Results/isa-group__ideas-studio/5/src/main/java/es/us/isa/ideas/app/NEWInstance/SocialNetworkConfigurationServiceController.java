package es.us.isa.ideas.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SocialNetworkConfigurationServiceController {

 private SocialNetworkConfigurationService socialnetworkconfigurationservice;


@GetMapping
("/missingServices")
public List<String> missingServices(@RequestParam(name = "researcherId") int researcherId){
  return socialnetworkconfigurationservice.missingServices(researcherId);
}


@GetMapping
("/getSocialNetworConfigurations")
public Collection<SocialNetworkConfiguration> getSocialNetworConfigurations(@RequestParam(name = "researcherId") int researcherId){
  return socialnetworkconfigurationservice.getSocialNetworConfigurations(researcherId);
}


}