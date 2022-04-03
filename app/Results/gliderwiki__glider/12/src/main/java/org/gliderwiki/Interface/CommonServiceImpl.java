package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.CommonService;
public class CommonServiceImpl implements CommonService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public WeWiki getWikiInfo(Integer weWikiIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWikiInfo"))
    .queryParam("weWikiIdx",weWikiIdx)
;  WeWiki aux = restTemplate.getForObject(builder.toUriString(), WeWiki.class);

 return aux;
}


public List<WeWikiTag> getWeWikiTagList(Integer weWikiIdx,Integer weWikiRevision){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWeWikiTagList"))
    .queryParam("weWikiIdx",weWikiIdx)
    .queryParam("weWikiRevision",weWikiRevision)
;  List<WeWikiTag> aux = restTemplate.getForObject(builder.toUriString(), List<WeWikiTag>.class);

 return aux;
}


public List<WeWikiNote> getWeWikiNoteList(Integer weWikiIdx,Integer weWikiRevision){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWeWikiNoteList"))
    .queryParam("weWikiIdx",weWikiIdx)
    .queryParam("weWikiRevision",weWikiRevision)
;  List<WeWikiNote> aux = restTemplate.getForObject(builder.toUriString(), List<WeWikiNote>.class);

 return aux;
}


public List<WeWikiLink> getWeWikiLinkList(Integer weWikiIdx,Integer weWikiRevision){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWeWikiLinkList"))
    .queryParam("weWikiIdx",weWikiIdx)
    .queryParam("weWikiRevision",weWikiRevision)
;  List<WeWikiLink> aux = restTemplate.getForObject(builder.toUriString(), List<WeWikiLink>.class);

 return aux;
}


public List<WeWikiFile> getWeWikiFileList(Integer weWikiIdx,Integer weWikiRevision){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWeWikiFileList"))
    .queryParam("weWikiIdx",weWikiIdx)
    .queryParam("weWikiRevision",weWikiRevision)
;  List<WeWikiFile> aux = restTemplate.getForObject(builder.toUriString(), List<WeWikiFile>.class);

 return aux;
}


public List<WeWikiSummary> getWeWikiSummaryList(Integer weWikiIdx,Integer weWikiRevision){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWeWikiSummaryList"))
    .queryParam("weWikiIdx",weWikiIdx)
    .queryParam("weWikiRevision",weWikiRevision)
;  List<WeWikiSummary> aux = restTemplate.getForObject(builder.toUriString(), List<WeWikiSummary>.class);

 return aux;
}


public WeWikiGraph getWeWikiGraph(Integer weWikiIdx,Integer weWikiRevision){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWeWikiGraph"))
    .queryParam("weWikiIdx",weWikiIdx)
    .queryParam("weWikiRevision",weWikiRevision)
;  WeWikiGraph aux = restTemplate.getForObject(builder.toUriString(), WeWikiGraph.class);

 return aux;
}


public void updateUserPoint(Integer we_ins_user,int point){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateUserPoint"))
    .queryParam("we_ins_user",we_ins_user)
    .queryParam("point",point)
;
  restTemplate.put(builder.toUriString(), null);
}


public int requestAlarmInfo(Integer we_login_user_idx,String we_user_nick,Integer we_meta_idx,Integer we_target_user_idx,Integer we_wiki_idx,Integer we_space_idx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/requestAlarmInfo"))
    .queryParam("we_login_user_idx",we_login_user_idx)
    .queryParam("we_user_nick",we_user_nick)
    .queryParam("we_meta_idx",we_meta_idx)
    .queryParam("we_target_user_idx",we_target_user_idx)
    .queryParam("we_wiki_idx",we_wiki_idx)
    .queryParam("we_space_idx",we_space_idx)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int insertWeFile(WeFile weFile){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insertWeFile"))
    .queryParam("weFile",weFile)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public void delWeFile(Integer weFileIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delWeFile"))
    .queryParam("weFileIdx",weFileIdx)
;
  restTemplate.put(builder.toUriString(), null);
}


public WeWikiComment getWeWikiComment(String weWikiCommentIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWeWikiComment"))
    .queryParam("weWikiCommentIdx",weWikiCommentIdx)
;  WeWikiComment aux = restTemplate.getForObject(builder.toUriString(), WeWikiComment.class);

 return aux;
}


public WeWikiFile getWikiFileInfo(Integer weFileIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWikiFileInfo"))
    .queryParam("weFileIdx",weFileIdx)
;  WeWikiFile aux = restTemplate.getForObject(builder.toUriString(), WeWikiFile.class);

 return aux;
}


public StringBuffer getHtmlSourceByWikiIdx(Integer we_wiki_idx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getHtmlSourceByWikiIdx"))
    .queryParam("we_wiki_idx",we_wiki_idx)
;  StringBuffer aux = restTemplate.getForObject(builder.toUriString(), StringBuffer.class);

 return aux;
}


}