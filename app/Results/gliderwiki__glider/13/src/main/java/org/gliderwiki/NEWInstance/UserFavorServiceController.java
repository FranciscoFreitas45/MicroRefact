package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserFavorServiceController {

 private UserFavorService userfavorservice;


@GetMapping
("/getMyFavoriteSpaceList")
public List<WikiFavoriteVo> getMyFavoriteSpaceList(@RequestParam(name = "weUserIdx") Integer weUserIdx){
  return userfavorservice.getMyFavoriteSpaceList(weUserIdx);
}


@GetMapping
("/getMyFavoriteWikiList")
public List<WikiFavoriteVo> getMyFavoriteWikiList(@RequestParam(name = "weUserIdx") Integer weUserIdx){
  return userfavorservice.getMyFavoriteWikiList(weUserIdx);
}


}