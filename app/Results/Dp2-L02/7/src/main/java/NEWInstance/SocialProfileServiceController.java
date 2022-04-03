package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SocialProfileServiceController {

 private SocialProfileService socialprofileservice;


@GetMapping
("/save")
public SocialProfile save(@RequestParam(name = "socialProfile") SocialProfile socialProfile){
  return socialprofileservice.save(socialProfile);
}


@GetMapping
("/create")
public SocialProfile create(@RequestParam(name = "nick") String nick,@RequestParam(name = "name") String name,@RequestParam(name = "profileLink") String profileLink){
  return socialprofileservice.create(nick,name,profileLink);
}


@GetMapping
("/findOne")
public SocialProfile findOne(@RequestParam(name = "socialProfileId") int socialProfileId){
  return socialprofileservice.findOne(socialProfileId);
}


}