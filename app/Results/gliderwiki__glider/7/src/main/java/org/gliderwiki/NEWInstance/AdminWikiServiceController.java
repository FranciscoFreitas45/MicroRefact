package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AdminWikiServiceController {

 private AdminWikiService adminwikiservice;


@GetMapping
("/getWikiSearchList")
public List<WeWiki> getWikiSearchList(@RequestParam(name = "weUserNick") String weUserNick,@RequestParam(name = "weWikiTitle") String weWikiTitle,@RequestParam(name = "weWikiText") String weWikiText,@RequestParam(name = "weSpaceName") String weSpaceName){
  return adminwikiservice.getWikiSearchList(weUserNick,weWikiTitle,weWikiText,weSpaceName);
}


}