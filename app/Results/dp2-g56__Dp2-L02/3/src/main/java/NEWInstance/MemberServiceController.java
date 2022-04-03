package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MemberServiceController {

 private MemberService memberservice;


@PutMapping
("/deleteLoggedMember")
public void deleteLoggedMember(){
memberservice.deleteLoggedMember();
}


@GetMapping
("/loggedMember")
public Member loggedMember(){
  return memberservice.loggedMember();
}


@GetMapping
("/findAll")
public List<Member> findAll(){
  return memberservice.findAll();
}


@GetMapping
("/updateMember")
public Member updateMember(@RequestParam(name = "member") Member member){
  return memberservice.updateMember(member);
}


@GetMapping
("/save")
public Member save(@RequestParam(name = "member") Member member){
  return memberservice.save(member);
}


@PutMapping
("/loggedAsMember")
public void loggedAsMember(){
memberservice.loggedAsMember();
}


@GetMapping
("/findOne")
public Member findOne(@RequestParam(name = "id") int id){
  return memberservice.findOne(id);
}


}