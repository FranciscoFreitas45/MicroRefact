package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MemberServiceController {

 private MemberService memberservice;


@GetMapping
("/findAll")
public List<Member> findAll(){
  return memberservice.findAll();
}


@GetMapping
("/isEmpty")
public Object isEmpty(@RequestParam(name = "Object") Object Object){
  return memberservice.isEmpty(Object);
}


@GetMapping
("/loggedMember")
public Member loggedMember(){
  return memberservice.loggedMember();
}


@GetMapping
("/createMember")
public Member createMember(){
  return memberservice.createMember();
}


@GetMapping
("/reconstruct")
public Member reconstruct(@RequestParam(name = "member") Member member,@RequestParam(name = "binding") BindingResult binding){
  return memberservice.reconstruct(member,binding);
}


@GetMapping
("/saveCreate")
public Member saveCreate(@RequestParam(name = "member") Member member){
  return memberservice.saveCreate(member);
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
("/updateMember")
public Member updateMember(@RequestParam(name = "member") Member member){
  return memberservice.updateMember(member);
}


@GetMapping
("/findOne")
public Member findOne(@RequestParam(name = "id") int id){
  return memberservice.findOne(id);
}


}