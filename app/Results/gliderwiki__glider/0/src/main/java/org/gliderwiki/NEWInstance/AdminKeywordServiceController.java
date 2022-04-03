package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AdminKeywordServiceController {

 private AdminKeywordService adminkeywordservice;


@GetMapping
("/deleteKeywordWiki")
public int deleteKeywordWiki(@RequestParam(name = "wikiIdx") Integer wikiIdx,@RequestParam(name = "wikiRevision") Integer wikiRevision,@RequestParam(name = "weUserIdx") Integer weUserIdx){
  return adminkeywordservice.deleteKeywordWiki(wikiIdx,wikiRevision,weUserIdx);
}


}