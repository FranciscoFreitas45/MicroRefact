package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WikiServiceController {

 private WikiService wikiservice;


@GetMapping
("/getWikiList")
public List<WeWiki> getWikiList(@RequestParam(name = "spaceIdx") int spaceIdx){
  return wikiservice.getWikiList(spaceIdx);
}


@GetMapping
("/getWeTemplateIdx")
public List<WeTemplate> getWeTemplateIdx(@RequestParam(name = "temp") WeTemplate temp){
  return wikiservice.getWeTemplateIdx(temp);
}


@GetMapping
("/getWikiForEdit")
public WeWiki getWikiForEdit(@RequestParam(name = "weWiki") WeWiki weWiki,@RequestParam(name = "loginUser") MemberSessionVo loginUser){
  return wikiservice.getWikiForEdit(weWiki,loginUser);
}


@GetMapping
("/addWikiAllContents")
public int addWikiAllContents(@RequestParam(name = "weWiki") WeWiki weWiki,@RequestParam(name = "weSpace") WeSpace weSpace,@RequestParam(name = "weTag") String weTag,@RequestParam(name = "weFileIdx") String[] weFileIdx,@RequestParam(name = "request") HttpServletRequest request){
  return wikiservice.addWikiAllContents(weWiki,weSpace,weTag,weFileIdx,request);
}


@GetMapping
("/modifiedWikiAndSaveRevision")
public int modifiedWikiAndSaveRevision(@RequestParam(name = "wikiForm") WeWiki wikiForm,@RequestParam(name = "weSpace") WeSpace weSpace,@RequestParam(name = "weTag") String weTag,@RequestParam(name = "loginUser") MemberSessionVo loginUser,@RequestParam(name = "request") HttpServletRequest request,@RequestParam(name = "weFileIdx") String[] weFileIdx){
  return wikiservice.modifiedWikiAndSaveRevision(wikiForm,weSpace,weTag,loginUser,request,weFileIdx);
}


@GetMapping
("/addSubWikiAllContents")
public int addSubWikiAllContents(@RequestParam(name = "weWiki") WeWiki weWiki,@RequestParam(name = "weSpace") WeSpace weSpace,@RequestParam(name = "weTag") String weTag,@RequestParam(name = "weFileIdx") String[] weFileIdx,@RequestParam(name = "request") HttpServletRequest request){
  return wikiservice.addSubWikiAllContents(weWiki,weSpace,weTag,weFileIdx,request);
}


@GetMapping
("/delWeWikiFile")
public int delWeWikiFile(@RequestParam(name = "weFileIdx") Integer weFileIdx){
  return wikiservice.delWeWikiFile(weFileIdx);
}


@GetMapping
("/insertWikiComment")
public int insertWikiComment(@RequestParam(name = "domain") WeWikiComment domain){
  return wikiservice.insertWikiComment(domain);
}


@GetMapping
("/addFavorite")
public int addFavorite(@RequestParam(name = "loginUserIdx") int loginUserIdx,@RequestParam(name = "spaceIdx") int spaceIdx,@RequestParam(name = "wikiIdx") int wikiIdx){
  return wikiservice.addFavorite(loginUserIdx,spaceIdx,wikiIdx);
}


}