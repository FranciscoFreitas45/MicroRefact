package DTO;
 import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.SocialProfileRepository;
import security.LoginService;
import domain.Actor;
import domain.SocialProfile;
public class SocialProfileService {

 private  SocialProfileRepository socialProfileRepository;

 private  ActorService actorService;

 private  Validator validator;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public SocialProfile save(SocialProfile socialProfile){
    this.actorService.loggedAsActor();
    return this.socialProfileRepository.save(socialProfile);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))

.queryParam("socialProfile",socialProfile)
;
SocialProfile aux = restTemplate.getForObject(builder.toUriString(),SocialProfile.class);
return aux;
}


}