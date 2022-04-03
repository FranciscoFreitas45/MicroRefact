package goorum.goorum.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MemberServiceController {

 private MemberService memberservice;


@GetMapping
("/setProfilePhoto")
public boolean setProfilePhoto(@RequestParam(name = "memberId") long memberId,@RequestParam(name = "profilePath") String profilePath){
  return memberservice.setProfilePhoto(memberId,profilePath);
}


}