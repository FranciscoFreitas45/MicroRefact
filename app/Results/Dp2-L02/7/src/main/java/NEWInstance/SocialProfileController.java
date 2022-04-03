package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SocialProfileController {

 private SocialProfile socialprofile;

 private SocialProfile socialprofile;


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
socialprofile.setName(name);
}


@PutMapping
("/setNick")
public void setNick(@RequestParam(name = "nick") String nick){
socialprofile.setNick(nick);
}


@PutMapping
("/setProfileLink")
public void setProfileLink(@RequestParam(name = "profileLink") String profileLink){
socialprofile.setProfileLink(profileLink);
}


}