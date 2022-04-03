package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.WikiService;
public class WikiServiceImpl implements WikiService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<WeTemplate> getWeTemplateIdx(WeTemplate temp){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWeTemplateIdx"))
    .queryParam("temp",temp)
;  List<WeTemplate> aux = restTemplate.getForObject(builder.toUriString(), List<WeTemplate>.class);

 return aux;
}


public List<WeWiki> getWikiList(int spaceIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWikiList"))
    .queryParam("spaceIdx",spaceIdx)
;  List<WeWiki> aux = restTemplate.getForObject(builder.toUriString(), List<WeWiki>.class);

 return aux;
}


public WeWiki getWikiForEdit(WeWiki weWiki,MemberSessionVo loginUser){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWikiForEdit"))
    .queryParam("weWiki",weWiki)
    .queryParam("loginUser",loginUser)
;  WeWiki aux = restTemplate.getForObject(builder.toUriString(), WeWiki.class);

 return aux;
}


public int addWikiAllContents(WeWiki weWiki,WeSpace weSpace,String weTag,String[] weFileIdx,HttpServletRequest request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addWikiAllContents"))
    .queryParam("weWiki",weWiki)
    .queryParam("weSpace",weSpace)
    .queryParam("weTag",weTag)
    .queryParam("weFileIdx",weFileIdx)
    .queryParam("request",request)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int modifiedWikiAndSaveRevision(WeWiki wikiForm,WeSpace weSpace,String weTag,MemberSessionVo loginUser,HttpServletRequest request,String[] weFileIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/modifiedWikiAndSaveRevision"))
    .queryParam("wikiForm",wikiForm)
    .queryParam("weSpace",weSpace)
    .queryParam("weTag",weTag)
    .queryParam("loginUser",loginUser)
    .queryParam("request",request)
    .queryParam("weFileIdx",weFileIdx)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int addSubWikiAllContents(WeWiki weWiki,WeSpace weSpace,String weTag,String[] weFileIdx,HttpServletRequest request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addSubWikiAllContents"))
    .queryParam("weWiki",weWiki)
    .queryParam("weSpace",weSpace)
    .queryParam("weTag",weTag)
    .queryParam("weFileIdx",weFileIdx)
    .queryParam("request",request)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int delWeWikiFile(Integer weFileIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delWeWikiFile"))
    .queryParam("weFileIdx",weFileIdx)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int insertWikiComment(WeWikiComment domain){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insertWikiComment"))
    .queryParam("domain",domain)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int addFavorite(int loginUserIdx,int spaceIdx,int wikiIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addFavorite"))
    .queryParam("loginUserIdx",loginUserIdx)
    .queryParam("spaceIdx",spaceIdx)
    .queryParam("wikiIdx",wikiIdx)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}