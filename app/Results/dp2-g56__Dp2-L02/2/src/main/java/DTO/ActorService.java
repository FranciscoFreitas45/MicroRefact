package DTO;
 import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import domain.Actor;
import domain.Box;
import domain.Message;
import domain.SocialProfile;
import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import Interface.SocialProfileService;
public class ActorService {

 private  ActorRepository actorRepository;

 private  SocialProfileService socialProfileService;

 private  ConfigurationService configurationService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public List<Box> getlistOfBoxes(Actor actor){
    return this.actorRepository.listOfBoxes(actor);
}


public List<Actor> getActors(){
    return this.actorRepository.getActors();
}


public Actor getActorByUsername(String a){
    return this.actorRepository.getActorByUserName(a);
}


public Boolean loggedAsActorBoolean(){
    Boolean res = true;
    UserAccount userAccount;
    try {
        userAccount = LoginService.getPrincipal();
        return res;
    } catch (Throwable oops) {
        res = false;
        return res;
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedAsActorBoolean"))

;
Boolean aux = restTemplate.getForObject(builder.toUriString(),Boolean.class);
return aux;
}


}