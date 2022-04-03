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


public Actor save(Actor a){
    return this.actorRepository.save(a);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))

.queryParam("a",a)
;
Actor aux = restTemplate.getForObject(builder.toUriString(),Actor.class);
return aux;
}


public Actor loggedActor(){
    Actor actor;
    UserAccount userAccount;
    try {
        userAccount = LoginService.getPrincipal();
        actor = this.actorRepository.getActorByUserName(userAccount.getUsername());
    } catch (Throwable oops) {
        actor = new Actor();
    }
    return actor;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedActor"))

;
Actor aux = restTemplate.getForObject(builder.toUriString(),Actor.class);
return aux;
}


public void loggedAsActor(){
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    Assert.isTrue(userAccount.getAuthorities().size() > 0);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedAsActor"))

;
restTemplate.put(builder.toUriString(),null);
}


}