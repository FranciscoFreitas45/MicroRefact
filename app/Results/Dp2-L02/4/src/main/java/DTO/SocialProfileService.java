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
import Interface.ActorService;
public class SocialProfileService {

 private  SocialProfileRepository socialProfileRepository;

 private  ActorService actorService;

 private  Validator validator;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public void deleteAllSocialProfiles(){
    Actor actor = this.actorService.loggedActor();
    Integer cont = actor.getSocialProfiles().size();
    List<SocialProfile> socialprofiles = new ArrayList<SocialProfile>();
    socialprofiles = actor.getSocialProfiles();
    for (int i = 0; i < cont; i++) this.deleteSocialProfile(socialprofiles.get(0));
    List<SocialProfile> deletedSocialprofiles = new ArrayList<SocialProfile>();
    deletedSocialprofiles = actor.getSocialProfiles();
    System.out.println();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteAllSocialProfiles"))

;
restTemplate.put(builder.toUriString(),null);
}


}