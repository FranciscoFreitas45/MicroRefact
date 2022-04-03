package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ActorController {

 private Actor actor;

 private Actor actor;


@PutMapping
("/setSocialProfiles")
public void setSocialProfiles(@RequestParam(name = "socialProfiles") List<SocialProfile> socialProfiles){
actor.setSocialProfiles(socialProfiles);
}


}