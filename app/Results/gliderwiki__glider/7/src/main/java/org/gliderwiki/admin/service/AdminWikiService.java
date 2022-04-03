package org.gliderwiki.admin.service;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeWiki;
public interface AdminWikiService {


public Object[] getWikiDetailInfo(Integer weWikiIdx,String attrId)
;

public Map<String,Object> entityDeleteWiki(Integer wikiIdx,Integer wikiRevision,Integer weUserIdx)
;

public int updateWikiStatus(Integer wikiIdx,Integer weUserIdx,String status)
;

public List<WeWiki> getWikiListMonth(Date month)
;

public List<WeWiki> getWikiSearchList(String weUserNick,String weWikiTitle,String weWikiText,String weSpaceName)
;

public int arrayDeleteWiki(String wikiIdx,Integer weUserIdx)
;

}