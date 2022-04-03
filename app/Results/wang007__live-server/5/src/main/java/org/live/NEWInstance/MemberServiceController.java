package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MemberServiceController {

 private MemberService memberservice;


@GetMapping
("/findMemberBymemberNo")
public Member findMemberBymemberNo(@RequestParam(name = "memberNo") String memberNo){
  return memberservice.findMemberBymemberNo(memberNo);
}


}