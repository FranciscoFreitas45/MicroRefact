package org.live.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.school.entity.Member;
@RestController
@CrossOrigin
public class MemberMobileUserController {

@Autowired
 private MemberMobileUserService membermobileuserservice;


@GetMapping
("/MobileUser/{id}/Member/getMember")
public Member getMember(@PathVariable(name="id") String idU3LS){
return membermobileuserservice.getMember(idU3LS);
}


@PutMapping
("/MobileUser/{id}/Member/setMember")
public void setMember(@PathVariable(name="id") String idU3LS,@RequestBody Member member){
membermobileuserservice.setMember(idU3LS,member);
}


}