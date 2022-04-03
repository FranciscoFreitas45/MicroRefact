package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.UserActionService;
public class UserActionServiceImpl implements UserActionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public List<WikiLogVo> getMyWikiLogAction(MemberSessionVo memberSessionVo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMyWikiLogAction"))
    .queryParam("memberSessionVo",memberSessionVo)
;  List<WikiLogVo> aux = restTemplate.getForObject(builder.toUriString(), List<WikiLogVo>.class);

 return aux;
}


public List<WikiLogVo> getSpaceInfoByIdx(List<Integer> wikiSpaceIdxList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSpaceInfoByIdx"))
    .queryParam("wikiSpaceIdxList",wikiSpaceIdxList)
;  List<WikiLogVo> aux = restTemplate.getForObject(builder.toUriString(), List<WikiLogVo>.class);

 return aux;
}


}