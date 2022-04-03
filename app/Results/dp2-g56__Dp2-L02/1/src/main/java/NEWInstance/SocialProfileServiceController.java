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


}