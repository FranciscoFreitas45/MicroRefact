package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeSpaceJoinController {

 private WeSpaceJoin wespacejoin;

 private WeSpaceJoin wespacejoin;


@PutMapping
("/setWe_user_idx")
public void setWe_user_idx(@RequestParam(name = "we_user_idx") Integer we_user_idx){
wespacejoin.setWe_user_idx(we_user_idx);
}


}