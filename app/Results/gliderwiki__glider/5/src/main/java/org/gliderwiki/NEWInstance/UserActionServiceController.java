package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserActionServiceController {

 private UserActionService useractionservice;


@GetMapping
("/getMyWikiLogAction")
public List<WikiLogVo> getMyWikiLogAction(@RequestParam(name = "memberSessionVo") MemberSessionVo memberSessionVo){
  return useractionservice.getMyWikiLogAction(memberSessionVo);
}


@GetMapping
("/getSpaceInfoByIdx")
public List<WikiLogVo> getSpaceInfoByIdx(@RequestParam(name = "wikiSpaceIdxList") List<Integer> wikiSpaceIdxList){
  return useractionservice.getSpaceInfoByIdx(wikiSpaceIdxList);
}


@GetMapping
("/getUserSpaceJoinList")
public List<WeSpaceJoin> getUserSpaceJoinList(@RequestParam(name = "joinDomain") WeSpaceJoin joinDomain,@RequestParam(name = "type") int type){
  return useractionservice.getUserSpaceJoinList(joinDomain,type);
}


}