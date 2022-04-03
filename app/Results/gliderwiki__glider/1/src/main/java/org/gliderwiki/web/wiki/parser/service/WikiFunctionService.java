package org.gliderwiki.web.wiki.parser.service;
 import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiAgree;
public interface WikiFunctionService {


public String addWikiQuota(Integer WikiId)
;

public String addUserFriend(Integer loginUserIdx,Integer Id)
;

public String updateWikiProdectStatus(Integer WikiId)
;

public int updateAgreeUserInsert(Integer weWikiIdx,Integer weUserIdx)
;

public String addWikiFavorite(Integer weUserIdx,Integer WikiId,Integer SpaceId)
;

}