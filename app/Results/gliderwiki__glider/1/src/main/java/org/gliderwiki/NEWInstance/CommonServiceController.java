package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CommonServiceController {

 private CommonService commonservice;


@GetMapping
("/getWeBbsComment")
public WeBbsComment getWeBbsComment(@RequestParam(name = "weBbsCommentIdx") String weBbsCommentIdx){
  return commonservice.getWeBbsComment(weBbsCommentIdx);
}


@GetMapping
("/getWikiInfo")
public WeWiki getWikiInfo(@RequestParam(name = "weWikiIdx") Integer weWikiIdx){
  return commonservice.getWikiInfo(weWikiIdx);
}


@GetMapping
("/getMenuInfo")
public WeMenu getMenuInfo(@RequestParam(name = "we_menu_idx") Integer we_menu_idx){
  return commonservice.getMenuInfo(we_menu_idx);
}


@GetMapping
("/getWeGroupInfo")
public WeGroupInfo getWeGroupInfo(@RequestParam(name = "groupIdx") Integer groupIdx){
  return commonservice.getWeGroupInfo(groupIdx);
}


@GetMapping
("/getWeUserList")
public List<WeUser> getWeUserList(@RequestParam(name = "loginUserIdx") Integer loginUserIdx,@RequestParam(name = "userNick") String userNick,@RequestParam(name = "userEmail") String userEmail,@RequestParam(name = "userName") String userName){
  return commonservice.getWeUserList(loginUserIdx,userNick,userEmail,userName);
}


@GetMapping
("/getUserInfo")
public WeUser getUserInfo(@RequestParam(name = "weUserIdx") Integer weUserIdx){
  return commonservice.getUserInfo(weUserIdx);
}


@GetMapping
("/getUserProfileInfo")
public WeProfile getUserProfileInfo(@RequestParam(name = "weUserIdx") Integer weUserIdx){
  return commonservice.getUserProfileInfo(weUserIdx);
}


@GetMapping
("/getUserFileInfo")
public WeFile getUserFileInfo(@RequestParam(name = "weFileIdx") Integer weFileIdx){
  return commonservice.getUserFileInfo(weFileIdx);
}


@GetMapping
("/insertWeFile")
public int insertWeFile(@RequestParam(name = "weFile") WeFile weFile){
  return commonservice.insertWeFile(weFile);
}


@PutMapping
("/updateAllRead")
public void updateAllRead(@RequestParam(name = "userIdx") int userIdx){
commonservice.updateAllRead(userIdx);
}


@GetMapping
("/requestAlarmInfo")
public int requestAlarmInfo(@RequestParam(name = "we_login_user_idx") Integer we_login_user_idx,@RequestParam(name = "we_user_nick") String we_user_nick,@RequestParam(name = "we_meta_idx") Integer we_meta_idx,@RequestParam(name = "we_target_user_idx") Integer we_target_user_idx,@RequestParam(name = "we_wiki_idx") Integer we_wiki_idx,@RequestParam(name = "we_space_idx") Integer we_space_idx){
  return commonservice.requestAlarmInfo(we_login_user_idx,we_user_nick,we_meta_idx,we_target_user_idx,we_wiki_idx,we_space_idx);
}


@GetMapping
("/addFavorite")
public int addFavorite(@RequestParam(name = "weUserIdx") Integer weUserIdx,@RequestParam(name = "type") FavorityType type,@RequestParam(name = "spaceIdx") int spaceIdx,@RequestParam(name = "wikiIdx") int wikiIdx){
  return commonservice.addFavorite(weUserIdx,type,spaceIdx,wikiIdx);
}


@GetMapping
("/realNotiView")
public String realNotiView(@RequestParam(name = "userIdx") int userIdx){
  return commonservice.realNotiView(userIdx);
}


@GetMapping
("/getWikiSpaceInfoDWR")
public WeSpace getWikiSpaceInfoDWR(@RequestParam(name = "weWikiSpaceIdx") Integer weWikiSpaceIdx){
  return commonservice.getWikiSpaceInfoDWR(weWikiSpaceIdx);
}


@GetMapping
("/getWikiSearchList")
public List<WikiVo> getWikiSearchList(@RequestParam(name = "wiki_text") String wiki_text){
  return commonservice.getWikiSearchList(wiki_text);
}


@GetMapping
("/delFavorite")
public int delFavorite(@RequestParam(name = "weUserIdx") Integer weUserIdx,@RequestParam(name = "type") FavorityType type,@RequestParam(name = "addIdx") Integer addIdx){
  return commonservice.delFavorite(weUserIdx,type,addIdx);
}


@GetMapping
("/getWeWikiTagList")
public List<WeWikiTag> getWeWikiTagList(@RequestParam(name = "weWikiIdx") Integer weWikiIdx,@RequestParam(name = "weWikiRevision") Integer weWikiRevision){
  return commonservice.getWeWikiTagList(weWikiIdx,weWikiRevision);
}


@GetMapping
("/getWeWikiNoteList")
public List<WeWikiNote> getWeWikiNoteList(@RequestParam(name = "weWikiIdx") Integer weWikiIdx,@RequestParam(name = "weWikiRevision") Integer weWikiRevision){
  return commonservice.getWeWikiNoteList(weWikiIdx,weWikiRevision);
}


@GetMapping
("/getWeWikiLinkList")
public List<WeWikiLink> getWeWikiLinkList(@RequestParam(name = "weWikiIdx") Integer weWikiIdx,@RequestParam(name = "weWikiRevision") Integer weWikiRevision){
  return commonservice.getWeWikiLinkList(weWikiIdx,weWikiRevision);
}


@GetMapping
("/getWeWikiFileList")
public List<WeWikiFile> getWeWikiFileList(@RequestParam(name = "weWikiIdx") Integer weWikiIdx,@RequestParam(name = "weWikiRevision") Integer weWikiRevision){
  return commonservice.getWeWikiFileList(weWikiIdx,weWikiRevision);
}


@GetMapping
("/getWeWikiSummaryList")
public List<WeWikiSummary> getWeWikiSummaryList(@RequestParam(name = "weWikiIdx") Integer weWikiIdx,@RequestParam(name = "weWikiRevision") Integer weWikiRevision){
  return commonservice.getWeWikiSummaryList(weWikiIdx,weWikiRevision);
}


@GetMapping
("/getWeWikiGraph")
public WeWikiGraph getWeWikiGraph(@RequestParam(name = "weWikiIdx") Integer weWikiIdx,@RequestParam(name = "weWikiRevision") Integer weWikiRevision){
  return commonservice.getWeWikiGraph(weWikiIdx,weWikiRevision);
}


@PutMapping
("/updateUserPoint")
public void updateUserPoint(@RequestParam(name = "we_ins_user") Integer we_ins_user,@RequestParam(name = "point") int point){
commonservice.updateUserPoint(we_ins_user,point);
}


@PutMapping
("/delWeFile")
public void delWeFile(@RequestParam(name = "weFileIdx") Integer weFileIdx){
commonservice.delWeFile(weFileIdx);
}


@GetMapping
("/getWeWikiComment")
public WeWikiComment getWeWikiComment(@RequestParam(name = "weWikiCommentIdx") String weWikiCommentIdx){
  return commonservice.getWeWikiComment(weWikiCommentIdx);
}


@GetMapping
("/getWikiFileInfo")
public WeWikiFile getWikiFileInfo(@RequestParam(name = "weFileIdx") Integer weFileIdx){
  return commonservice.getWikiFileInfo(weFileIdx);
}


@GetMapping
("/getHtmlSourceByWikiIdx")
public StringBuffer getHtmlSourceByWikiIdx(@RequestParam(name = "we_wiki_idx") Integer we_wiki_idx){
  return commonservice.getHtmlSourceByWikiIdx(we_wiki_idx);
}


}