package services;
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
@Service
@Transactional
public class SocialProfileService {

@Autowired
 private  SocialProfileRepository socialProfileRepository;

@Autowired
 private  ActorService actorService;

@Autowired
 private  Validator validator;


public void deleteSocialProfile(SocialProfile socialProfile){
    Actor logguedActor = this.actorService.getActorByUsername(LoginService.getPrincipal().getUsername());
    Assert.isTrue(logguedActor.getSocialProfiles().contains(socialProfile));
    List<SocialProfile> socialProfiles = logguedActor.getSocialProfiles();
    socialProfiles.remove(socialProfile);
    logguedActor.setSocialProfiles(socialProfiles);
    this.actorService.save(logguedActor);
    this.socialProfileRepository.delete(socialProfile);
}


public void flush(){
    this.socialProfileRepository.flush();
}


public void deleteAllSocialProfiles(){
    Actor actor = this.actorService.loggedActor();
    Integer cont = actor.getSocialProfiles().size();
    List<SocialProfile> socialprofiles = new ArrayList<SocialProfile>();
    socialprofiles = actor.getSocialProfiles();
    for (int i = 0; i < cont; i++) this.deleteSocialProfile(socialprofiles.get(0));
    List<SocialProfile> deletedSocialprofiles = new ArrayList<SocialProfile>();
    deletedSocialprofiles = actor.getSocialProfiles();
    System.out.println();
}


public SocialProfile save(SocialProfile socialProfile){
    this.actorService.loggedAsActor();
    return this.socialProfileRepository.save(socialProfile);
}


public SocialProfile findOne(int socialProfileId){
    return this.socialProfileRepository.findOne(socialProfileId);
}


public SocialProfile create(String nick,String name,String profileLink){
    SocialProfile socialProfile = new SocialProfile();
    socialProfile.setName(name);
    socialProfile.setNick(nick);
    socialProfile.setProfileLink(profileLink);
    return socialProfile;
}


public SocialProfile reconstruct(SocialProfile socialProfile,BindingResult binding){
    SocialProfile result;
    SocialProfile copy;
    // result = this.create();
    if (socialProfile.getId() == 0)
        result = socialProfile;
    else
        // copy = this.socialProfileRepository.findOne(socialProfile.getId());
        result = socialProfile;
    this.validator.validate(result, binding);
    return result;
}


public List<SocialProfile> findAll(){
    return this.socialProfileRepository.findAll();
}


public void delete(SocialProfile socialProfile){
    this.socialProfileRepository.delete(socialProfile);
}


}