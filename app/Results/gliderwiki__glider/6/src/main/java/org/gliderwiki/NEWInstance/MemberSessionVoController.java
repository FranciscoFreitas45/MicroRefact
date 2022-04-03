package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MemberSessionVoController {

 private MemberSessionVo membersessionvo;

 private MemberSessionVo membersessionvo;


@GetMapping
("/isGuest")
public boolean isGuest(){
  return membersessionvo.isGuest();
}


}